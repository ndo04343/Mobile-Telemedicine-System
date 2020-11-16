package com.knu.medifree;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DRegNextActivity<database> extends AppCompatActivity {
    ImageButton btn_reg;
    private FirebaseAuth mAuth;
    private Spinner hospitalNameSpinner,majorSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_reg_next);

        mAuth = FirebaseAuth.getInstance();

        // 객체 할당
        btn_reg = (ImageButton) findViewById(R.id.d_reg_btn_reg);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("YOUR_REFERENCE");


        Spinner major_spinner = (Spinner) findViewById(R.id.major);

        myRef.child("Hospital").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> hospitals = new ArrayList<String>();
                for (DataSnapshot hospitalSnapshot : dataSnapshot.getChildren()) {
                    String hospital = hospitalSnapshot.getValue(String.class);
                    hospitals.add(hospital);
                }
                Spinner hospital_spinner = (Spinner) findViewById(R.id.hospital_Name);
                ArrayAdapter<String> hospitalsAdapter = new ArrayAdapter<String>(DRegNextActivity.this, android.R.layout.simple_spinner_item, hospitals);
                hospitalsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                hospital_spinner.setAdapter(hospitalsAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                startToast("생성안됨");
            }
        });

        // 클릭 리스너 할당
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 회원 가입 버튼을 눌렀을 때
                // 현재 상황 : DHomeActivity로 이동
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String uid = user.getUid();
                insert_user_Information(uid);
            }
        });


    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    //알림을 출력하는 method
    private void startToast(String msg) { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show(); }

    //생성된 uid 및 나머지 정보들 firestore에 넣는 작업.
    private void insert_user_Information(final String uid) {


        String hospital_Name = ((Spinner)findViewById((R.id.hospital_Name))).getSelectedItem().toString();
        String major = ((Spinner)findViewById(R.id.major)).getSelectedItem().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> user = new HashMap<>();
        user.put("userType","Doctor");
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
        String hospital_Name = ((Spinner)findViewById((R.id.hospital_Name))).getSelectedItem().toString();
        String major = ((Spinner)findViewById(R.id.major)).getSelectedItem().toString();
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
