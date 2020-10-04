package com.knu.medifree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/*

    1 페이지의 1 번째 그림의 내용입니다. 로그인 관련 UI과 기능을 구현하는 액티비티입니다.

 */


public class LoginActivity extends AppCompatActivity {
    EditText et_email, et_password;
    Button btn_signin, btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 객체 할당
        et_email = (EditText) findViewById(R.id.login_et_email);
        et_password = (EditText) findViewById(R.id.login_et_password);

        btn_signin = (Button) findViewById(R.id.login_btn_signin);
        btn_signup = (Button) findViewById(R.id.login_btn_signup);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 로그인 버튼을 눌렀을 때의 이벤트임
                // 현재 상황 : PHomeActivity로 이동


                Intent intent = new Intent(getApplicationContext(), PHomeActivity.class);
                startActivity(intent);
                finish();
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



}