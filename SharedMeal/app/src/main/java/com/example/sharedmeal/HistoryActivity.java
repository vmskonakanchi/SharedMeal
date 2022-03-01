package com.example.sharedmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    //TODO: Store the user list in database - so when the app closes the history will be there
    private ListView list = null;
    private ArrayAdapter<String> adapter;
    private List<String> userDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        list = findViewById(R.id.historyList);
        try {
            UpdateList();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void UpdateList() {
        //getting the data for the updating the list
        String data = QuantityActivity.quantitySelected + "\n"
                + QuantityActivity.vehicleType + "\n"
                + QuantityActivity.address;
        if (!userDataList.contains(data)) {
            userDataList.add(data);
        } else {
            Toast.makeText(this, "You have already donated for this address ", Toast.LENGTH_LONG).show();
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userDataList);
        list.setAdapter(adapter);
    }
}
