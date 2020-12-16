package com.knu.medifree;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.knu.medifree.model.Patient;
import com.knu.medifree.model.PatientAdapter2;
import com.knu.medifree.model.PatientAdapter3;

import java.util.ArrayList;

public class DOfficeActivity extends AppCompatActivity {

    public Button office_btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_office);
        populatePatientsList();

        office_btn=(Button)findViewById(R.id.d_office_btn);
        office_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DResNextActivity.class);
                startActivity(intent);
            }
        });
    }
    private void populatePatientsList() {
        ArrayList arrayOfUsers = Patient.getPatient();
        // Create the adapter to convert the array to views
        PatientAdapter3 adapter = new PatientAdapter3(this, arrayOfUsers);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listview_office_patient);
        listView.setAdapter(adapter);
    }
}

/*
*
*
* */