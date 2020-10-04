package com.knu.medifree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PRegActivity extends AppCompatActivity {
    Button btn_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_reg);

        // 객체 할당
        btn_reg = (Button) findViewById(R.id.p_reg_btn_reg);

        // 클릭 리스너 할당
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 회원 가입 버튼을 눌렀을 때
                // 현재 상황 : PHomeActivity로 이동




                Intent intent = new Intent(getApplicationContext(), PHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}