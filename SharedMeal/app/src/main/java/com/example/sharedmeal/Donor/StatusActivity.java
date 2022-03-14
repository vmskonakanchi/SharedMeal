package com.example.sharedmeal.Donor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharedmeal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StatusActivity extends AppCompatActivity {

    public static boolean approved;
    private DatabaseReference db;
    private final String databaseURL = "https://shared-meal-ce571-default-rtdb.asia-southeast1.firebasedatabase.app/";
    private FirebaseUser user;
    private String uID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_status);
        db = FirebaseDatabase.getInstance(databaseURL)
                .getReference("users").child(MainActivity.type).child("donations");
        user = FirebaseAuth.getInstance().getCurrentUser();
        uID = user.getUid();
        try {
            UpdateStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void UpdateStatus() {
        if (approved) {
            //TODO:set approved to yes

        } else {
            //TODO:set approved to not
            // show delete button
        }
    }

    public void DeleteDonation(View view) {
        // called when delete donation button is clicked
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot d : snapshot.getChildren()) {
                        String address = d.getValue().toString();
                        /*
                           TODO: here add logic to show details in card view and button
                         */
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StatusActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}