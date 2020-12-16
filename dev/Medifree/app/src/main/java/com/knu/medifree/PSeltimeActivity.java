package com.knu.medifree;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.knu.medifree.model.Reservation;
import com.knu.medifree.util.DBManager;

public class PSeltimeActivity extends AppCompatActivity implements View.OnClickListener {

    Button p_sel_time_btn_diag;
    private Context mContext;
    private CustomDialog Dialog;
    private FirebaseAuth mAuth;

    private String doctor_id;
    private String cur_uid;
    private String date;
    private String doctor_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_sel_time);

        Intent intent = getIntent();

        TextView textView = (TextView)findViewById(R.id.who_select);
        textView.setText(intent.getStringExtra("hospital_name")+" " +intent.getStringExtra("name")+" is selected");

        //intent를 통해 정보 받아오기
        doctor_name =intent.getExtras().getString("name");
        doctor_id = intent.getExtras().getString("id");
        // Debug
        Log.e("D_name : ",doctor_name);

        // 객체 할당
        /* 일단 이 Acitivity 흐름
            1. 시간대를 클릭한다
            2. 그 시간대를 String으로 받아온다.
            3. Calender를 사용하여 현재 년,월,일을 받아온다.
            4. Firestore에 들어갈 날짜 포맷을 만든다.
                예약 날짜 String format => "2020/12/16/10/00" (년/월/일/시/분) 으로 포맷을 만든다.
            5. Reservation 객체 생성자를 통해 생성 -> model.Reservation 클래스 참조.
         */
        // 현재 uid 가져오기.
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        cur_uid = user.getUid();

        // Reservation 객체 생성 , date 는 임시로 넣음.
        date = "2020/12/16/11/00";
        Reservation res = new Reservation(cur_uid, doctor_id, date,false);


        p_sel_time_btn_diag = (Button) findViewById(R.id.p_sel_time_btn_diag);
        p_sel_time_btn_diag.setOnClickListener(this);
    }
    // 예약 주고 받는 부분은 어디서 하는지 모르겠어서 일단 킵 합니당...
    @Override
    public void onClick(View v) {
        /* 밑에 time dialog가 먼지몰라서 일단 주석해놓음*/
        /* 예약 누르면 PHomeActivity로 옮기면서 예약메소드 실행 */

        // Reservation 객체 생성 , date 는 임시로 넣음.
        date = "2020/12/16/11/00";
        Reservation res = new Reservation(cur_uid, doctor_id, date,false);
        // 예약 메소드 실행
        DBManager.createReservation(res);
        //PHomeActivity로가면서 요청 기다리기
        startToast("예약요청을 성공하였습니다. 요청이 완료되면 예약목록에 추가됩니다.");
        Intent intent2 = new Intent(getApplicationContext(), PHomeActivity.class);
        startActivity(intent2);
        finish();



//        switch (v.getId()) {
//            case R.id.p_sel_time_btn_diag:
//                Dialog = new CustomDialog(this);
//                WindowManager.LayoutParams params = this.Dialog.getWindow().getAttributes();
//
//                this.Dialog.getWindow().setAttributes(params);
//                Dialog.setCancelable(false);
//                Dialog.getWindow().setGravity(Gravity.BOTTOM);
//                Dialog.show();
//                break;
//        }
    }

    private void startToast(String msg) { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show(); }
}