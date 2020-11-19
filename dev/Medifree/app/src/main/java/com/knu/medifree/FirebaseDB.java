package com.knu.medifree;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.knu.medifree.reservation.Reservation;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class FirebaseDB {
    private static FirebaseFirestore db = null;
    private static FirebaseAuth mAuth;
    public static void getInstance(){
        if(db == null)
            db = FirebaseFirestore.getInstance();
    }

    public static FirebaseAuth getmAuth(){
        mAuth = FirebaseAuth.getInstance();
        return mAuth;
    }

    // document는 유일하다고 판단.
    public static Reservation getReservation(){
        final Reservation[] tmp = {null};
        Log.d("test", "test");
        Log.d("test",tmp.toString());
        String Doctor_id = mAuth.getCurrentUser().getUid();
        Log.d("test",Doctor_id);
        // for문으로 Reservation[]에 담아야됨.
        // 스냅샷 WhereEqualto함수로 가져온거 포문으로 돌림.\
        db.collection("Reservation")
                .whereEqualTo("Doctor_id", Doctor_id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult())

                                Log.d("test",document.getId() + " => " + document.getData());
                        }else
                            Log.d("test","실패");
                    }
                });
       return tmp[0];
    }

    public static void setReservation(String reservationId, String Doctor_id, String date, int id, String patient_id){

        Map<String ,Object> tmp = new HashMap<>();
        tmp.put("Doctor_id",Doctor_id);
        tmp.put("date",date);
        tmp.put("id",id);
        tmp.put("patient_id",patient_id);

        db.collection("Reservation").add(tmp);
        //db.collection("Reservation").document(reservationId).set(tmp); -> Id 생성가능

    }
}
