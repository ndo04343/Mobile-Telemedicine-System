package com.knu.medifree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.knu.medifree.actors.User;

/*
    1번째 페이지의 2번째 그림에 관한 내용입니다. 앱을 초기화하고 유저 정보 프로세싱하는 액티비티입니다.
 */


public class InitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        // TODO : Data Processing
        boolean firstUser = true;
        // 여기서 데이터 처
        Log.i("ACT", "ACTIVITY : InitActivity Created");





        // 일단 Login activity로 이동.
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.i("ACT", "ACTIVITY : InitActivity Destroyed");
//        // TODO : 데이터 손상 확인
//    }
}