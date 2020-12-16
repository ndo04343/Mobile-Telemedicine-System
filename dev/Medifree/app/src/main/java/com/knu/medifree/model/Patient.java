package com.knu.medifree.model;

import com.knu.medifree.util.DBManager;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
            patient.add(new Patient(
                    list_reservations.get(i).getPatient_name(),
                    list_reservations.get(i).getDate()
            ));
        }

        return patient;
    }


}
