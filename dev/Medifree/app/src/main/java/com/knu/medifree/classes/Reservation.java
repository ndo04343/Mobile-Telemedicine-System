package com.knu.medifree.classes;

import java.io.Serializable;

public class Reservation implements Serializable {
    /* firestore에 사용된 필드
       변수명 일치화 시키는 게 좋음
       Doctor_id, data, id, patient_id
     */
    private String doctor_id;
    private String patient_id;
    private String date;
    private String id;

    public Reservation(String patient_id, String Doctor_id, String date) {
        this.doctor_id =Doctor_id;
        this.date = date;
        this.patient_id = patient_id;
    }
    public Reservation(String patient_id, String Doctor_id, String date, String res_id) {
        this.doctor_id =Doctor_id;
        this.date = date;
        this.patient_id = patient_id;
    }


    // Getter Method

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }


    public String toString() {
        return this.getPatient_id() + this.getDoctor_id() + this.getDate();
    }
}
