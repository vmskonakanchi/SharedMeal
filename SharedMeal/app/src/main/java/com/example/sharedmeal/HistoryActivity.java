package com.example.sharedmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.ui.auth.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private List<String> userDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        UpdateList();
    }

    private void UpdateList() {
        //getting the data for the updating the list
        ListView list = findViewById(R.id.historyList);
        //TODO: get the selected item from the quantity activity and get them here and it userdata here
        String data = QuantityActivity.quantitySelected + "\n"
                + QuantityActivity.vehicleType + "\n"
                + QuantityActivity.address;

        userDataList.add(data);
        userDataList.add("Hey All XDXD");

        ArrayAdapter<String> listOfDonations = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userDataList);
        list.setAdapter(listOfDonations);
    }
}
