package com.knu.medifree;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PSeltimeActivity extends AppCompatActivity implements View.OnClickListener {

    Button p_sel_time_btn_diag;
    private Context mContext;
    private CustomDialog Dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_sel_time);
        // 객체 할당
        p_sel_time_btn_diag = (Button) findViewById(R.id.p_sel_time_btn_diag);
        p_sel_time_btn_diag.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.p_sel_time_btn_diag:
                Dialog = new CustomDialog(this);
                WindowManager.LayoutParams params = this.Dialog.getWindow().getAttributes();

                this.Dialog.getWindow().setAttributes(params);
                Dialog.setCancelable(false);
                Dialog.getWindow().setGravity(Gravity.BOTTOM);
                Dialog.show();
                break;
        }
    }

}