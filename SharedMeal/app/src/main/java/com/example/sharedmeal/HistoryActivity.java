package com.example.sharedmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    List<String> donations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        UpdateList();
    }

    private void UpdateList() {
        donations = QuantityActivity.quantities;
        ArrayAdapter<String> listOfDonations = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, donations);
        ListView list = findViewById(R.id.historyList);
        list.setAdapter(listOfDonations);
        //TODO: set the list of quantities and address and vehicle type to the user data and update the history list
    }
}

class UserData {
    private String quantity;
    private String address;
    private String vehicleType;
    public String totalData;

    public UserData(String quantity, String address, String vehicleType) {
        this.quantity = quantity;
        this.address = address;
        this.vehicleType = vehicleType;
        totalData = quantity + "\n" + vehicleType + "\n" + address;
    }
}