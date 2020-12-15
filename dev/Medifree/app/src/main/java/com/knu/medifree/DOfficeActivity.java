package com.knu.medifree;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DOfficeActivity extends AppCompatActivity {

    public Button office_btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_office);

        office_btn=(Button)findViewById(R.id.d_office_btn);
        office_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DResNextActivity.class);
                startActivity(intent);
            }
        });
    }
}

/*
*
*
* */