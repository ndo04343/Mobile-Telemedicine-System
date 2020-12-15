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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.knu.medifree.util.DBManager;
import com.knu.medifree.model.Reservation;
import com.knu.medifree.model.ReservationAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

public class PHomeActivity extends AppCompatActivity {
    private String uid;
    private Button btn_reg, btn_diag;
    private ImageButton btn_refresh;
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
    private FirebaseFirestore mFirestore;
    private Query mQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_home);

        //
        // Additional part
        Intent intent = getIntent();
        uid = intent.getStringExtra("user_id");

        // Reservation Init


        // 객체 할당
        btn_reg = (Button) findViewById(R.id.p_home_btn_reg);
        btn_diag = (Button) findViewById(R.id.p_home_btn_diag);
        btn_refresh = (ImageButton) findViewById(R.id.p_home_btn_refresh);
        listview_res = (ListView) findViewById(R.id.p_home_listview);





        // 클릭 리스너 할당
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 예약하기 버튼을 눌렀을 때
                // 현재 상황 : 다이얼로그 뛰워줌 일단.

    }

    @Override
    protected void onStart() {
        super.onStart();
        reservationThreadStart();

    }


                        builder.setPositiveButton(Html.fromHtml("<font color='#7F7F7F'>NO</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // NO 버튼이 눌린거임.
                                // 초진의 경우 병원과 의사 connect
                                Intent intent=new Intent(PHomeActivity.this,PSelhospActivity.class);
                                setNotResitered(false);
                                startActivity(intent);


    private void onAddItemsClicked() {
        // Get a reference to the restaurants collection
        CollectionReference reservations = mFirestore.collection("Reservation");

        for (int i = 0; i < 10; i++) {
            // Get a random Restaurant POJO
            Reservation reservation = ReservationUtil.getSomeReservation(this);

            // Add a new document to the restaurants collection
            reservations.add(reservation);
        }
    }




    // 리스너를 구현하고 완전 비동기식으로 하고 싶지만,
    // 데이터 로딩시간하고 바쁘니까 그냥 이렇게 해둡니다.
    public void reservationThreadStart() {
        int timer_sec = 0;
        second = new TimerTask() {
            @Override
            public void run() {
                Log.i("HEESUNG", "Timer start");
                Update();
            }
        };
        Timer timer = new Timer();
        timer.schedule(second, 0, 5000);
    }
    protected void Update() {
        Runnable updater = new Runnable() {
            Calendar cal = Calendar.getInstance();
            int year, mon, dayOfMonth, hour, min, sec, dayOfWeek;

            public void run() {
                // 나중에 시간체크용
                year = cal.get(Calendar.YEAR);
                mon = cal.get(Calendar.MONTH);
                dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
                hour = cal.get(Calendar.HOUR);
                min = cal.get(Calendar.MINUTE);
                sec = cal.get(Calendar.SECOND);
                dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

                try {
                    // TODO : 여기서 리스트뷰 리셋하고 업데이트


                    list_reservations = DBManager.getReservations();
                    listview_res.setAdapter(res_adapter);
                } catch (InterruptedException | ParseException e) {
                    e.printStackTrace();
                }
            }
        };
        handler.post(updater);
    }
    //정진이 보셈
    private Map<String,Object>  major = new HashMap<>();
    private void list_hospital() {
        db = FirebaseFirestore.getInstance();
        db.collection("Hospital")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("돼라.", document.getId() + " => " + document.getData().getClass().getName());
                                // document.getData() => HashMap으로 받음.
                                // 따라서 Class에도 Hash Map으로 선언해야 될 것 같음.
                                major = document.getData();
                                // Hashmap ForEach.
                                // key  => 병원이름(서울병원,경대병원 등등)
                                // value = Doctor_uid -> ArrayList<String>으로 이루어져있음.
                                // (ArrayList<string>) => value자체는 그냥 Object를 가리키고 있어서 Typecasting함.
                                // 따라서 class를 만들 때,
                                // Hospital name = String
                                // major => Hashmap(String, ArrayList<String>)이런식으로 만들어야할듯. 이부분은 java적 언어 부족.
                                major.forEach((key, value)->{
                                    ArrayList<String> d = (ArrayList<String>)value;
                                    // Arraylist를 참조.
                                    for (String s : d){
                                        Log.d("제발돼라", String.format("키 -> %s, 값 -> %s", key, s));
                                    }
                                });
                            }
                        } else Log.w("안됐네", "Error getting documents.", task.getException());
                    }
                });
    }
}