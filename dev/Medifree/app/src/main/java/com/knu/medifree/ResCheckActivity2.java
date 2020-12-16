package com.knu.medifree;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.knu.medifree.model.Patient;
import com.knu.medifree.model.PatientAdapter;

import java.util.ArrayList;

public class ResCheckActivity2 extends AppCompatActivity {
    public Button first_btn, origin_btn;
    public ImageButton dhome_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_res_check2);
        populatePatientsList();
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
    private void populatePatientsList() {
        ArrayList arrayOfUsers = Patient.getPatient();
        // Create the adapter to convert the array to views
        PatientAdapter adapter = new PatientAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listview_patientrequest);
        listView.setAdapter(adapter);
    }
}
