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

    private boolean approved;
    private DatabaseReference db;

    private FirebaseUser user;
    private String uID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_status);
        db = FirebaseDatabase.getInstance("https://shared-meal-ce571-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("users").child(MainActivity.type);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uID = user.getUid();
        try {
            UpdateStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void UpdateStatus() {
        TextView recentText = findViewById(R.id.recentDonationText);
        TextView approvedText = findViewById(R.id.approvedText);
        Button deleteButton = findViewById(R.id.deleteButton);
        if (approved) {
            //show approved text with a tick mark on right side of it
            deleteButton.setVisibility(View.GONE);
            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String address = snapshot.child(uID).getValue(User.class).getRecentDonation();
                        recentText.setText(address);
                        approvedText.setText("APPROVED");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(StatusActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            approvedText.setText("NOT APPROVED");
            deleteButton.setVisibility(View.VISIBLE);
        }
    }

    public void DeleteDonation(View view) {
        // called when delete donation button is clicked
        db.child(uID).child("recentDonation").setValue(" ");
    }

}