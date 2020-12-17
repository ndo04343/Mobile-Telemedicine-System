package com.knu.medifree;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.knu.medifree.model.Reservation;
import com.knu.medifree.util.DBManager;

public class
PrescriptionActivity extends AppCompatActivity {

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        dialog = new Dialog(PrescriptionActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_check);
        ((TextView)(dialog.findViewById(R.id.timecheck))).setText("다음 예약을 하시겠습니까?");


        findViewById(R.id.d_office_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }
    public void showDialog(){
        dialog.show();
        Button noBtn = dialog.findViewById(R.id.noBtn);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
               // Reservation res = new Reservation(cur_uid, doctor_id, date, false,patient_name ,doctor_name);
                //Log.d("TAG", "onClick: "+res);
                // 예약 메소드 실행
                //DBManager.createReservation(res);
                //PHomeActivity로가면서 요청 기다리기
                //startToast(res.getDate()+" 예약요청을 성공하였습니다. 요청이 완료되면 예약목록에 추가됩니다.");
               // Intent intent2 = new Intent(getApplicationContext(), PHomeActivity.class);
                //startActivity(intent2);
               // finish();
            }
        });
    }
}