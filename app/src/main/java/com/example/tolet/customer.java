package com.example.tolet;

public class customer {
    String lname,fname,mobile,password,mail;

    public customer(String lname, String fname, String mobile, String password, String mail) {
        this.lname = lname;
        this.fname = fname;
        this.mobile = mobile;
        this.password = password;
        this.mail = mail;
    }

    public String getLname() {
        return lname;
    }

    public String getFname() {
        return fname;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }
}
