package com.example.sharedmeal.Donor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

    private TextView addressView;
    private Button deleteButton;
    private TextView approvedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_status);
        db = FirebaseDatabase.getInstance(databaseURL)
                .getReference("users").child(MainActivity.type).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("donations");
        addressView = findViewById(R.id.addressText);
        deleteButton = findViewById(R.id.deleteButton);
        approvedView = findViewById(R.id.approvedText);
        UpdateStatus();
    }

    private void UpdateStatus() {
        try {
            addressView.setText(HistoryActivity.riderResults.get(0));
            if (approved) {
                approvedView.setText("Approved");
                deleteButton.setVisibility(View.INVISIBLE);
            } else {
                approvedView.setText("Not Approved");
                deleteButton.setVisibility(View.VISIBLE);
            }
        } catch (
                NullPointerException e) {
            Toast.makeText(this, "Something went wrong,please try again", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void DeleteDonation(View view) {
        // called when deleteButton donation button is clicked
        try {
            db.removeValue(new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    ref.setValue(null);
                    addressView.setText("");
                }
            });
        } catch (NullPointerException e) {
            Toast.makeText(this, "Something went wrong,please try again", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}