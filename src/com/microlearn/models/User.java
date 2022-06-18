package com.microlearn.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {

    public String fullName;
    public String profilePictureUrl;
    public boolean isVerified;
    public String joinedDate;
    public String password;
    public String details;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();

    public User(String fullName, String profilePictureUrl, boolean isVerified, String password, String details) {
        this.fullName = fullName;
        this.profilePictureUrl = profilePictureUrl;
        this.isVerified = isVerified;
        this.joinedDate = dateFormat.format(date);
        this.password = password;
        this.details = details;
    }

    public String getFullName() {
        return fullName;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public String getJoinedDate() {
        return joinedDate;
    }

    public String getPassword() {
        return password;
    }

    public String getDetails() {
        return details;
    }
}
