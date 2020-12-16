package com.knu.medifree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class DAppActivity extends AppCompatActivity {
    Button btn_check;
    Button btn_diag;
    ImageButton btn_refresh;
    TextView timelist,timelist1,timelist2,timelist3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_app);
        list_reservation();
        // 객체 할당
        btn_check = (Button) findViewById(R.id.d_home_btn_check);
        btn_diag = (Button) findViewById(R.id.d_home_btn_diag);
        btn_refresh = (ImageButton) findViewById(R.id.d_home_btn_refresh);
        timelist=(TextView)findViewById(R.id.ten_oclock);
        timelist1 = (TextView)findViewById(R.id.eleven_oclock);
        timelist2 = (TextView)findViewById(R.id.fourteen_oclock);
        timelist3 = (TextView)findViewById(R.id.fifthteen_oclock);

        // 클릭 리스너 할당

        timelist.setOnClickListener(time);
        timelist1.setOnClickListener(time);
        timelist2.setOnClickListener(time);
        timelist3.setOnClickListener(time);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 예약하기 버튼을 눌렀을 때
                // 현재 상황 : ResCheckActivity로 이동
                Intent intent = new Intent(getApplicationContext(), ResCheckActivity.class);
                startActivity(intent);
                //finish(); 일단 뒤로 버튼을 눌러서 의사 홈으로 돌아올 수 있게 해둠.
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
                list_reservation();
            }
        });
    }
    View.OnClickListener time = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), DetailAppActivity.class);
            intent.putExtra("time",v.getTag().toString());
            startActivity(intent);
        }
    };
    private void list_reservation() {
        /*예약 리스트 만드는 부분인가???*/
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String TAG = "제발좀되라 씨발2";
        if (user != null){
            String uid = user.getUid();

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("Reservation")
                    .whereEqualTo("Doctor_id", uid)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                }
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });
        }
    }
}