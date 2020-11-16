package com.knu.medifree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class PHomeActivity extends AppCompatActivity {
    Button btn_reg;
    Button btn_diag;
    ImageButton btn_refresh;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_home);
        list_reservation();
        // 객체 할당
        btn_reg = (Button) findViewById(R.id.p_home_btn_reg);
        btn_diag = (Button) findViewById(R.id.p_home_btn_diag);
        btn_refresh = (ImageButton) findViewById(R.id.p_home_btn_refresh);


        // 클릭 리스너 할당
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 예약하기 버튼을 눌렀을 때
                // 현재 상황 : 다이얼로그 뛰워줌 일단.

                AlertDialog.Builder builder = new AlertDialog.Builder(PHomeActivity.this);
                //builder.setTitle("걍 제목임. 여긴. 없어도 ㄱㅊ");
                builder.setMessage("새로 예약을 잡으시겟습니까?");


                builder.setPositiveButton(Html.fromHtml("<font color='#7F7F7F'>NO</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // NO 버튼이 눌린거임. 예약안할거니까 그냥 종료

                    }
                });
                builder.setNegativeButton(Html.fromHtml("<font color='#2F528F'>YES</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // YES 버튼이 눌린거임.
                        // 여기서 새로 다이어그램 만드는거
                        /****************************************************/
                        AlertDialog.Builder builder = new AlertDialog.Builder(PHomeActivity.this);
                        //builder.setTitle("걍 제목임. 여긴. 없어도 ㄱ");
                        builder.setMessage("등록 되지 않은 진료가 있습니까?");


                        builder.setPositiveButton(Html.fromHtml("<font color='#7F7F7F'>NO</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // NO 버튼이 눌린거임.
                                // 초진의 경우 병원과 의사 connect
                                Intent intent=new Intent(PHomeActivity.this,PSelhospActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton(Html.fromHtml("<font color='#2F528F'>YES</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // YES 버튼이 눌린거임.
                                //재진의 경우 (병원측에서 예약 자동 설정? 혹 환자가 설정? 물어보기)
                                Intent intent=new Intent(PHomeActivity.this,PSelhospAgainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        AlertDialog dialog = builder.create();
                       //다이얼로그 위치를 하단으로 조정
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();

                        params.gravity = Gravity.TOP | Gravity.LEFT;
                        params.x = 20;   //x position
                        params.y = 1700;   //y position
                       //조정 완료 후 화면출력
                        dialog.show();
                        //다이얼로그 크기를 조절 나중에 custom custom_dialog 로 ppt똑같이 만들기
                        params.width =  WindowManager.LayoutParams.MATCH_PARENT;
                        //params.height =  300;
                        dialog.getWindow().setAttributes(params);
                        /****************************************************/
                    }
                });

                AlertDialog dialog = builder.create();
                //다이얼로그 위치를 하단으로 조정
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();

                params.gravity = Gravity.TOP | Gravity.LEFT;
                params.x = 20;   //x position
                params.y = 1700;   //y position
                //조정 완료 후 화면출력
                dialog.show();
                //다이얼로그 크기를 조절 나중에 custom custom_dialog 로 ppt똑같이 만들기
                params.width =  WindowManager.LayoutParams.MATCH_PARENT;
                //params.height =  440;
                dialog.getWindow().setAttributes(params);
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

    private void list_reservation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String TAG = "제발좀되라 씨발";
        if (user != null){
            String uid = user.getUid();

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("Reservation")
                    .whereEqualTo("patient_id", uid)
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