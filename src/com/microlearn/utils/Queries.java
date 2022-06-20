package com.microlearn.utils;

import com.microlearn.models.Course;
import com.microlearn.models.Instructor;
import com.microlearn.models.User;

public class Queries {

    public static class UserQueries {

        /**
         * @param user object
         * @return the corresponding query to add new user to the database
         * */
        public static String insert(User user){
            String flag = "INSERT INTO `users` (`user_id`, `fullname`, `profilepictureurl`, `joineddate`, `isverified`, `password`, `details`) VALUES ";

            return flag + "(NULL, \"" + user.getFullName() + "\",\"" + user.getProfilePictureUrl() + "\",\"" +
                    user.getJoinedDate() + "\"," + "\"false\""+ ",\"" +
                    user.getPassword() + "\",NULL);";
        }

        /**
         *@return the corresponding query to get all users from the database
         * */
        public static String get(){
            return "SELECT * FROM `users`";
        }


        /**
         * @param id: user id
         * @return the corresponding query to get user by id from the database
         * */

        public static String get(int id){
            return "SELECT * FROM `users` WHERE user_id=" + id + ";";
        }

        /**
         * @param fname: user full name
         * @return the corresponding query to get user by full name from the database
         * */
        public static String get(String fname){
            return "SELECT * FROM `users` WHERE fullname=\"" + fname + "\"";
        }

        /**
         * @param newData: new user data, id: user id, flag: update flag [fname, pfp, pwd]
         * @return the corresponding query to update user data in the database
         * */
        public static String update(String newData, int id, String flag){
            if(flag.equals("fname")){
                return "UPDATE `users` SET fullname=\"" + newData + "\"" + "WHERE user_id=" + id + ";";
            }else if(flag.equals("pfp")){
                return "UPDATE `users` SET profilepictureurl=\"" + newData + "\"" + "WHERE user_id=" + id + ";";
            }else if(flag.equals("pwd")){
                return "UPDATE `users` SET password=\"" + newData + "\"" + "WHERE user_id=" + id + ";";
            }else{
                return "-1";
            }
        }

        /**
         * @param id: user id
         * @return the corresponding query to delete user from the database using user id
         * */
        public static String delete(int id){
            return "DELETE FROM `users` WHERE user_id=" + id + ";";
        }

    }

    public static class InstructorQueries {

        /**
         * @param instructor object
         * @return the corresponding query to add new user to the database
         * */
        public static String insert(Instructor instructor){
            String flag = "INSERT INTO `instructor` (`instructor_id`, `fullname`, `profilepictureurl`, `joineddate`, `isverified`, `password`, `details`) VALUES ";

            return flag + "(NULL, \"" + instructor.getFullName() + "\",\"" + instructor.getProfilePictureUrl() + "\",\"" +
                    instructor.getJoinedDate() + "\"," + "\"false\""+ ",\"" +
                    instructor.getPassword() + "\",NULL);";
        }

        /**
         *@return the corresponding query to get all users from the database
         * */
        public static String get(){
            return "SELECT * FROM `instructor`";
        }


        /**
         * @param id: instructor id
         * @return the corresponding query to get user by id from the database
         * */

        public static String get(int id){
            return "SELECT * FROM `instructor` WHERE instructor_id=" + id + ";";
        }

        /**
         * @param fname: instructor full name
         * @return the corresponding query to get user by full name from the database
         * */
        public static String get(String fname){
            return "SELECT * FROM `instructor` WHERE fullname=\"" + fname + "\"";
        }

        /**
         * @param newData: new instructor data, id: instructor id, flag: update flag [fname, pfp, pwd]
         * @return the corresponding query to update instructor data in the database
         * */
        public static String update(String newData, int id, String flag){
            if(flag.equals("fname")){
                return "UPDATE `instructor` SET fullname=\"" + newData + "\"" + "WHERE instructor_id=" + id + ";";
            }else if(flag.equals("pfp")){
                return "UPDATE `instructor` SET profilepictureurl=\"" + newData + "\"" + "WHERE instructor_id=" + id + ";";
            }else if(flag.equals("pwd")){
                return "UPDATE `instructor` SET password=\"" + newData + "\"" + "WHERE instructor_id=" + id + ";";
            }else{
                return "-1";
            }
        }

        /**
         * @param id: instructor id
         * @return the corresponding query to delete user from the database using user id
         * */
        public static String delete(int id){
            return "DELETE FROM `instructor` WHERE instructor_id=" + id + ";";
        }

    }

    public static class CourseQueries {
        /**
         * @param course object
         * @return the corresponding query to add new course to the database
         * */
        public static String insert(Course course){
            String flag = "INSERT INTO `courses` (`course_id`, `title`, `publishedate`, `instructor`, `estimatedtime`, `details`, `coverpictureurl`, `price`) VALUES ";

            return flag + "(NULL, \"" + course.getTitle() + "\",\"" + course.getPublishedDate() + "\",\"" +
                    course.getInstructor() + "\"," + "\"false\""+ ",\"" +
                    course.getEstimatedTime() + "\",NULL\"," + course.getCoverPictureUrl() + "\",\"" + course.getPrice()  +");";
        }

        /**
         *@return the corresponding query to get all courses from the database
         * */
        public static String get(){
            return "SELECT * FROM `courses`";
        }


        /**
         * @param id: course id
         * @return the corresponding query to get course by id from the database
         * */

        public static String get(int id){
            return "SELECT * FROM `courses` WHERE user_id=" + id + ";";
        }

        /**
         * @param title: course title
         * @return the corresponding query to get course by title from the database
         * */
        public static String get(String title){
            return "SELECT * FROM `courses` WHERE title=\"" + title + "\"";
        }

        /**
         * @param newData: new course data, id: course id, flag: update flag [title, pfp, price, duration]
         * @return the corresponding query to update user data in the database
         * */
        public static String update(String newData, int id, String flag){
            if(flag.equals("title")){
                return "UPDATE `courses` SET title=\"" + newData + "\"" + "WHERE course_id=" + id + ";";
            }else if(flag.equals("pfp")){
                return "UPDATE `courses` SET coverpictureurl=\"" + newData + "\"" + "WHERE course_id=" + id + ";";
            }else if(flag.equals("$")){
                return "UPDATE `courses` SET price=\"" + newData + "\"" + "WHERE course_id=" + id + ";";
            }else if(flag.equals("duration")){
                return "UPDATE `courses` SET estimatedtime=\"" + newData + "\"" + "WHERE course_id=" + id + ";";
            }
            else{
                return "-1";
            }
        }

        /**
         * @param id: course id
         * @return the corresponding query to delete course from the database using course id
         * */
        public static String delete(int id){
            return "DELETE FROM `courses` WHERE course_id=" + id + ";";
        }
    }
}
