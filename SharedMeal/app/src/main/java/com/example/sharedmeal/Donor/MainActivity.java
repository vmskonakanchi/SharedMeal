package com.example.sharedmeal.Donor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sharedmeal.R;


public class MainActivity extends AppCompatActivity {

    static String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Donor(View view) {
        //called when user clicks the donor button
        Intent donateIntent = new Intent(this, LoginActivity.class);
        type = "donors";
        startActivity(donateIntent);
    }

    public void Rider(View view) {
        //called when user clicks on the Rider button
        Intent intent = new Intent(this, LoginActivity.class);
        type = "riders";
        startActivity(intent);
    }
}