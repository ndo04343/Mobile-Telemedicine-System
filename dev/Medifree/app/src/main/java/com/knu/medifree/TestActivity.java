package com.knu.medifree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.knu.medifree.util.DBManager;

public class TestActivity extends AppCompatActivity {
    DBManager tool;
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