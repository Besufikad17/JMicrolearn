package com.microlearn.utils;

import com.microlearn.models.User;

import org.json.JSONObject;
import org.json.JSONException;

import java.io.*;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class Utils {

    public static Scanner input = new Scanner(System.in);
    public static String JSONURI =  "src/com/microlearn/models/json/users.json";

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

    public static String getCourses(int user_id, Connection connection){
        String query = Queries.UserQueries.get(user_id);

        String courses = null;
        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                courses = String.valueOf(resultSet.getObject("courses"));
            }
            int count = 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return courses;
    }

    public static boolean isEnrolled(String[] courses, String course){
        for(String s: courses){
            if(s.equals(course)){
                return true;
            }
        }
        return false;
    }

    public static String updateCourses(String course_id, String courses){
        String[] cs =courses.split(",");

        if(!isEnrolled(cs, course_id)){
            courses = courses + "," + course_id;
        }else{
            return "course exists";
        }
        return courses;
    }

    public static void toJSON(User user){
        String path = "src/com/microlearn/models/json/users.json";

        JSONObject json = new JSONObject();
        try {
            json.put("Full name", user.fullName);
            json.put("Password", user.password);
            json.put("Profile picture url", user.profilePictureUrl);
            json.put("Balance", user.balance);
            json.put("Enrolled courses", user.enrolledCourses.split(","));
            json.put("Role", "user");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            out.write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void flush(String path){
        File file = new File(Paths.get(path).toString());
        file.setWritable(true);
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(Paths.get(path).toString()));
            bufferedOutputStream.write("{}".getBytes());
            bufferedOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateJSONData(String newData, String dataType){
        JSONObject json = null;
        try {
            json = new JSONObject("src/com/microlearn/models/json/users.json");
            json.remove(dataType);
            json.put(dataType, newData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileWriter("src/com/microlearn/models/json/users.json"))) {
            out.write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printJSONData(){
        JSONObject json = null;
        try {
            json = new JSONObject(JSONURI);
            System.out.println("Full name :" + json.get("Full name"));
            System.out.println("Profile picture url :" + json.get("Profile picture url"));
            System.out.println("Balance :" + json.get("Balance"));
            System.out.println("Enrolled courses :" + json.get("Enrolled courses"));
            System.out.println("Password :" + json.get("Password"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
