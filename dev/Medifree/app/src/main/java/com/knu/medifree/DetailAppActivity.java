package com.knu.medifree;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ListView;

import com.knu.medifree.model.Patient;
import com.knu.medifree.model.PatientAdapter;
import com.knu.medifree.model.PatientAdapter2;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DetailAppActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_detailapp);
        populatePatientsList();
}
    private void populatePatientsList() {
        ArrayList arrayOfUsers = Patient.getPatient();
        // Create the adapter to convert the array to views
        PatientAdapter2 adapter = new PatientAdapter2(this, arrayOfUsers);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listview_patientlist);
        listView.setAdapter(adapter);
    }
}
