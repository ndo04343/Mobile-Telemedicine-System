package com.knu.medifree.model;

public class Hospital {
    private String hospitalName;
    private String id;

    public Hospital(String hospitalName, String id){
        this.hospitalName = hospitalName;
        this.id = id;
    }
    public String getHospitalName(){
        return hospitalName;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "hospitalName='" + hospitalName + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
