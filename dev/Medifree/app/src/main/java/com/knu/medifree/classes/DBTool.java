package com.knu.medifree.classes;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DBTool {
    private static DateFormat dateformat;

    // Encapsulate target
    private static ArrayList<Reservation> reservations_result;
    private static boolean updated;

    // User info
    private static String uid;
    private static int utype;

    public static void InitDBTool(String uid, int utype) throws ParseException {
        dateformat = new SimpleDateFormat("yyyy/MM/dd/kk/mm");
        DBTool.uid = uid;
        DBTool.utype = utype;
        updated = false;

        DBTool.queryReservations(uid, utype);
    }

    // Getter
    public static boolean isUpdated() { return updated; }

    //
    // Reservation Control
    public static void setReservation(Reservation reservation) throws ParseException {
        // Return reservation_id
        Map<String, Object> resMap = new HashMap<>();

        // Create map
        resMap.put("patient_id", reservation.getPatient_id());
        resMap.put("doctor_id", reservation.getDoctor_id());
        resMap.put("date", reservation.getDate());
        resMap.put("completed", reservation.isCompleted());

        // Set
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Reservation")
                .add(resMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {

                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.i("HEESUNG", documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("HEESUNG", "setReservation() error!", e);
                    }
                });


        queryReservations(uid, utype);
    }
    public static ArrayList<Reservation> getReservations() throws InterruptedException, ParseException {
        /* Usage : getReservations(uid, User.TYPE_DOCTOR)   */
        /*         getReservations(uid, User.TYPE_PATIENT)  */
        queryReservations(uid, utype);
        if (updated)
            return reservations_result;
        return new ArrayList<Reservation>();
    }
    private static synchronized void queryReservations(String uid, int type) throws ParseException {
        // Warning! You need not use this method!
        // Date control ("yyyy/MM/dd/kk/mm format")
        // Index : Date format
        String now_datestr = dateformat.format(Calendar.getInstance().getTime());
        Date date = dateformat.parse(now_datestr);

        if (type == User.TYPE_DOCTOR) {
            // Query Setting
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference resRef = db.collection("Reservation");
            Query resDataQuery = resRef.whereEqualTo("doctor_id", uid);

            Log.i("HEESUNG", "Waiting DB Callback...");
            resDataQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        Log.i("HEESUNG", "Getting DB Callback...");
                        updated = false;
                        reservations_result = new ArrayList<Reservation>();

                        // Critical section control
                        for (QueryDocumentSnapshot document : task.getResult()) {

                            reservations_result.add(new Reservation(
                                    document.getString("patient_id")
                                    , document.getString("doctor_id")
                                    , document.getString("date")
                                    , document.getBoolean("completed")
                                    , document.getId()
                            ));
                        }
                        updated = true;
                        Log.i("HEESUNG", "Reservation DB Updeate complete.");
                    }
                }
            });
        } else if (type == User.TYPE_PATIENT) {
            // Query Setting
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference resRef = db.collection("Reservation");
            Query resDataQuery = resRef.whereEqualTo("patient_id", uid);

            Log.i("HEESUNG", "Waiting DB Callback...");
            resDataQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        Log.i("HEESUNG", "Getting DB Callback...");
                        updated = false;
                        reservations_result = new ArrayList<Reservation>();

                        // Critical section control
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            reservations_result.add(new Reservation(
                                    document.getString("patient_id")
                                    , document.getString("doctor_id")
                                    , document.getString("date")
                                    , document.getBoolean("completed")
                                    , document.getId()
                            ));
                        }
                        updated = true;
                        Log.i("HEESUNG", "Reservation DB Updeate complete.");
                    }
                }
            });
        } // End of if (type == User.TYPE_DOCTOR)
    } // End of method



}
