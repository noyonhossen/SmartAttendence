package com.tanvirnoor.smartattendence;

public class StudentInfo {

    String id;
    String Name;
    String Date;



    String Time;
    String attendance;
    String latitude_txt;
    String longitude_txt;

    public StudentInfo(String id, String Name,String Date,String time, String attendance, String latitude_txt, String longitude_txt) {
        this.id = id;
        this.Name = Name;
        this.Date = Date;
        this.Time = time;
        this.attendance = attendance;
        this.latitude_txt = latitude_txt;
        this.longitude_txt = longitude_txt;
    }
    StudentInfo(){

    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getLatitude_txt() {
        return latitude_txt;
    }

    public void setLatitude_txt(String latitude_txt) {
        this.latitude_txt = latitude_txt;
    }

    public String getLongitude_txt() {
        return longitude_txt;
    }

    public void setLongitude_txt(String longitude_txt) {
        this.longitude_txt = longitude_txt;
    }
}
