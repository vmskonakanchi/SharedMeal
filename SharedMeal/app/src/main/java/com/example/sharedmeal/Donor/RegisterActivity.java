package com.example.sharedmeal.Donor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sharedmeal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference database;
    private final String databaseurl = "https://shared-meal-ce571-default-rtdb.asia-southeast1.firebasedatabase.app/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_register);
        try {
            auth = FirebaseAuth.getInstance();
            database = FirebaseDatabase.getInstance(databaseurl).getReference();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void RegisterNew(View view) {
        //called when user clicks on the register button
        EditText userName = findViewById(R.id.userName);
        EditText userEmail = findViewById(R.id.userEmail);
        EditText userPassword = findViewById(R.id.userPassword);

        if (TextUtils.isEmpty(userEmail.getText().toString())) {
            userEmail.setError("Valid Username is required");
            Toast.makeText(this, "Please Type The Appropriate Username And Password", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword.getText().toString())) {
            userPassword.setError("Valid Username is required");
            Toast.makeText(this, "Please Type The Appropriate Username And Password", Toast.LENGTH_LONG).show();
            return;
        }
        if (userPassword.getText().toString().length() <= 7) {
            userPassword.setError("Password is too short must be greater than 7 characters");
        }

        //create the user with username and password
        auth.createUserWithEmailAndPassword(userEmail.getText().toString(), userPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //successfully created account
                    AddUser(userName.getText().toString(), userEmail.getText().toString(), database, auth);
                    Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                } else {
                    // unsuccessful
                    Toast.makeText(RegisterActivity.this, "Error : " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        try {
            UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                    .setDisplayName(userName.getText().toString()).build();
            auth.getCurrentUser().updateProfile(request);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void AddUser(String name, String email, DatabaseReference db, FirebaseAuth auth) {
        User user = new User(name, email, " ");
        if (db != null && auth != null) {
            try {
                db.child("users").child(MainActivity.type).child(auth.getCurrentUser().getUid()).setValue(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}