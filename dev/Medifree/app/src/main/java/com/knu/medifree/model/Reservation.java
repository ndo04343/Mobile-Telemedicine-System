package com.knu.medifree.model;

import java.io.Serializable;

public class Reservation implements Serializable {
    /* firestore에 사용된 필드
       변수명 일치화 시키는 게 좋음
       Doctor_id, data, id, patient_id
     */
    private String patient_id;
    private String doctor_id;
    private String date;
    private boolean completed;
    private String id;

    // Constructor
    public Reservation(String patient_id, String Doctor_id, String date, Boolean completed) {
        this.doctor_id = Doctor_id;
        this.date = date;
        this.patient_id = patient_id;
        this.completed = completed;
    }
    public Reservation(String patient_id, String doctor_id, String date) {
        this.doctor_id = doctor_id;
        this.date = date;
        this.patient_id = patient_id;
        this.completed = false;
    }
    public Reservation(String patient_id, String Doctor_id, String date, String res_id) {
        this.doctor_id =Doctor_id;
        this.date = date;
        this.patient_id = patient_id;
        this.completed = false;
    }
    public Reservation(String patient_id, String Doctor_id, String date, Boolean completed, String res_id) {
        this.doctor_id = Doctor_id;
        this.date = date;
        this.patient_id = patient_id;
        this.completed = completed;
        this.id = res_id;
    }


    // Getter Setter
    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "doctor_id='" + doctor_id + '\'' +
                ", patient_id='" + patient_id + '\'' +
                ", date='" + date + '\'' +
                ", completed=" + completed +
                ", id='" + id + '\'' +
                '}';
    }
}
