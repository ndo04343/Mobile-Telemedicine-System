package com.knu.medifree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TypeActivity extends AppCompatActivity {
    Button btn_pat, btn_doc, btn_ph;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        // 객체 할당
        btn_pat = (Button) findViewById(R.id.type_btn_patient);
        btn_doc = (Button) findViewById(R.id.type_btn_doctor);
        btn_ph = (Button) findViewById(R.id.type_btn_ph);

        // 클릭 리스너 할당
        btn_pat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Patient 버튼을 눌렀을 때
                // 현재 상황 : PResActivity 로 이동

                Log.i("CHECK_", "btn_pat");

                Intent intent = new Intent(getApplicationContext(), PRegActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Doctor 버튼을 눌렀을 때
                // 현재 상황 : DResActivity 로 이동


                Log.i("CHECK_", "btn_doc");

                Intent intent = new Intent(getApplicationContext(), DRegActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_ph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ph. 버튼을 눌렀을 때
                // 현재 상황 : 일단 대기
                // TODO : PH.

            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}