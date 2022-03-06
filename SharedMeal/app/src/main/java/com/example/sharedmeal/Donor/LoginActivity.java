package com.example.sharedmeal.Donor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sharedmeal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Rider.RiderDashboardActivity;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference db;

    private final String databaseURL = "https://shared-meal-ce571-default-rtdb.asia-southeast1.firebasedatabase.app/";

    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_login);
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance(databaseURL).getReference("users");
    }

    public void Login(View view) {
        //called when user clicked on login screen
        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);
        CheckDetails(email, password);
        try {
            LoginInWithDetails(email.getText().toString().trim(), password.getText().toString().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //TODO: check if the user email has in

    private void CheckDetails(EditText email, EditText password) {
        if (TextUtils.isEmpty(password.getText().toString()) && TextUtils.isEmpty(email.getText())) {
            email.setError("Please Enter A Valid Email Address");
            password.setError("Please Enter A Valid Password");
            return;
        } else if (TextUtils.isEmpty(email.getText()) && !TextUtils.isEmpty(password.getText().toString())) {
            email.setError("Please Enter A Valid Email Address");
            return;
        } else if (TextUtils.isEmpty(password.getText().toString()) && !TextUtils.isEmpty(email.getText())) {
            password.setError("Please Enter A Valid Password");
            return;
        }
    }

    private void LoginInWithDetails(String userEmail, String userPassword) {
        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //login successful
                    db.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                try {
                                    String details = snapshot.child(MainActivity.type).child(auth.getCurrentUser().getUid()).getValue(User.class).getEmail();
                                    if (details.equals(email.getText().toString())) {
                                        Toast.makeText(LoginActivity.this, "Logging in", Toast.LENGTH_SHORT).show();
                                        if (MainActivity.type == "donors")
                                            startActivity(new Intent(LoginActivity.this, DashBoardActivity.class));
                                        else
                                            startActivity(new Intent(LoginActivity.this, RiderDashboardActivity.class));
                                    }
                                } catch (NullPointerException e) {
                                    Toast.makeText(LoginActivity.this, "Please Check The Credentials", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    //login unsuccessful
                    Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void Register(View view) {
        //called when user pressed register button - go to register activity
        String module = "";

        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void ForgotPassword(View view) {
        //called when user clicks forgot password button
        Toast.makeText(this, "Please Check Your Inbox And Verify", Toast.LENGTH_SHORT).show();
    }
}