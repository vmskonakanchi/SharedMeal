package com.example.sharedmeal;

import androidx.appcompat.app.AppCompatActivity;

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

    //for storing the data in the list
    private List<String> vehicleTypes;

    //list for other classes to use via making them static
    static String quantitySelected;
    static String address;
    static String vehicleType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantity);
        ShowQuantity();
        ShowVehicleType();
    }

    private void ShowQuantity() {
        //called when user touches quantity drop down columns
        List<String> quantityList = new ArrayList<>();
        for (int i = 10; i < 110; i += 10) {
            quantityList.add(i + "kg");
        }
        quantitySpinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, quantityList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(adapter);
    }

    private void ShowVehicleType() {
        vehicleTypes = new ArrayList<>();
        vehicleTypes.add("Bike");
        vehicleTypes.add("Auto");
        vehicleTypes.add("Heavy Vehicle");
        vType = findViewById(R.id.vehicleTypes);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, vehicleTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vType.setAdapter(adapter);
    }

    public void GoToHome(View view) {
        //called when user clicks the submit button
        EditText userAddress = findViewById(R.id.address);
        //getting user address field above and getting the text in it below
        if (!TextUtils.isEmpty(userAddress.getText().toString())) {
            address = userAddress.getText().toString();
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