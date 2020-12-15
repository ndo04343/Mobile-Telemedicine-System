package com.knu.medifree;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.knu.medifree.model.Doctor;
import com.knu.medifree.model.DoctorAdapter;
import com.knu.medifree.model.Hospital;
import com.knu.medifree.util.DBManager;

import java.util.ArrayList;
import java.util.List;


public class ResCheckActivity extends AppCompatActivity {
    public Button first_btn, origin_btn;
    public ImageButton dhome_btn;
    static final String[] PatientName={"Kim Haseung","Kim Semin"};
    static final String[] Patienttime={"10/20 15:00","10/21 14:00"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_res_check);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, PatientName) ;
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Patienttime) ;

        ListView listview = (ListView) findViewById(R.id.listview_patientrequest) ;
        listview.setAdapter(adapter) ;

        listview.setOnItemClickListener((parent, view, position, id) -> {
            String patientName = (String) parent.getItemAtPosition(position) ;
            textView1.setText(currentWord.getS1());

        });
        first_btn = (Button) findViewById(R.id.d_req_first);
        origin_btn = (Button) findViewById(R.id.d_req_origin);
        dhome_btn=(ImageButton)findViewById(R.id.backtodhome);


        first_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResCheckActivity.class);
                startActivity(intent);
            }
        });
        origin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResCheckActivity2.class);
                startActivity(intent);
            }
        });
        dhome_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DHomeActivity.class);
                startActivity(intent);
            }
        });

    }

}
