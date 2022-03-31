package com.example.sharedmeal.Donor;


public class User {
    //for the user data to store in a database
    private String name;
    private String email;

    public User() {
        // for not showing error
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
