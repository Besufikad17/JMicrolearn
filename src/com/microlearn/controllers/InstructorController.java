package com.microlearn.controllers;

import com.microlearn.models.Instructor;
import com.microlearn.utils.Queries;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

import static com.microlearn.utils.Utils.*;

public class InstructorController {
    public static Scanner input = new Scanner(System.in);

    public static void addInstructor(Connection connection){
        String name = input.next();
        String pwd = hash(input.next());
        String profilePictureUrl =  input.next();
        boolean isVerified = false;
        String details = "{}";

        Instructor instructor = new Instructor(name, profilePictureUrl, pwd, null, null);
        Statement statement;
        int resultSet = 0;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeUpdate(Queries.InstructorQueries.insert(instructor));
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

    public static void getAllInstructors(Connection connection){
        String query = Queries.InstructorQueries.get();
        runGetQuery(connection, query, "Instructor");
    }

    public static void getInstructorById(Connection connection){
        System.out.println("Enter user id");
        int id = input.nextInt();
        String query = Queries.InstructorQueries.get(id);
        runGetQuery(connection, query, "Instructor");
    }

    public static void getInstructorByFullName(Connection connection){
        System.out.println("Enter user full name");
        String fname = input.next();
        String query = Queries.InstructorQueries.get(fname);
        runGetQuery(connection, query, "Instructor");
    }

    public static void updateFullName(Connection connection){
        System.out.println("Enter new full name and id");
        String fname = input.next();
        int id = input.nextInt();
        String query = Queries.InstructorQueries.update(fname, id, "fname");
        runPostOrUpdateQuery(connection, query);
    }

    public static void updateProfilePictureUrl(Connection connection){
        System.out.println("Enter new profile picture url and id");
        String pfp = input.next();
        int id = input.nextInt();
        String query = Queries.InstructorQueries.update(pfp, id, "pfp");
        runPostOrUpdateQuery(connection, query);
    }

    public static void updatePassword(Connection connection){
        System.out.println("Enter new password and id");
        String pwd = hash(input.next());
        int id = input.nextInt();
        String query = Queries.InstructorQueries.update(pwd, id, "pwd");
        runPostOrUpdateQuery(connection, query);
    }

    public static void delete(Connection connection){
        System.out.println("Enter user id");
        int id = input.nextInt();
        String query = Queries.InstructorQueries.delete(id);
        runPostOrUpdateQuery(connection, query);
    }
}
