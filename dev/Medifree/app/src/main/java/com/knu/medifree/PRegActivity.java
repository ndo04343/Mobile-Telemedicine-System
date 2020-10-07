package com.knu.medifree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//Firebase Auth를 위한 API
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PRegActivity extends AppCompatActivity {
    //TAG
    private static final String TAG = "SignUp_Patient";
    // 인스턴스 생성
    private FirebaseAuth mAuth;
    //Button
    Button btn_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_reg);
        // Auth 인스턴스 생성
        mAuth = FirebaseAuth.getInstance();

        // 객체 할당
        btn_reg = (Button) findViewById(R.id.p_reg_btn_reg);

        // 클릭 리스너 할당
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 회원 가입 버튼을 눌렀을 때
                createAccount_Patient();
                // 현재 상황 : PHomeActivity로 이동

            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void createAccount_Patient() {
        String email = ((TextView) findViewById(R.id.email_P)).getText().toString();
        String password = ((TextView) findViewById(R.id.password_P)).getText().toString();
        String passwordCheck = ((TextView) findViewById(R.id.passwordCheck_P)).getText().toString();

        if (email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0){
            if (password.equals(passwordCheck)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // 회원가입 성공
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    startToast("회원가입이 완료되었습니다.");
                                    //홈화면으로 이동.
                                    Intent intent = new Intent(getApplicationContext(), PHomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // 회원가입 실패=> 비밀번호 길이 및 아이디 중복 여부 등
                                    if (task.getException() != null){
                                        startToast(task.getException().toString());
                                    }
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
    //알림을 출력하는 method
    private void startToast(String msg) { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show(); }
}