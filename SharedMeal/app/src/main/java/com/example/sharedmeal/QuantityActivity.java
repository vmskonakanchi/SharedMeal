package com.example.sharedmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import kotlin.collections.ArrayDeque;

public class QuantityActivity extends AppCompatActivity {
    //declaring spinner object for the user to select the type of objects
    private Spinner quantitySpinner;
    private Spinner vType;

    //list for other classes to use via making them static
    static String quantitySelected;
    static String address;
    static String vehicleType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantity);
        quantitySpinner = findViewById(R.id.spinner);
        vType = findViewById(R.id.vehicleTypes);
    }


    public void GoToHome(View view) {
        //called when user clicks the submit button
        EditText userAddress = findViewById(R.id.address);
        //getting user address field above and getting the text in it below
        if (!TextUtils.isEmpty(userAddress.getText().toString())) {
            address = userAddress.getText().toString();
        } else {
            userAddress.setError("Please Enter A Valid Address");
        }
        quantitySelected = quantitySpinner.getSelectedItem().toString();
        vehicleType = vType.getSelectedItem().toString();

        if (!TextUtils.isEmpty(address)) {
            startActivity(new Intent(this, DashBoardActivity.class));
        } else {
            Toast.makeText(this, "Please Enter A Valid Address", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}