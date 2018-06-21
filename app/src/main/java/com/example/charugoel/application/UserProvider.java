package com.example.charugoel.application;

/**
 * Created by Charu Goel on 21-06-2018.
 */

public class UserProvider {


    private String username, first_name, last_name;

    public UserProvider(String username, String first_name, String last_name){

        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
}
