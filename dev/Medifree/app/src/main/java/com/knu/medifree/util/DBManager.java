package com.knu.medifree.util;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.knu.medifree.model.Doctor;
import com.knu.medifree.model.Hospital;
import com.knu.medifree.model.Reservation;
import com.knu.medifree.model.User;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static java.lang.Thread.sleep;

public class DBManager extends Thread {
    private static DateFormat dateformat;

    // Encapsulate target
    private static ArrayList<Reservation> reservations_list;
    private static ArrayList<Hospital> hospitals_list;
    private static ArrayList<String> major_list;
    private static ArrayList<Doctor> doctors_list;

    // User info
    private static String uid;
    private static int utype;

    public static void initDBManager(String uid, int utype) throws ParseException {
        dateformat = new SimpleDateFormat("yyyy/MM/dd/kk/mm");
        DBManager.uid = uid;
        DBManager.utype = utype;
    }

    public static void getDoctor(String Hospital_Name){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        doctors_list = new ArrayList<Doctor>();
        Log.d("TAG", "getDoctor: "+Hospital_Name);
        db.collection("Profile")
                .whereEqualTo("Hospital_Name",Hospital_Name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                               doctors_list.add(new Doctor(document.get("Major").toString(), document.get("name").toString(), document.get("phoneNum").toString()));
                            }
                            Collections.sort(doctors_list);
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
    public static ArrayList<Doctor> getDoctors_list(){
        return doctors_list;
    }
//    public static void getHospital(){
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        hospitals_list = new ArrayList<Hospital>();
//        db.collection("Hospital")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                hospitals_list.add(new Hospital(document.getId()));
//                            }
//                        } else {
//                            Log.d("TAG", "Error getting documents: ", task.getException());
//                        }
//                    }
//                });
//    }



    //
    // Reservation Control
    // Setter
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
                        Log.i("DBManager", documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("DBManager", "setReservation() error!", e);
                    }
                });
    }


    // Getter
    public static ArrayList<Reservation> getReservations() {
       return DBManager.reservations_list;
    }
    public static ArrayList<Hospital> getHospitals() {
        return DBManager.hospitals_list;
    }
    public static ArrayList<String> getMajors() {
        return DBManager.major_list;
    }

    // Activity Starter
    public static void startActivityWithReservationReading( Activity from, Intent to) {
        // Warning! You need not use this method!
        // Date control ("yyyy/MM/dd/kk/mm format")
        // Index : Date format

        if (DBManager.utype == User.TYPE_DOCTOR) {
            // Query Setting
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference resRef = db.collection("Reservation");
            Query resDataQuery = resRef.whereEqualTo("doctor_id", DBManager.uid);
            Log.i("HEESUNG", "Waiting DB Callback...");
            resDataQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        Log.i("HEESUNG", "Getting DB Callback...");
                        reservations_list = new ArrayList<Reservation>();
                        // Critical section control
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            reservations_list.add(new Reservation(
                                    document.getString("patient_id")
                                    , document.getString("doctor_id")
                                    , document.getString("date")
                                    , document.getBoolean("completed")
                                    , document.getId()
                            ));
                        }
                        Log.i("HEESUNG", "Reservation DB Updeate complete.");
                        from.startActivity(to);
                        from.finish();
                    }
                }
            });
        } else if (DBManager.utype == User.TYPE_PATIENT) {
            // Query Setting
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference resRef = db.collection("Reservation");
            Query resDataQuery = resRef.whereEqualTo("patient_id", DBManager.uid);
            Log.i("HEESUNG", "Waiting DB Callback...");
            resDataQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        Log.i("HEESUNG", "Getting DB Callback...");
                        reservations_list = new ArrayList<Reservation>();
                        // Critical section control
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            reservations_list.add(new Reservation(
                                    document.getString("patient_id")
                                    , document.getString("doctor_id")
                                    , document.getString("date")
                                    , document.getBoolean("completed")
                                    , document.getId()
                            ));
                        }
                        Log.i("HEESUNG", "Reservation DB Updeate complete.");
                        from.startActivity(to);
                        from.finish();
                    }
                }
            });
        } // End of if (type == User.TYPE_DOCTOR)
    } // End of method

    public static void startActivityWithHospitalReading(Activity from, Intent to) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = db.collection("Hospital");

        Log.i("HEESUNG", "Waiting DB Callback...");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.i("HEESUNG", "Getting DB Callback...");
                    hospitals_list = new ArrayList<Hospital>();

                    // Critical section control
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        hospitals_list.add(new Hospital(
                                document.getString("name")
                                , document.getId()
                        ));
                    }
                    Log.i("HEESUNG", "Reservation DB Updeate complete.");
                    from.startActivity(to);
                    from.finish();
                }
            }
        });
    }

    public static void startActivityWithMajorReading(String hospital_id, Activity from, Intent to) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = db.collection("Hospital/" + hospital_id + "/Major" );

        Log.i("HEESUNG", "Waiting DB Callback...");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.i("HEESUNG", "Getting DB Callback...");
                    major_list = new ArrayList<String>();

                    // Critical section control
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        major_list.add(new String(
                                document.getString("name")
                        ));
                    }
                    Log.i("HEESUNG", "Reservation DB Updeate complete.");
                    from.startActivity(to);
                    from.finish();
                }
            }
        });
    }

    public static void startActivityWithDoctorReading(String hospital_name, Activity from, Intent to) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = db.collection("Profile");
        Query query = collectionReference.whereEqualTo("Hospital_Name", hospital_name);

        Log.i("HEESUNG", "Waiting DB Callback...");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.i("HEESUNG", "Getting DB Callback...");
                    hospitals_list = new ArrayList<Hospital>();

                    // Critical section control
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        hospitals_list.add(new Hospital(
                                document.getString("name")
                                , document.getId()
                        ));

                    }
                    Log.i("HEESUNG", "Reservation DB Updeate complete.");
                    from.startActivity(to);
                    from.finish();
                }
            }
        });
    }
}
