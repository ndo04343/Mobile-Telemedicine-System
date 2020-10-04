package com.knu.medifree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DHomeActivity extends AppCompatActivity {
    Button btn_check, btn_diag, btn_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_home);



        // 객체 할당
        btn_check = (Button) findViewById(R.id.d_home_btn_check);
        btn_diag = (Button) findViewById(R.id.d_home_btn_diag);
        btn_refresh = (Button) findViewById(R.id.d_home_btn_refresh);


        // 클릭 리스너 할당
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 예약하기 버튼을 눌렀을 때
                // 현재 상황 : ResCheckActivity로 이동



                Intent intent = new Intent(getApplicationContext(), ResCheckActivity.class);
                startActivity(intent);
                //finish(); 일단 뒤로 버튼을 눌러서 의사 홈으로 돌아올 수 있게 해둠.
            }
        });
        btn_diag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 진료실 버튼을 눌렀을 때
                // 현재 상황 :
                // TODO :
            }
        });
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 새로고침 버튼을 눌렀을 때
                // 현재 상황 :
                // TODO :
            }
        });
    }
}