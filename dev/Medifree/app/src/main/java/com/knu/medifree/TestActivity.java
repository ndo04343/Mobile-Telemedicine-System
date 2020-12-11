package com.knu.medifree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.knu.medifree.classes.DBTool;
import com.knu.medifree.classes.Reservation;
import com.knu.medifree.classes.User;

import java.text.ParseException;
import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {
    DBTool tool;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        btn = (Button) findViewById(R.id.btn);
        tv = (TextView) findViewById(R.id.tv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    tool.setReservation(new Reservation(
//                            "another_test_patient_id", "another_test_doctor_id", "2020/12/12/00/00"
//                    ));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }


               // ArrayList<Reservation> res = tool.getReservations();
            }
        });






    }
}