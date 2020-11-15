package com.knu.medifree;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDateTime;

/*

    1 페이지의 1 번째 그림의 내용입니다. 로그인 관련 UI과 기능을 구현하는 액티비티입니다.

 */


public class LoginActivity extends AppCompatActivity {
    Button btn_signin, btn_signup;

    private FirebaseAuth mAuth;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // 로딩화면 MEDIFREE 5초 출력
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);
        //로딩화면 종료

        FirebaseDB.getInstance();
        mAuth = FirebaseDB.getmAuth();
        // 버튼 객체 할당
        btn_signin= (Button) findViewById(R.id.login_btn_signin);
        btn_signup = (Button) findViewById(R.id.login_btn_signup);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 로그인 버튼을 눌렀을 때의 이벤트임
                // 현재 상황 : PHomeActivity로 이동
                SignUp_P();
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 회원 가입을 눌렀을 때의 이벤트임
                // 현재 상황 : TypeActivity로 이동.
                Intent intent = new Intent(getApplicationContext(), TypeActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void SignUp_P() {
        String email = ((EditText) findViewById(R.id.login_et_email)).getText().toString();
        String password = ((EditText) findViewById(R.id.login_et_password)).getText().toString();

        if (email.length() > 0 && password.length() > 0 ){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // 로그인 성공
                                FirebaseUser user = mAuth.getCurrentUser();
                                startToast("로그인 되었습니다.");

                                String uid = user.getUid();


                                //Firestore db로 부터 uid를 사용하여 현재 user의 userType을 가져오는 함수.
                                FirebaseFirestore db = FirebaseFirestore.getInstance();

                                DocumentReference docRef = db.collection("Profile").document(uid);
                                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            DocumentSnapshot document = task.getResult();
                                            if (document.exists()) {
                                                String userType= document.getData().get("userType").toString();
                                                if ( userType.equals("Patient")){
                                                    //patient일때 patient 홈 화면으로 간다.
                                                    Intent intent = new Intent(getApplicationContext(), PHomeActivity.class);
                                                    //intent.putExtra("uid", uid);
                                                    startActivity(intent);
                                                    finish();
                                                }else {
                                                    //Doctor라면 Doctor홈화면으로 간다.
                                                    Intent intent = new Intent(getApplicationContext(), DHomeActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                }

                                            } else {
                                                startToast("document가 없습니다.");
                                            }
                                        } else {
                                            startToast("get failed with "+ task.getException());
                                        }
                                    }
                                });

                            } else {
                                // 로그인 실패=> 비밀번호 길이 및 아이디 중복 여부 등
                                if (task.getException() != null) {
                                    startToast(task.getException().toString());
                                }else{
                                    startToast("Null이들어");
                                }
                            }
                        }
                    });
        } else{
            startToast("이메일 또는 비밀번호를 입력해주세요.");
        }


    }
    //알림을 출력하는 method
    private void startToast(String msg) { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show(); }


}