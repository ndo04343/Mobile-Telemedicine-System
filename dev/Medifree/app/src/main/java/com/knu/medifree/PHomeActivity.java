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
    private ImageButton btn_refresh;
    private ListView listview_res;
    private static int flag ;    // flag를 통해서 초진, 등록되지않은진료를 구분, Intent에 추가해서 넘김.
    // 2 : 등록되지않은 진료.
    // 1 : 초진.
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
        uid = intent.getStringExtra("user_id");

        Log.d("tag" , uid);
        // 현재 uid
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String cur_uid = user.getUid();


        // 현재 uid와 같지않다면 액티비티종료.
        if (cur_uid.equals(uid) == false ) {
            startToast("사용자 정보가 일치하지않습니다. 다시 로그인 해주십시오.");
            finish();
        }

        // Reservation Init
        list_reservations = DBManager.getReservations();

        // 객체 할당
        btn_reg = (Button) findViewById(R.id.p_home_btn_reg);
        btn_diag = (Button) findViewById(R.id.p_home_btn_diag);
        btn_refresh = (ImageButton) findViewById(R.id.p_home_btn_refresh);
        listview_res = (ListView) findViewById(R.id.p_home_listview);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PHomeActivity.this);
                //builder.setTitle("걍 제목임. 여긴. 없어도 ㄱㅊ");
                builder.setMessage("새로 예약을 하시겠습니까?");


                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Ok 버튼이 눌린거임.
                        flag = 1;
                        Intent intent2 = new Intent(getApplicationContext(), PSelhospActivity.class);
                        intent2.putExtra("Flag", flag);
                        DBManager.startActivityWithHospitalReading(PHomeActivity.this , intent2);
                    }
                });
                builder.setNegativeButton("아니오.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // No 버튼이 눌린거임.
                        // 여기서 새로 다이어그램 만드는거
                        /****************************************************/
                        AlertDialog.Builder builder = new AlertDialog.Builder(PHomeActivity.this);
                        //builder.setTitle("걍 제목임. 여긴. 없어도 ㄱ");
                        builder.setMessage("현재 앱에 등록 되지 않은 진료가 있습니까?");


                        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Ok 버튼이 눌린거임. -> 의사에게 자신의 환자가 맞는지 요청

                            }
                        });
                        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // No 버튼이 눌린거임. -> 홈 그대로.

                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                        /****************************************************/
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
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
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 새로고침 버튼을 눌렀을 때
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

