package com.microlearn.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {

    public String fullName;
    public String profilePictureUrl;
    public String joinedDate;
    public String password;
    public String enrolledCourses;
    public String balance;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();

    public User(String fullName, String profilePictureUrl, String password, String enrolledCourses, String balance) {
        this.fullName = fullName;
        this.profilePictureUrl = profilePictureUrl;
        this.joinedDate = dateFormat.format(date);
        this.password = password;
        this.enrolledCourses = enrolledCourses;
        this.balance = balance;
    }

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

    public String getEnrolledCourses() {
        return enrolledCourses;
    }

    public String getBalance() {
        return balance;
    }
}

