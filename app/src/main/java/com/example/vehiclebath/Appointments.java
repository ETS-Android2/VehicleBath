package com.example.vehiclebath;

public class Appointments {
    private String C_Name;
    private String CarWashType;
    private String Date;
    private String Time;


    public Appointments(){

    }

    public Appointments(String c_Name, String carWashType, String date, String time) {
        C_Name = c_Name;
        CarWashType = carWashType;
        Date = date;
        Time = time;
    }

    public String getC_Name() {
        return C_Name;
    }

    public void setC_Name(String c_Name) {
        C_Name = c_Name;
    }

    public String getCarWashType() {
        return CarWashType;
    }

    public void setCarWashType(String carWashType) {
        CarWashType = carWashType;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
