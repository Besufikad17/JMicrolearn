package com.microlearn;

import com.microlearn.controllers.UserController;
import com.microlearn.utils.Utils;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import static com.microlearn.utils.Utils.*;

public class View {

    static Scanner in = new Scanner(System.in);

    public static char printMenu(){
        char ch;
        System.out.println("____Welcome to JMicrolearn____");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        ch = in.next().charAt(0);
        return ch;
    }

    public static char printSubMenu(){
        char ch;
        System.out.println("1. User");
        System.out.println("2. Instructor");
        System.out.println("3. Admin");
        System.out.println("4. Main menu");
        ch = in.next().charAt(0);
        return ch;
    }

    public static char printUserMenu(){
        char ch;
        System.out.println("1. Enroll course");
        System.out.println("2. Update profile");
        System.out.println("3. View profile");
        System.out.println("4. Drop courses");
        System.out.println("5. Logout");
        System.out.println("6. Main menu");
        ch = in.next().charAt(0);
        return ch;
    }

    public static void main(String[] args) {

        String url = "jdbc:mysql://127.0.0.1:3306/microlearn";
        String hostedPassword = "Bsk5&fQTlrib%#S";
        String username = "root";
        String password = "";
        loadDriver();

        try(Connection connection = DriverManager.getConnection(url, username, password)){

            mainLoop: while(true){

                // Getting the state
                JSONObject users = new JSONObject("/home/besufikad/IdeaProjects/SQLConnectionDemo/src/com/microlearn/models/json/users.json");

                // If there is no user/admin/instructor
                if(users.length() == 0){

                    while (true){
                        char choice = printMenu();
                        switch (choice){

                            // Login
                            case '1':
                                innerLogin: while (true){
                                    char ch = printSubMenu();
                                    switch (ch){

                                        // User Login
                                        case '1':
                                            if( UserController.login(connection).equals("-1")){
                                                System.out.println("Invalid credentials!!");
                                            }else{
                                               continue mainLoop;
                                            }
                                            break;

                                            // Instructor Login
                                        case '2':
                                            break;

                                        // Instructor Login
                                        case '3':
                                            break;

                                            // Authentication menu
                                        case '4':
                                            break innerLogin;
                                        default:
                                            System.out.println("Invalid input!");
                                    }
                                }
                                break;
                            case '2':
                                innerRegister: while (true){
                                    char ch = printSubMenu();
                                    switch (ch){

                                        // User Registration
                                        case '1':
                                            if( UserController.login(connection).equals("-1")){
                                                System.out.println("Invalid credentials!!");
                                            }else{
                                                continue mainLoop;
                                            }
                                            break;

                                        // Instructor Registration
                                        case '2':
                                            break;

                                        // Admin Registration
                                        case '3':
                                            break;

                                        // Authentication menu
                                        case '4':
                                            break innerRegister;
                                        default:
                                            System.out.println("Invalid input!");
                                    }
                                }
                                break;

                            // Exit
                            case '3':
                                break mainLoop;
                            default:
                                System.out.println("Invalid input!");
                        }
                    }
                }else{

                    // User menu
                    if(users.get("Role").toString().equals("user")){
                        String fname = UserController.login(connection);
                        System.out.println("Welcome " + fname);
                        user : while(true){
                            char c = printUserMenu();
                            switch (c){

                                // Enroll course
                                case '1':
                                    break;

                                // Update profile
                                case '2':
                                    userLoop: while(true){
                                        char ch2;
                                        System.out.println("1. Change full name");
                                        System.out.println("2. Change profile picture url");
                                        System.out.println("3. Change password");
                                        System.out.println("4. Main menu");
                                        ch2 = in.next().charAt(0);
                                        switch (ch2){
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
                                                break userLoop;
                                            default:
                                                System.out.println("Invalid input!!");
                                        }
                                    }
                                    break;

                                // View profile
                                case '3':
                                    printJSONData();
                                    break;

                                // Drop course
                                case '4':
                                    break;

                                // Logout
                                case '5':
                                    flush("src/com/microlearn/models/json/users.json");
                                    break user;

                                // Main menu
                                case '6':
                                    break user;
                                default:
                                    System.out.println("Invalid input!");
                            }
                        }
                    }else{
                        // Admin menu
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
