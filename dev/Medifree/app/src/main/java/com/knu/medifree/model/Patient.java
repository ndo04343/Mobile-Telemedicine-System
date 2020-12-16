package com.knu.medifree.model;

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
        patient.add(new Patient("Harry", "10/20 15:00"));
        patient.add(new Patient("Marla", "10/21 14:00"));
        patient.add(new Patient("Sarah", "10/21 15:00"));
        return patient;
    }
}
