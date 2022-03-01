package com.example.sharedmeal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
    }

    public void RegisterNew(View view) {
        //called when user clicks on the register button
        EditText userName = findViewById(R.id.userName);
        EditText userEmail = findViewById(R.id.userEmail);
        EditText userPassword = findViewById(R.id.userPassword);
        EditText userPhone = findViewById(R.id.userPhone);

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
        if (userPassword.getText().toString().length() <= 5) {
            userPassword.setError("Password is too short must be greater than 8 characters");
        }
        //create the user with username and password
        auth.createUserWithEmailAndPassword(userEmail.getText().toString(), userPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //successfully created account
                    Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                } else {
                    // unsuccessful
                    Toast.makeText(RegisterActivity.this, "Error : " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}