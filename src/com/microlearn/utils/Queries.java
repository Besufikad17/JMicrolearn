package com.microlearn.utils;

import com.microlearn.models.User;

public class Queries {

    public static class UserQueries {

        /** @POST
         * Method that returns the corresponding query to add new user to the database
         * */
        public static String insert(User user){
            String flag = "INSERT INTO `users` (`user_id`, `fullname`, `profilepictureurl`, `joineddate`, `isverified`, `password`, `details`) VALUES ";

            return flag + "(NULL, \"" + user.getFullName() + "\",\"" + user.getProfilePictureUrl() + "\",\"" +
                    user.getJoinedDate() + "\"," + "\"false\""+ ",\"" +
                    user.getPassword() + "\",NULL);";
        }

        /** @GET
         * Method that returns the corresponding query to get all users from the database
         * */
        public static String get(){
            return "SELECT * FROM `users`";
        }

        /** @GET
         * Method that returns the corresponding query to get user by id from the database
         * */
        public static String get(int id){
            return "SELECT * FROM `users` WHERE user_id=" + id + ";";
        }

        /** @GET
         * Method that returns the corresponding query to get user by first name from the database
         * */
        public static String get(String fname){
            return "SELECT * FROM `users` WHERE fullname=\"" + fname + "\"";
        }

        /** @UPDATE
         * Method that returns the corresponding query to update full name, profile picture url and password of user from the database
         * */
        public static String update(String newData, int id, String flag){
            if(flag.equals("fname")){
                return "UPDATE `users` SET fullname=\"" + newData + "\"" + "WHERE user_id=" + id + ";";
            }else if(flag.equals("ppf")){
                return "UPDATE `users` SET profilepictureurl=\"" + newData + "\"" + "WHERE user_id=" + id + ";";
            }else if(flag.equals("pwd")){
                return "UPDATE `users` SET password=\"" + newData + "\"" + "WHERE user_id=" + id + ";";
            }else{
                return "-1";
            }
        }

    }
}
