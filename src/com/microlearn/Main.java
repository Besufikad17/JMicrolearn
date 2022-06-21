package com.microlearn;

import com.microlearn.controllers.CourseController;
import com.microlearn.controllers.InstructorController;
import com.microlearn.controllers.UserController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.Scanner;

import static com.microlearn.utils.Utils.*;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:mysql://127.0.0.1:3306/microlearn";

        String hostedPassword = "Bsk5&fQTlrib%#S";
        String username = "root";
        String password = "";
        Scanner input = new Scanner(System.in);

        loadDriver();

        try(Connection connection = DriverManager.getConnection(url, username, password)){
            mainLoop : while(true){
                char choice = printMainMenu();
                switch (choice){
                    case '1':
                        char userChoice = printUserMenu();
                        switch (userChoice){
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
                        break;
                    case '2':
                        char instructorChoice = printInstructorMenu();
                        switch (instructorChoice){
                            case '1':
                                InstructorController.addInstructor(connection);
                                break;
                            case '2':
                                while(true){
                                    char ch;
                                    System.out.println("1. All instructors");
                                    System.out.println("2. By id");
                                    System.out.println("3. By full name");
                                    System.out.println("4. Main menu");
                                    ch = input.next().charAt(0);
                                    switch (ch){
                                        case '1':
                                            InstructorController.getAllInstructors(connection);
                                            break;
                                        case '2':
                                            InstructorController.getInstructorById(connection);
                                            break;
                                        case '3':
                                            InstructorController.getInstructorByFullName(connection);
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
                                            InstructorController.updateFullName(connection);
                                            break;
                                        case '2':
                                            InstructorController.updateProfilePictureUrl(connection);
                                            break;
                                        case '3':
                                            InstructorController.updatePassword(connection);
                                            break;
                                        case '4':
                                            continue mainLoop;
                                        default:
                                            System.out.println("Invalid input!!");
                                    }
                                }
                            case '4':
                                InstructorController.delete(connection);
                                continue mainLoop;
                            case '5':
                                System.exit(0);
                            default:
                                System.out.println("Invalid input!!");
                        }
                        break;
                    case '3':
                        char courseChoice = printCourseMenu();
                        switch (courseChoice){
                            case '1':
                                CourseController.addCourse(connection);
                                break;
                            case '2':
                                while(true){
                                    char ch;
                                    System.out.println("1. All Courses");
                                    System.out.println("2. By id");
                                    System.out.println("3. By title");
                                    System.out.println("4. Main menu");
                                    ch = input.next().charAt(0);
                                    switch (ch){
                                        case '1':
                                            CourseController.getAllCourses(connection);
                                            break;
                                        case '2':
                                            CourseController.getCourseById(connection);
                                            break;
                                        case '3':
                                            CourseController.getCourseByTitle(connection);
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
                                    System.out.println("1. Change title");
                                    System.out.println("2. Change cover picture url");
                                    System.out.println("3. Change duration");
                                    System.out.println("4. Change price");
                                    System.out.println("5. Main menu");
                                    ch = input.next().charAt(0);
                                    switch (ch){
                                        case '1':
                                            CourseController.updateCourseTitle(connection);
                                            break;
                                        case '2':
                                            CourseController.updateCourseCoverPictureUrl(connection);
                                            break;
                                        case '3':
                                            CourseController.updateCourseDuration(connection);
                                            break;
                                        case '4':
                                            CourseController.updateCoursePrice(connection);
                                            break;
                                        case '5':
                                            continue mainLoop;
                                        default:
                                            System.out.println("Invalid input!!");
                                    }
                                }
                            case '4':
                                CourseController.deleteCourse(connection);
                                break;
                            case '5':
                                continue mainLoop;
                            default:
                                System.out.println("Invalid input!!");
                        }
                        break;
                    case '4':
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
