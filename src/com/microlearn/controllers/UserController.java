package com.microlearn.controllers;

import com.microlearn.models.User;
import com.microlearn.utils.Queries;

import java.sql.Connection;
import java.util.Scanner;

import static com.microlearn.utils.Utils.*;

public class UserController {

    public static Scanner input = new Scanner(System.in);

    public static void addUser(Connection connection){
        String name = input.next();
        String pwd = hash(input.next());
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
        pwd = hash(pwd);
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
