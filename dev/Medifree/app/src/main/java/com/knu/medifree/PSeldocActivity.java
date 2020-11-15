package com.knu.medifree;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class PSeldocActivity extends Activity implements View.OnClickListener {
    public LinearLayout doctor1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_p_sel_doc);

        doctor1 = (LinearLayout) findViewById(R.id.doctor1);
        doctor1.setOnClickListener(this);

    }

    public void onClick(View view) {

        if (view.getId() == R.id.doctor1) {
            Intent intent = new Intent(PSeldocActivity.this, PSeltimeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}



