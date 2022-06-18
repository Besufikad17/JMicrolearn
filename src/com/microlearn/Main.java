package com.microlearn;

import com.microlearn.controllers.UserController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {

    public static Scanner input = new Scanner(System.in);

    public static void loadDriver(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static char printMenu(){
        char ch;
        System.out.println("________Welcome to MicroLearn Backend_______");
        System.out.println("1. Add user");
        System.out.println("2. Get user");
        System.out.println("3. Update user data");
        System.out.println("4. Delete user");
        System.out.println("5. Exit");
        ch = input.next().charAt(0);
        return ch;
    }
    public static void main(String[] args) {

        String url = "jdbc:mysql://127.0.0.1:3306/microlearn";
        String username = "root";
        String password = "";
        Scanner input = new Scanner(System.in);

        loadDriver();

        try(Connection connection = DriverManager.getConnection(url, username, password)){
            mainLoop : while(true){
                char choice = printMenu();
                switch (choice){
                    case '1':
                        UserController.addUser(connection);
                        break;
                    case '2':
                        while(true){
                            char ch;
                            System.out.println("1. All users");
                            System.out.println("2. By id");
                            System.out.println("3. By full name");
                            System.out.println("4. Main menu");
                            ch = input.next().charAt(0);
                            switch (ch){
                                case '1':
                                    UserController.getAllUsers(connection);
                                    break;
                                case '2':
                                    UserController.getUserById(connection);
                                    break;
                                case '3':
                                    UserController.getUserByFullName(connection);
                                    break;
                                case '4':
                                    continue mainLoop;
                                default:
                                    System.out.println("Invalid input!!");
                            }
                        }
                    case '3':
                        while(true){
                            char ch;
                            System.out.println("1. Change full name");
                            System.out.println("2. Change profile picture url");
                            System.out.println("3. Change password");
                            System.out.println("4. Main menu");
                            ch = input.next().charAt(0);
                            switch (ch){
                                case '1':
                                    UserController.updateFullName(connection);
                                    break;
                                case '2':
                                    UserController.updateProfilePictureUrl(connection);
                                    break;
                                case '3':
                                    UserController.updatePassword(connection);
                                    break;
                                case '4':
                                    continue mainLoop;
                                default:
                                    System.out.println("Invalid input!!");
                            }
                        }
                    case '4':
                        UserController.delete(connection);
                        continue mainLoop;
                    case '5':
                        System.exit(0);
                    default:
                        System.out.println("Invalid input!!");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
