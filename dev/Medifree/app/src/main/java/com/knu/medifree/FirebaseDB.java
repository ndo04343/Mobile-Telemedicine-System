package com.knu.medifree;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.knu.medifree.reservation.Reservation;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class FirebaseDB {
    private static FirebaseFirestore db = null;

    public static void getInstance(){
        if(db == null)
            db = FirebaseFirestore.getInstance();
    }

    public static FirebaseAuth getmAuth(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        return mAuth;
    }

    // document는 유일하다고 판단.
    public static Reservation getReservation(String reservationId){
        final Reservation[] tmp = {null};
        db.collection("Reservation").document(reservationId)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("set", String.valueOf(documentSnapshot));
                Reservation reservation = documentSnapshot.toObject(Reservation.class);
                tmp[0] = reservation;
            }
        });
        return tmp[0];
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void setReservation(String reservationId, String Doctor_id, LocalDateTime data, int id, String patient_id){

        Map<String ,Object> tmp = new HashMap<>();
        tmp.put("Doctor_id",Doctor_id);
        tmp.put("data",data);
        tmp.put("id",id);
        tmp.put("patient_id",patient_id);

        db.collection("Reservation").add(tmp);

    }
}
