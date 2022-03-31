package com.example.sharedmeal.Donor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sharedmeal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    private final String databaseURL = "https://shared-meal-ce571-default-rtdb.asia-southeast1.firebasedatabase.app/";

    private DatabaseReference db;
    private FirebaseAuth auth;

    private EditText userName;
    private EditText userEmail;
    private EditText userPassword;

    private FirebaseUser user;

    private boolean nameSuccessful;
    private boolean emailSuccessful;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_profile);
        db = FirebaseDatabase.getInstance(databaseURL).getReference("users").child("donors");
        auth = FirebaseAuth.getInstance();
        GetDetails();
    }


    private void GetDetails() {
        userName = findViewById(R.id.profileName);
        userEmail = findViewById(R.id.profileEmail);
        userPassword = findViewById(R.id.profilePassword);
        if (auth.getCurrentUser() != null) {
            user = auth.getCurrentUser();
            userName.setText(user.getDisplayName());
            userEmail.setText(user.getEmail());
        }
    }

    public void UpdateProfile(View view) {
        // called when update profile button is clicked
        userName.setTextIsSelectable(true);
        userEmail.setTextIsSelectable(true);
        userPassword.setTextIsSelectable(true);

        //for setting password
        if (userName.isTextSelectable() && userEmail.isTextSelectable() && userPassword.isTextSelectable()) {
            if (!TextUtils.isEmpty(userName.getText().toString())) {
                UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                        .setDisplayName(userName.getText().toString()).build();

                user.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            nameSuccessful = true;
                        else
                            Toast.makeText(ProfileActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                userName.setError("UserName Cannot be empty");
                return;
            }

            //for setting email
            if (!TextUtils.isEmpty(userEmail.getText().toString())) {
                user.updateEmail(userEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            if (nameSuccessful) {
                                emailSuccessful = true;

                            } else {
                                Toast.makeText(ProfileActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            } else {
                userEmail.setError("EmailAddress Cannot be empty");
                return;
            }

            //for setting password
            if (!TextUtils.isEmpty(userPassword.getText().toString())) {
                user.updatePassword(userPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            if (nameSuccessful && emailSuccessful) {
                                db.child(user.getUid()).child("email").setValue(user.getEmail());
                                db.child(user.getUid()).child("name").setValue(user.getDisplayName());
                                Toast.makeText(ProfileActivity.this, "Update Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ProfileActivity.this, DashBoardActivity.class));
                                userName.setTextIsSelectable(false);
                                userEmail.setTextIsSelectable(false);
                                userPassword.setTextIsSelectable(false);
                                Toast.makeText(ProfileActivity.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            } else {
                userPassword.setError("Password Cannot Be Empty");
            }

        }
    }

}