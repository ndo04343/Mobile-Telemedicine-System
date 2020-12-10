package com.knu.medifree.classes;

import android.service.restrictions.RestrictionsReceiver;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBTool {
    private FirebaseFirestore db;

    public DBTool() {
        db = FirebaseFirestore.getInstance();
    }

//    public ArrayList<Reservation> getReservations(String pat_id) {
//        ArrayList<Reservation> list = new ArrayList<Reservation>();
//
//
//    }


    public String setReservation(Reservation reservation) {
        // Return User_id
        Map<String, Object> resMap = new HashMap<>();
        final String[] created_id = new String[1];

        // Create map
        resMap.put("doctor_id", reservation.getDoctor_id());
        resMap.put("date", reservation.getDate());
        resMap.put("patient_id", reservation.getPatient_id());

        // Set
        db.collection("Reservation")
                .add(resMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {

                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        created_id[0] = documentReference.getId();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("DB_TOOL", "setReservation() error!", e);
                    }
                });

        return created_id[0];
    }
    public ArrayList<Reservation> getReservations(String uid, int type) {
        /* Usage : getReservations(uid, User.TYPE_DOCTOR)   */
        /*         getReservations(uid, User.TYPE_PATIENT)  */

        ArrayList<QueryDocumentSnapshot> res_snapshot = new ArrayList<QueryDocumentSnapshot>();

        db.collection("Reservation")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) res_snapshot.add(document);
                        } else {
                            Log.d("CHECK", "Error getting documents: ", task.getException());
                        }
                    }
                });

        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        for (int i = 0 ; i < res_snapshot.size(); i ++ ) {
            if (res_snapshot.get(i).getString("patient_id").equals(uid))
                reservations.add(
                        new Reservation(
                                res_snapshot.get(i).getString("patient_id")
                                , res_snapshot.get(i).getString("doctor_id")
                                , res_snapshot.get(i).getString("date")
                                , res_snapshot.get(i).getId()
                        )
                );
        }
        return reservations;
    }



}
