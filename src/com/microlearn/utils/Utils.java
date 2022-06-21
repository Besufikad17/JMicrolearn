package com.microlearn.utils;

import com.microlearn.models.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Utils {

    public static Scanner input = new Scanner(System.in);

    public static int runPostOrUpdateQuery(Connection connection, String query) {
        Statement statement;
        int resultSet = 0;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static int runPostOrUpdateQuery(Connection connection, User u){
        Statement statement;
        int resultSet = 0;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeUpdate(Queries.UserQueries.insert(u));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void runGetQuery(Connection connection, String query, String type){
        Statement statement;
        ResultSet resultSet;

        String[] userAndInstructorAttrs = {"fullname", "profilepictureurl", "password"};
        String[] courseAttrs = {"title", "instructor", "estimatedtime", "publishedate", "coverpictureurl", "price"};

        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            int count = 0;

            if(type.equals("User") || type.equals("Instructor")){
                while(resultSet.next()){
                    System.out.println(type + ":" + count + 1);
                    System.out.println("Full name :" + resultSet.getObject("fullname"));
                    System.out.println("Profile picture url :" + resultSet.getObject("profilepictureurl"));
                    System.out.println("password :" + resultSet.getObject("password"));
                    System.out.println();
                    count ++;
                }
            }else{
                while(resultSet.next()){
                    System.out.println(type + ":" + count + 1);
                    System.out.println("Title :" + resultSet.getObject("title"));
                    System.out.println("Cover picture url :" + resultSet.getObject("coverpictureurl"));
                    System.out.println("Instructor :" + resultSet.getObject("instructor"));
                    System.out.println("Published date :" + resultSet.getObject("publishedate"));
                    System.out.println("Estimated hour :" + resultSet.getObject("estimatedtime"));
                    System.out.println("Price :" + resultSet.getObject("price"));
                    System.out.println();
                    count ++;
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void loadDriver(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static char printMainMenu(){
        char ch;
        System.out.println("________Welcome to MicroLearn Backend_______");
        System.out.println("1. User manager");
        System.out.println("2. Instructor manager");
        System.out.println("3. Course manager");
        System.out.println("4. Exit");
        ch = input.next().charAt(0);
        return ch;
    }

    public static char printUserMenu(){
        char ch;
        System.out.println("1. Add user");
        System.out.println("2. Get user");
        System.out.println("3. Update user data");
        System.out.println("4. Delete user");
        System.out.println("5. Main menu");
        ch = input.next().charAt(0);
        return ch;
    }

    public static char printInstructorMenu(){
        char ch;
        System.out.println("1. Add instructor");
        System.out.println("2. Get instructor");
        System.out.println("3. Update instructor data");
        System.out.println("4. Delete instructor");
        System.out.println("5. Main menu");
        ch = input.next().charAt(0);
        return ch;
    }

    public static char printCourseMenu(){
        char ch;
        System.out.println("1. Add course");
        System.out.println("2. Get courses");
        System.out.println("3. Update course data");
        System.out.println("4. Delete course");
        System.out.println("5. Main menu");
        ch = input.next().charAt(0);
        return ch;
    }

    public static String hash(String password){
        String encryptedpassword = null;

        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes());
            byte[] bytes = m.digest();

            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            encryptedpassword = s.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptedpassword;
    }
}
