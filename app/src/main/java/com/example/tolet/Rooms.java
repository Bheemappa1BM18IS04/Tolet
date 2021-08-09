package com.example.tolet;

public class Rooms {
     String PatientId,Patientname,Age,AdimeteDate,Blood_pressuer,sugar_level,Current_status,rating;

    public Rooms(String patientId, String patientname, String age, String adimeteDate, String blood_pressuer, String sugar_level, String current_status, String rating) {
        PatientId = patientId;
        Patientname = patientname;
        Age = age;
        AdimeteDate = adimeteDate;
        Blood_pressuer = blood_pressuer;
        this.sugar_level = sugar_level;
        Current_status = current_status;
        this.rating = rating;
    }

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String patientId) {
        PatientId = patientId;
    }

    public String getPatientname() {
        return Patientname;
    }

    public void setPatientname(String patientname) {
        Patientname = patientname;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getAdimeteDate() {
        return AdimeteDate;
    }

    public void setAdimeteDate(String adimeteDate) {
        AdimeteDate = adimeteDate;
    }

    public String getBlood_pressuer() {
        return Blood_pressuer;
    }

    public void setBlood_pressuer(String blood_pressuer) {
        Blood_pressuer = blood_pressuer;
    }

    public String getSugar_level() {
        return sugar_level;
    }

    public void setSugar_level(String sugar_level) {
        this.sugar_level = sugar_level;
    }

    public String getCurrent_status() {
        return Current_status;
    }

    public void setCurrent_status(String current_status) {
        Current_status = current_status;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
