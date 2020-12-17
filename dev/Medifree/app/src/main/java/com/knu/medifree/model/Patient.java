package com.knu.medifree.model;

import android.util.Log;

import com.knu.medifree.util.DBManager;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Patient {
    private String name;
    private String time;


    public Patient(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getName(){
        return name;
    }

    public String getTime(){
        return time;
    }

    public static ArrayList getPatient() {
        ArrayList patient = new ArrayList();
        ArrayList<Reservation> list_reservations = DBManager.getReservations();

        for(int i=0;i<list_reservations.size();i++){
            if (list_reservations.get(i).isCompleted() == false) {
                patient.add(new Patient(
                        list_reservations.get(i).getPatient_name(),
                        list_reservations.get(i).getDate()
                ));
            }
        }

        return patient;
    }

    public static ArrayList getPatientFromTime(String time){
        ArrayList patient = new ArrayList();
        ArrayList<Reservation> list_reservations = DBManager.getReservations();

        String currentDate = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());

        Log.e("time : " , time);
        Log.e("current Date : ", currentDate);

        for(int i=0;i<list_reservations.size();i++){
            
        }
        return patient;
    }


}
