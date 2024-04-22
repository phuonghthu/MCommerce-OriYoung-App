package com.group6.models;

public class User {
    String userID;
    String userName;
    String phoneNumber;
    String email;
    String dob;

    public User() {

    }

    public User(String userName, String phoneNumber, String email, String dob) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dob = dob;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
