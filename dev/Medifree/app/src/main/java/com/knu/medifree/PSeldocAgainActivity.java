package com.knu.medifree;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.knu.medifree.util.DBManager;
import com.knu.medifree.model.Doctor;
import com.knu.medifree.model.DoctorAdapter;

public class PSeldocAgainActivity extends Activity  {
//    public LinearLayout doctor1;
//    private Context mContext;
//    private CustomDialogTwo Dialog;
//    public Button selectdoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_p_sel_doc_again);
    }
}
//        Intent intent = getIntent();//병원 이름 받아옵니다.
//        TextView textView = (TextView)findViewById(R.id.p_sel_doc_again_name);
//        textView.setText(intent.getStringExtra("hopitalname")+" is selected");
//
//        DoctorAdapter doctorAdapter = new DoctorAdapter(this, DBManager.getDoctors_list());
//        ListView listView2 = (ListView)findViewById(R.id.listview_doctorlist);
//        listView2.setAdapter(doctorAdapter);
//        listView2.setOnItemClickListener((parent, view, position, id) -> {
//            //String hospitalname = ((Hospital)hospitalAdapter.getItem(position)).getHospitalName();
//            String doctorName = ((Doctor)doctorAdapter.getItem(position)).getName();
//        });
//        selectdoctor=(Button)findViewById(R.id.p_sel_doctor_again_btn_diag);
//        selectdoctor.setOnClickListener(this);
//
//    }
//
//    public void onClick(View view) {
//
//        if (view.getId() == R.id.p_sel_doctor_again_btn_diag) {
//            Dialog = new CustomDialogTwo(this);
//            WindowManager.LayoutParams params = this.Dialog.getWindow().getAttributes();
//
//            this.Dialog.getWindow().setAttributes(params);
//            Dialog.setCancelable(false);
//            Dialog.getWindow().setGravity(Gravity.BOTTOM);
//            Dialog.show();
//        }




