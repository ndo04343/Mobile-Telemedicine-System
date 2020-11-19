package com.knu.medifree;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DResNextActivity extends AppCompatActivity {

    private TextView patient_date;
    private DatePickerDialog.OnDateSetListener callbackMethod;
    private Spinner patient_time;
    private ArrayAdapter<String> arrayAdapter;
    private Context mContext;
    private CustomDialogThree Dialog;
    public Button res_yes, res_no;
    public Button res_untact, res_contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_next_app);
        res_contact = (Button) findViewById(R.id.p_res_contact);
        res_untact = (Button) findViewById(R.id.p_res_untact);
        patient_date = (TextView) findViewById(R.id.p_res_date);
        res_yes=(Button)findViewById(R.id.p_res_yes);
        res_no=(Button)findViewById(R.id.p_res_no);

        this.InitializeView();
        this.InitializeListener();

        patient_time = (Spinner) findViewById(R.id.p_res_time);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, (String[]) getResources().getStringArray(R.array.spinner_time));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        patient_time.setAdapter(arrayAdapter);
        patient_time.setSelection(0);



        patient_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(DResNextActivity.this, callbackMethod, 2020, 11, 19);
                dialog.show();
            }
        });


        res_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                res_contact.setBackgroundColor(Color.parseColor("#2DA5E1"));
                res_untact.setBackgroundColor(Color.parseColor("#FCFCFC"));

            }
        });
        res_untact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                res_untact.setBackgroundColor(Color.parseColor("#2DA5E1"));
                res_contact.setBackgroundColor(Color.parseColor("#FCFCFC"));

            }
        });

    }

    public void InitializeView() {
        patient_date = (TextView) findViewById(R.id.p_res_date);

    }

    public void InitializeListener() {
        callbackMethod = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                patient_date.setText(year + "/" + monthOfYear + "/" + dayOfMonth);
            }
        };
    }

    public void onClick(View view) {

        if(view.getId()==R.id.p_res_yes) {
            Dialog = new CustomDialogThree(this);
            WindowManager.LayoutParams params = this.Dialog.getWindow().getAttributes();

            this.Dialog.getWindow().setAttributes(params);
            Dialog.setCancelable(false);
            Dialog.getWindow().setGravity(Gravity.BOTTOM);
            Dialog.show();
        }
        else if (view.getId() == R.id.p_res_no) {
            Intent intent=new Intent(DResNextActivity.this,DAppActivity.class);
            startActivity(intent);
            finish();

        }
    }


}


