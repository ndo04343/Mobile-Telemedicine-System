package com.knu.medifree;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.knu.medifree.classes.DBTool;
import com.knu.medifree.classes.Doctor;
import com.knu.medifree.classes.DoctorAdapter;

public class PSeldocAgainActivity extends Activity implements View.OnClickListener {
    public LinearLayout doctor1;
    private Context mContext;
    private CustomDialogTwo Dialog;
    public Button selectdoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_p_sel_doc_again);
        Intent intent = getIntent();//병원 이름 받아옵니다.
        TextView textView = (TextView)findViewById(R.id.p_sel_doc_again_name);
        textView.setText(intent.getStringExtra("hopitalname")+" is selected");

        DoctorAdapter doctorAdapter = new DoctorAdapter(this, DBTool.getDoctors_list());
        ListView listView2 = (ListView)findViewById(R.id.listview_doctorlist);
        listView2.setAdapter(doctorAdapter);
        listView2.setOnItemClickListener((parent, view, position, id) -> {
            //String hospitalname = ((Hospital)hospitalAdapter.getItem(position)).getHospitalName();
            String doctorName = ((Doctor)doctorAdapter.getItem(position)).getName();
        });
        selectdoctor=(Button)findViewById(R.id.p_sel_doctor_again_btn_diag);
        selectdoctor.setOnClickListener(this);

    }

    public void onClick(View view) {

        if (view.getId() == R.id.p_sel_doctor_again_btn_diag) {
            Dialog = new CustomDialogTwo(this);
            WindowManager.LayoutParams params = this.Dialog.getWindow().getAttributes();

            this.Dialog.getWindow().setAttributes(params);
            Dialog.setCancelable(false);
            Dialog.getWindow().setGravity(Gravity.BOTTOM);
            Dialog.show();
        }
    }
}



