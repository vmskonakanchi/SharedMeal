package com.example.sharedmeal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
    }

    public void Login(View view) {
        //called when user clicked on login screen
        //check if the user is in the database or not
        EditText email = findViewById(R.id.emailLogin);
        EditText password = findViewById(R.id.passwordLogin);
        CheckDetails(email, password);
        try {
            LoginInWithDetails(email.getText().toString().trim(), password.getText().toString().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void CheckDetails(EditText email, EditText password) {
        if (TextUtils.isEmpty(password.getText().toString()) && TextUtils.isEmpty(email.getText())) {
            email.setError("Please Enter A Valid Email Address");
            password.setError("Please Enter A Valid Password");
            Toast.makeText(this, "Please Enter A Valid Email And Password", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(email.getText()) && !TextUtils.isEmpty(password.getText().toString())) {
            email.setError("Please Enter A Valid Email Address");
            Toast.makeText(this, "Please Enter A Valid Email", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(password.getText().toString()) && !TextUtils.isEmpty(email.getText())) {
            password.setError("Please Enter A Valid Password");
            Toast.makeText(this, "Please Enter A Valid Password", Toast.LENGTH_LONG).show();
            return;
        }
    }

    private void LoginInWithDetails(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //login successful
                    Toast.makeText(LoginActivity.this, "Logging in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, DashBoardActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void Register(View view) {
        //called when user pressed register button - go to register activity
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void ForgotPassword(View view) {
        //called when user clicks forgot password button
        Toast.makeText(this, "Please Check Your Inbox And Verify", Toast.LENGTH_SHORT).show();
    }
}