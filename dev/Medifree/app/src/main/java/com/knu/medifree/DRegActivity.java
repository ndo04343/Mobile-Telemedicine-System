package com.knu.medifree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class DRegActivity extends AppCompatActivity {
    Button btn_reg;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_reg);

        mAuth = FirebaseAuth.getInstance();

        // 객체 할당
        btn_reg = (Button) findViewById(R.id.d_reg_btn_reg);

        // 클릭 리스너 할당
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 회원 가입 버튼을 눌렀을 때
                // 현재 상황 : DHomeActivity로 이동
                createAccount_Doctor();
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void createAccount_Doctor() {
        String email = ((EditText) findViewById(R.id.email_D)).getText().toString();
        String password = ((EditText) findViewById(R.id.password_D)).getText().toString();
        String passwordCheck = ((EditText) findViewById(R.id.passwordCheck_D)).getText().toString();
        String hospital_Name = ((EditText)findViewById((R.id.hospital_Name))).getText().toString();

        if (email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0){
            if (password.equals(passwordCheck)) {
                // 의사가 입력한 병원과 전공이 맞는지 확인하는 작업.
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                DocumentReference docRef = db.collection("Hospital").document(hospital_Name);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                registerDoctor();
                                startToast("입력하신정보로 회원가입중입니다.");
                            } else {
                                startToast("입력하신 병원이 DB에 없습니다.");
                            }
                        } else {
                            startToast("get failed with "+ task.getException());
                        }
                    }
                });

            } else {
                // 비밀번호 확인실패.
                startToast("비밀번호가 일치하지 않습니다.");
            }
        } else{
            startToast("이메일 또는 비밀번호를 입력해주세요.");
        }


    }

    private void registerDoctor(){
        String email = ((EditText) findViewById(R.id.email_D)).getText().toString();
        String password = ((EditText) findViewById(R.id.password_D)).getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 회원가입 성공
                            FirebaseUser user = mAuth.getCurrentUser();
                            startToast("회원가입이 완료되었습니다.");

                            //현재 유저의 uid가져오기.
                            String uid = user.getUid();
                            //user정보를 db에 집어넣가.
                            insert_user_Information(uid);

                        } else {
                            // 회원가입 실패=> 비밀번호 길이 및 아이디 중복 여부 등
                            if (task.getException() != null){
                                startToast(task.getException().toString());
                            }
                        }
                    }
                });
    }
    //알림을 출력하는 method
    private void startToast(String msg) { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show(); }

    //생성된 uid 및 나머지 정보들 firestore에 넣는 작업.
    private void insert_user_Information(final String uid) {

        String name = ((EditText)findViewById(R.id.name_D)).getText().toString();
        String phone = ((EditText)findViewById((R.id.phone_D))).getText().toString();
        String hospital_Name = ((EditText)findViewById((R.id.hospital_Name))).getText().toString();
        String major = ((EditText)findViewById(R.id.major)).getText().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> user = new HashMap<>();
        user.put("userType","Doctor");
        user.put("name",name);
        user.put("phoneNum",phone);
        user.put("Hospital_Name",hospital_Name);
        user.put("Major",major);
        //실제 firestore에 추가하는 작업, add=> 자동으로 문서id(문서이름)를 만들어줌

        // Add a new document with a generated ID
        db.collection("Profile").document(uid)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void avoid) {
                        //uid정보를 hospital에 넣기.

                        insert_doctor_to_hospital(uid);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        startToast("정보저장에 실패하였습니다.");
                    }
                });

    }

    private void insert_doctor_to_hospital(String uid) {
        String hospital_Name = ((EditText)findViewById((R.id.hospital_Name))).getText().toString();
        String major = ((EditText)findViewById(R.id.major)).getText().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Hospital").document(hospital_Name)
                .update(major, FieldValue.arrayUnion(uid))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void avoid) {
                        //Doctor 홈화면으로 이동.
                        Intent intent = new Intent(getApplicationContext(), DHomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        startToast("정보저장에 실패하였습니다.");
                    }
                });
    }
}