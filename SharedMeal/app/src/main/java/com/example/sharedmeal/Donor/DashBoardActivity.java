package com.example.sharedmeal.Donor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sharedmeal.R;
import com.google.firebase.auth.FirebaseAuth;

public class DashBoardActivity extends AppCompatActivity {
    private FirebaseAuth auth;  //connection to firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_dash_board);
        auth = FirebaseAuth.getInstance();
    }

    public void Donate(View view) {
        //called when Donate button is clicked
        AskForLocationPermission();
    }

    public void Status(View view) {
        //called when Status button is clicked
        startActivity(new Intent(this, StatusActivity.class));
    }

    public void History(View view) {
        //called when History button is clicked
        startActivity(new Intent(this, HistoryActivity.class));
    }

    public void Profile(View view) {
        //called when Profile button is clicked
        startActivity(new Intent(this, ProfileActivity.class));
    }

    public void LogOut(View view) {
        //called when Logout button is clicked
        auth.signOut();
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void AskForLocationPermission() {
        //ask for user location if not granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        } else {
            startActivity(new Intent(this, QuantityActivity.class));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "You granted the permission for location", Toast.LENGTH_SHORT).show();
            }
        }
    }
}