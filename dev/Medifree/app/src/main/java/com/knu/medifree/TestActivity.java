package com.knu.medifree;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {
    DBTool tool;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        tool = new DBTool();

        btn = (Button) findViewById(R.id.btn);
        tv = (TextView) findViewById(R.id.tv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Reservation> res = tool.getReservations("test_patient_id", User.TYPE_PATIENT);

                for (int i = 0 ; i < res.size() ;i ++) {
                    Log.d("Test_check", res.get(i).toString());
                }
            }
        });






    }
}