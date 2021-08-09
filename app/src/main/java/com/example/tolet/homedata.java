package com.example.tolet;

public class homedata {
    String fname,lname,mobile,mail,password;

    public homedata(){

    }

    public homedata(String fname, String lname, String mobile, String mail, String password) {
        this.fname = fname;
        this.lname = lname;
        this.mobile = mobile;
        this.mail = mail;
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNail() {
        return mail;
    }

    public void setNail(String nail) {
        this.mail = nail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
