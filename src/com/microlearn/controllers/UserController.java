package com.microlearn.controllers;

import com.microlearn.models.User;
import com.microlearn.utils.Queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class UserController {

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

    public static void runGetQuery(Connection connection, String query){
        Statement statement;
        ResultSet resultSet;

        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            int count = 0;
            while(resultSet.next()){
                System.out.println("User :" + count + 1);
                System.out.println("Full name :" + resultSet.getObject("fullname"));
                System.out.println("Profile picture url :" + resultSet.getObject("profilepictureurl"));
                System.out.println("password :" + resultSet.getObject("password"));
                System.out.println();
                count ++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void addUser(Connection connection){
        String name = input.next();
        String pwd = input.next();
        String profilePictureUrl =  input.next();
        boolean isVerified = false;
        String details = "{}";

        User u = new User(name, profilePictureUrl, isVerified, pwd, details);
        boolean isDone = runPostOrUpdateQuery(connection, u) == 1;

        if (isDone){
            System.out.println("Done");
        }else{
            System.out.println("Smt is wrong, check ur inputs and try again!!");
        }
    }

    public static void getAllUsers(Connection connection){
        String query = Queries.UserQueries.get();
        runGetQuery(connection, query);
    }

    public static void getUserById(Connection connection){
        System.out.println("Enter user id");
        int id = input.nextInt();
        String query = Queries.UserQueries.get(id);
        runGetQuery(connection, query);
    }

    public static void getUserByFullName(Connection connection){
        System.out.println("Enter user full name");
        String fname = input.next();
        String query = Queries.UserQueries.get(fname);
        runGetQuery(connection, query);
    }

    public static void updateFullName(Connection connection){
        System.out.println("Enter new full name and id");
        String fname = input.next();
        int id = input.nextInt();
        String query = Queries.UserQueries.update(fname, id, "fname");
        runPostOrUpdateQuery(connection, query);
    }

    public static void updateProfilePictureUrl(Connection connection){
        System.out.println("Enter new profile picture url and id");
        String pfp = input.next();
        int id = input.nextInt();
        String query = Queries.UserQueries.update(pfp, id, "pfp");
        runPostOrUpdateQuery(connection, query);
    }

    public static void updatePassword(Connection connection){
        System.out.println("Enter new password and id");
        String pwd = input.next();
        int id = input.nextInt();
        String query = Queries.UserQueries.update(pwd, id, "pwd");
        runPostOrUpdateQuery(connection, query);
    }

    public static void delete(Connection connection){
        System.out.println("Enter user id");
        int id = input.nextInt();
        String query = Queries.UserQueries.delete(id);
        runPostOrUpdateQuery(connection, query);
    }


}
