package com.microlearn.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Course {

    public String title;
    public String instructor;
    public String estimatedTime;
    public String publishedDate;
    public String details;
    public String coverPictureUrl;
    public String price;

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();

    public Course(String title, String instructor, String coverPictureUrl, String estimatedTime, String details, String price) {
        this.title = title;
        this.instructor = instructor;
        this.coverPictureUrl = coverPictureUrl;
        this.estimatedTime = estimatedTime;
        this.publishedDate = dateFormat.format(date);
        this.details = details;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getDetails() {
        return details;
    }

    public String getCoverPictureUrl() {
        return coverPictureUrl;
    }

    public String getPrice() {
        return price;
    }
}
