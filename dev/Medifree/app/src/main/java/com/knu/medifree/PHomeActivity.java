package com.knu.medifree;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PHomeActivity extends AppCompatActivity {
    Button btn_reg, btn_diag, btn_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_home);


        // 객체 할당
        btn_reg = (Button) findViewById(R.id.p_home_btn_reg);
        btn_diag = (Button) findViewById(R.id.p_home_btn_diag);
        btn_refresh = (Button) findViewById(R.id.p_home_btn_refresh);


        // 클릭 리스너 할당
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 예약하기 버튼을 눌렀을 때
                // 현재 상황 : 다이얼로그 뛰워줌 일단.

                AlertDialog.Builder builder = new AlertDialog.Builder(PHomeActivity.this);
                builder.setTitle("걍 제목임. 여긴. 없어도 ㄱㅊ");
                builder.setMessage("새로 예약 할 거임?");


                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Ok 버튼이 눌린거임.

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // No 버튼이 눌린거임.
                        // 여기서 새로 다이어그램 만드는거
                        /****************************************************/
                        AlertDialog.Builder builder = new AlertDialog.Builder(PHomeActivity.this);
                        builder.setTitle("걍 제목임. 여긴. 없어도 ㄱ");
                        builder.setMessage("등록 되지 않은 진료가 있?");


                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Ok 버튼이 눌린거임.

                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // No 버튼이 눌린거임.
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                        /****************************************************/
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
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