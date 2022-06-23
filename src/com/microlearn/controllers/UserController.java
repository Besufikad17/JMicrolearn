package com.microlearn.controllers;

import com.microlearn.models.User;
import com.microlearn.utils.Queries;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import static com.microlearn.utils.Utils.*;

public class UserController {

    public static Scanner input = new Scanner(System.in);

    public static String login(Connection connection){
        System.out.println("Full name: ");
        String fname = input.next();

        System.out.println("Password: ");
        String pwd = hash(input.next());

        Statement statement;
        ResultSet resultSet;

        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM `users` WHERE fullname= \"" + fname + "\";");
            while(resultSet.next()){
                if(resultSet.getObject("password").toString().equals(pwd)){

                    String balance = resultSet.getObject("balance") == null ? "" : (String) resultSet.getObject("balance");
                    String ec = resultSet.getObject("enrolledcourses") == null ? "" : (String) resultSet.getObject("enrolledcourses");

                    User user = new User(fname, (String) resultSet.getObject("profilepictureurl"), pwd, ec , balance);
                    toJSON(user);
                    return fname;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "-1";
    }

    public static void addUser(Connection connection){
        String name = input.next();
        String pwd = hash(input.next());
        String profilePictureUrl =  input.next();
        String enrolledCourses = "";
        String balance = "";

        User u = new User(name, profilePictureUrl, pwd, enrolledCourses, balance);
        toJSON(u);
        boolean isDone = runPostOrUpdateQuery(connection, u) == 1;

        if (isDone){
            System.out.println("Done");
        }else{
            System.out.println("Smt is wrong, check ur inputs and try again!!");
        }
    }

    public static void getAllUsers(Connection connection){
        String query = Queries.UserQueries.get();
        runGetQuery(connection, query, "User");
    }

    public static void getUserById(Connection connection){
        System.out.println("Enter user id");
        int id = input.nextInt();
        String query = Queries.UserQueries.get(id);
        runGetQuery(connection, query, "User");
    }

    public static void getUserByFullName(Connection connection){
        System.out.println("Enter user full name");
        String fname = input.next();
        String query = Queries.UserQueries.get(fname);
        runGetQuery(connection, query, "User");
    }

    public static void updateFullName(Connection connection){
        System.out.println("Enter new full name and id");
        String fname = input.next();
        int id = input.nextInt();
        String query = Queries.UserQueries.update(fname, id, "fname");
        runPostOrUpdateQuery(connection, query);
        updateJSONData(fname, "Full name");
    }

    public static void updateProfilePictureUrl(Connection connection){
        System.out.println("Enter new profile picture url and id");
        String pfp = input.next();
        int id = input.nextInt();
        String query = Queries.UserQueries.update(pfp, id, "pfp");
        runPostOrUpdateQuery(connection, query);
        updateJSONData(pfp, "Profile picture url");
    }

    public static void updatePassword(Connection connection){
        System.out.println("Enter new password and id");
        String pwd = input.next();
        int id = input.nextInt();
        pwd = hash(pwd);
        String query = Queries.UserQueries.update(pwd, id, "pwd");
        runPostOrUpdateQuery(connection, query);
        updateJSONData(pwd, "Password");
    }

    public static void delete(Connection connection){
        System.out.println("Enter user id");
        int id = input.nextInt();
        String query = Queries.UserQueries.delete(id);
        runPostOrUpdateQuery(connection, query);
    }


}
