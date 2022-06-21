package com.microlearn.controllers;

import com.microlearn.models.Course;
import com.microlearn.utils.Queries;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;

import static com.microlearn.utils.Utils.runGetQuery;
import static com.microlearn.utils.Utils.runPostOrUpdateQuery;

public class CourseController {

    public static Scanner input = new Scanner(System.in);

    public static void addCourse(Connection connection){
        System.out.println("Title : ");
        String title = input.next();

        System.out.println("Instructor : ");
        String instructor = input.next();

        System.out.println("Cover picture link : ");
        String coverPictureUrl = input.next();

        System.out.println("Price : ");
        String price =  input.next();

        System.out.println("Duration : ");
        String duration = input.next();

        boolean isVerified = false;
        String details = "{}";

        Course course = new Course(title, instructor, coverPictureUrl, duration, details, price);
        System.out.println(Queries.CourseQueries.insert(course));

        Statement statement;
        int resultSet = 0;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeUpdate(Queries.CourseQueries.insert(course));

            boolean isDone = resultSet == 1;

            if (isDone){
                System.out.println("Done");
            }else{
                System.out.println("Smt is wrong, check ur inputs and try again!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getAllCourses(Connection connection){
        String query = Queries.CourseQueries.get();
        runGetQuery(connection, query,  "Course");
    }

    public static void getCourseByTitle(Connection connection){
        System.out.println("Enter title of the course");
        String title = input.next();
        String query = Queries.CourseQueries.get(title);
        runGetQuery(connection, query, "Course");
    }

    public static void getCourseById(Connection connection){
        System.out.println("Enter course id");
        int id = input.nextInt();
        String query = Queries.CourseQueries.get(id);
        runGetQuery(connection, query, "Course");
    }

    public static void updateCourseTitle(Connection connection){
        System.out.println("Enter title of the course");
        String title = input.next();
        int id = input.nextInt();
        String query = Queries.CourseQueries.update(title, id, "title");
        runGetQuery(connection, query, "Course");
    }

    public static void updateCourseCoverPictureUrl(Connection connection){
        System.out.println("Enter cover picture of the course");
        String coverUrl = input.next();
        int id = input.nextInt();
        String query = Queries.CourseQueries.update(coverUrl, id, "pfp");
        runGetQuery(connection, query, "Course");
    }

    public static void updateCoursePrice(Connection connection){
        System.out.println("Enter price of the course");
        String price = input.next();
        int id = input.nextInt();
        String query = Queries.CourseQueries.update(price, id, "$");
        runGetQuery(connection, query, "Course");
    }

    public static void updateCourseDuration(Connection connection){
        System.out.println("Enter duration of the course");
        String duration = input.next();
        int id = input.nextInt();
        String query = Queries.CourseQueries.update(duration, id, "duration");
        runGetQuery(connection, query, "Course");
    }

    public static void deleteCourse(Connection connection){
        System.out.println("Enter course id");
        int id = input.nextInt();
        String query = Queries.CourseQueries.delete(id);
        runPostOrUpdateQuery(connection, query);
    }
}
