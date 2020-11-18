package com.knu.medifree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DHomeActivity extends AppCompatActivity {
    ImageButton btn_app;
    ImageButton btn_office;
    ImageButton btn_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_home);

        // 객체 할당
        btn_app = (ImageButton) findViewById(R.id.d_app);
        btn_office = (ImageButton) findViewById(R.id.d_office);
        btn_request = (ImageButton) findViewById(R.id.d_request);
        // 클릭 리스너 할당
        btn_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 예약하기 버튼을 눌렀을 때
                // 현재 상황 : DAppActivity로 이동
                Intent intent = new Intent(getApplicationContext(), DAppActivity.class);
                startActivity(intent);
                //finish(); 일단 뒤로 버튼을 눌러서 의사 홈으로 돌아올 수 있게 해둠.
            }
        });
        btn_office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 진료실 버튼을 눌렀을 때
                // 현재 상황 :
                Intent intent = new Intent(getApplicationContext(), DResNextActivity.class);
                startActivity(intent);
                // TODO :
            }
        });
        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 새로고침 버튼을 눌렀을 때
                // 현재 상황 :
                Intent intent = new Intent(getApplicationContext(), ResCheckActivity.class);
                startActivity(intent);
                // TODO :

            }
        });
    }


}