package com.microlearn.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Instructor{

    public String fullName;
    public String profilePictureUrl;
    public String joinedDate;
    public String password;
    public String givenCourses;
    public String balance;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();

    public String getFullName() {
        return fullName;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public String getJoinedDate() {
        return joinedDate;
    }

    public String getPassword() {
        return password;
    }

    public String getGivenCourses() {
        return givenCourses;
    }

    public String getBalance() {
        return balance;
    }

    public Instructor(String fullName, String profilePictureUrl, String password, String givenCourses, String balance) {
        this.fullName = fullName;
        this.profilePictureUrl = profilePictureUrl;
        this.password = password;
        this.givenCourses = givenCourses;
        this.balance = balance;
    }
}
