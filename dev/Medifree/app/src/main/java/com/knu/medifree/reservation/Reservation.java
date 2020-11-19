package com.knu.medifree.reservation;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation implements Serializable {
    /* firestore에 사용된 필드
       변수명 일치화 시키는 게 좋음
       Doctor_id, data, id, patient_id
     */
    private String Doctor_id;
    private String date;
    private int id;
    private String patient_id;

    public Reservation(){}
    public Reservation(String Doctor_id, String date, int id, String patient_id) {
        this.Doctor_id =Doctor_id;
        this.date = date;
        this.id = id;
        this.patient_id = patient_id;
    }


    // Getter Method

    public String getDoctor_id() {
        return Doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        Doctor_id = doctor_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }


}
