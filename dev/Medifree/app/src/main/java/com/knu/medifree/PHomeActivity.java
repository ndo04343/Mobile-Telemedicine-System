package com.knu.medifree;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.CollectionReference;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.knu.medifree.model.User;
import com.knu.medifree.util.DBManager;
import com.knu.medifree.model.Reservation;
import com.knu.medifree.model.ReservationAdapter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static com.google.firebase.firestore.core.UserData.Source.Update;



public class PHomeActivity extends AppCompatActivity {
    private String uid;
    private Button btn_reg, btn_diag;
    private ListView listview_res;

    private FirebaseFirestore db;
    private static Boolean notResitered;

    public static Boolean getNotResitered() {
        return notResitered;
    }

    public static void setNotResitered(Boolean notResitered) {
        PHomeActivity.notResitered = notResitered;
    }

    private TimerTask second;
    private final Handler handler = new Handler();
    private ArrayList<Reservation> list_reservations = new ArrayList<>();
    ReservationAdapter res_adapter;

    // Additional 12.15
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_home);

        //
        // Additional part
        Intent intent = getIntent();

        // 현재 uid
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String cur_uid = user.getUid();



        // Reservation Init
        list_reservations = DBManager.getReservations();
        // reservation log로 확인 , get(i) 하고 .하면 관련해서 뭐 가져올수있는지 볼 수 있음.
        /*for (int i = 0 ;i < list_reservations.size(); i ++) {
            Log.e("List of Reservation", list_reservations.get(i).getDate());
        }*/

        // 객체 할당
        btn_reg = (Button) findViewById(R.id.p_home_btn_reg);
        btn_diag = (Button) findViewById(R.id.p_home_btn_diag);
        listview_res = (ListView) findViewById(R.id.p_home_listview);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // 초진,등록안된 진료 좇같아서 그냥 합치기로했음.
                Intent intent2 = new Intent(getApplicationContext(), PSelhospActivity.class);
                DBManager.startActivityWithHospitalReading(PHomeActivity.this , intent2);
            }
        });
        btn_diag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 진료실 버튼을 눌렀을 때
                // 현재 상황 :
                // TODO :
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void startToast(String msg) { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show(); }
}

