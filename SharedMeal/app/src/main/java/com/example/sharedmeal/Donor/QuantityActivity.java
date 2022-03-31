package com.example.sharedmeal.Donor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sharedmeal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QuantityActivity extends AppCompatActivity {
    //declaring spinner object for the user to select the type of objects
    private Spinner quantitySpinner;
    private Spinner vType;
    private Spinner donationType;

    // referring database
    private DatabaseReference db;
    private FirebaseUser user;

    // url for our database reference
    private final String databaseurl = "https://shared-meal-ce571-default-rtdb.asia-southeast1.firebasedatabase.app/";

    public static String dAddress;
    public static String name;
    public static String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_quantity);
        user = FirebaseAuth.getInstance().getCurrentUser();
        quantitySpinner = findViewById(R.id.spinner);
        vType = findViewById(R.id.vehicleTypes);
        donationType = findViewById(R.id.donationType);
        db = FirebaseDatabase.getInstance(databaseurl).getReference();
        name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
    }


    public void GoToHome(View view) {
        if (db == null) return;
        String address = "";
        //called when user clicks the submit button
        EditText userAddress = findViewById(R.id.address);

        //getting user address field above and getting the text in it below
        if (!TextUtils.isEmpty(userAddress.getText().toString())) {
            address = userAddress.getText().toString();
        } else {
            userAddress.setError("Please Enter A Valid Address");
        }
        String donatedTo = "DonationType : " + donationType.getSelectedItem().toString() + "\n"
                + "VehicleType : " + vType.getSelectedItem().toString() + "\n"
                + "Quantity : " + quantitySpinner.getSelectedItem().toString() + "\n"
                + "Address : " + address;
        try {
            if (!TextUtils.isEmpty(address)) {
                dAddress = address;
                db.child("users").child("donors").child(user.getUid()).child("donations").push().setValue(donatedTo);
                startActivity(new Intent(this, DashBoardActivity.class));
            } else {
                Toast.makeText(this, "Please Enter A Valid Address", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}