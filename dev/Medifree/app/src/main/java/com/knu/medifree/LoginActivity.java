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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*

    1 페이지의 1 번째 그림의 내용입니다. 로그인 관련 UI과 기능을 구현하는 액티비티입니다.

 */


public class LoginActivity extends AppCompatActivity {
    Button btn_signin, btn_signup;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        // 버튼 객체 할당
        btn_signin = (Button) findViewById(R.id.login_btn_signin);
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
                // 현재 상황 : TypeActivity로 이


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
                                //홈화면으로 이동.
                                Intent intent = new Intent(getApplicationContext(), PHomeActivity.class);
                                startActivity(intent);
                                finish();
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