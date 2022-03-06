package com.example.sharedmeal.Donor;

import java.util.List;

public class User {
    //for the user data to store in a database
    private String name;
    private String email;
    private int uNumber;


    private String recentDonation;

    public User() {
        // for not showing error
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getUnumber() {
        return uNumber;
    }

    public void setRecentDonation(String recentDonation) {
        this.recentDonation = recentDonation;
    }

    public String getRecentDonation() {
        return recentDonation;
    }

    public User(String name, String email, String recentDonation) {
        this.name = name;
        this.email = email;
        this.recentDonation = recentDonation;
        uNumber++;
    }
}
