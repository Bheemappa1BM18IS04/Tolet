package com.example.tolet;

public class card {
    String cardnumber,month,cvv;

    public card(String cardnumber, String month, String cvv) {
        this.cardnumber = cardnumber;
        this.month = month;
        this.cvv = cvv;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public String getMonth() {
        return month;
    }

    public String getCvv() {
        return cvv;
    }

}
