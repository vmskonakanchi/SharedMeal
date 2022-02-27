package com.example.sharedmeal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
    }

    public void Donate(View view) {
        //called when user clicks the donor button
        Intent donateIntent = new Intent(this, LoginActivity.class);
        startActivity(donateIntent);
    }

    public void Ride(View view) {
        //called when user clicks on the Rider button
    }
}