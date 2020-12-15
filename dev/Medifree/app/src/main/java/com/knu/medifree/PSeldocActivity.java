package com.knu.medifree;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.knu.medifree.util.DBManager;
import com.knu.medifree.model.Doctor;
import com.knu.medifree.model.DoctorAdapter;

import java.util.ArrayList;

public class PSeldocActivity extends Activity {
    public LinearLayout doctor1;
    static ArrayList<Doctor> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_p_sel_doc);
        setContentView(R.layout.activity_p_sel_doc);
        Intent intent = getIntent();//병원 이름 받아옵니다.
        TextView textView = (TextView)findViewById(R.id.p_sel_doc_name);
        textView.setText(intent.getStringExtra("hopitalname")+" is selected");


        DoctorAdapter doctorAdapter = new DoctorAdapter(this, DBManager.getDoctors_list());
        ListView listView2 = (ListView)findViewById(R.id.listview_doctorlist);
        listView2.setAdapter(doctorAdapter);
        listView2.setOnItemClickListener((parent, view, position, id) -> {
            //String hospitalname = ((Hospital)hospitalAdapter.getItem(position)).getHospitalName();
            String doctorName = ((Doctor)doctorAdapter.getItem(position)).getName();
            Intent intent2 = new Intent(PSeldocActivity.this, PSeltimeActivity.class);
            intent2.putExtra("doctorName",doctorName);
            startActivity(intent2);
        });



        //doctor1 = (LinearLayout) findViewById(R.id.doctor1);
       // doctor1.setOnClickListener(onClickListener);

    }

}



