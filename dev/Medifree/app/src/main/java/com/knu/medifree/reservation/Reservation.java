package com.knu.medifree.reservation;

public class Reservation {
    private String hos_name;

    private String doc_name;
    private String pat_name;

    private int year, month, date, hour;
    private int order;



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

    public int getDate() {
        return date;
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

    public void setDate(int date) {
        this.date = date;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
