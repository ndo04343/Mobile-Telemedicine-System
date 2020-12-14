package com.knu.medifree.classes;

public class Doctor  implements Comparable<Doctor>{
    private String major;
    private String name;
    private String phoneNum;

    public Doctor(String major, String name, String phoneNum) {
        this.major = major;
        this.name = name;
        this.phoneNum = phoneNum;
    }



    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public int compareTo(Doctor o) {
        return this.major.compareTo(o.major);
    }
}
