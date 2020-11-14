package com.knu.medifree.reservation;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation {
    /* firestore에 사용된 필드
       변수명 일치화 시키는 게 좋음
       Doctor_id, data, id, patient_id
     */
    @PropertyName("Doctor_id")
    private String doc_name;

    @PropertyName("date")
    private LocalDateTime date;
    @PropertyName("id")
    private int id;
    @PropertyName("patient_id")
    private String pat_name;

    private String hos_name;
    private int year, month, day, hour;
    private int order;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Reservation(String doctor_id, LocalDateTime day, int id, String patient_id) {
        this.doc_name =doctor_id;
        this.month = day.getDayOfMonth();
        this.year = day.getDayOfYear();
        this.pat_name = patient_id;
    }


    // Getter Method
    public String getHos_name() {
        return hos_name;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public String getPat_name() {
        return pat_name;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getOrder() {
        return order;
    }

    // Setter Method

    public void setHos_name(String hos_name) {
        this.hos_name = hos_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public void setPat_name(String pat_name) {
        this.pat_name = pat_name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
