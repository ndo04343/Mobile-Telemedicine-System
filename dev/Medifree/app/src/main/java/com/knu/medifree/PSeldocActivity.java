package com.knu.medifree;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.knu.medifree.classes.DBTool;
import com.knu.medifree.classes.Doctor;
import com.knu.medifree.classes.DoctorAdapter;

import java.util.ArrayList;

public class PSeldocActivity extends Activity {
    public LinearLayout doctor1;
    static ArrayList<Doctor> list = DBTool.getDoctors_list();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_sel_doc);

        Intent intent = getIntent();//병원 이름 받아옵니다.
        TextView textView = (TextView)findViewById(R.id.p_sel_doc_name);
        textView.setText(intent.getStringExtra("hopitalname")+" is selected");

        DoctorAdapter doctorAdapter = new DoctorAdapter(this, list);
        ListView listView = (ListView)findViewById(R.id.listview_doctorlist);
        listView.setAdapter(doctorAdapter);
        doctorAdapter.notifyDataSetChanged();
        Log.d("TAG", "onCreate: "+doctorAdapter.getCount());
        for (int i = 0; i < list.size(); i++) {
            Log.d("TAG", "list :  "+list.get(i).getName());
        }
        doctor1 = (LinearLayout) findViewById(R.id.doctor1);
        //doctor1.setOnClickListener(this);

    }

    public void onClick(View view) {

        if (view.getId() == R.id.doctor1) {
            Intent intent = new Intent(PSeldocActivity.this, PSeltimeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}



