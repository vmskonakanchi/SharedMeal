package Rider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sharedmeal.Donor.QuantityActivity;
import com.example.sharedmeal.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RiderHistory extends AppCompatActivity {

    private ListView list;
    private DatabaseReference db;
    private List<String> donations = new ArrayList<>();

    private final String databaseURL = "https://shared-meal-ce571-default-rtdb.asia-southeast1.firebasedatabase.app/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rider_history);
        db = FirebaseDatabase.getInstance(databaseURL).getReference("users").child("donors");
        list = findViewById(R.id.riderHistoryList);
        UpdateList();
    }

    private void UpdateList() {
        //TODO: add logic to get donor recentDonation to the rider history page along with name of the donor and userid - Database query
        try {
            String recent = "Donor Name : " + QuantityActivity.name +
                    "Donor Email : " + QuantityActivity.email
                    + QuantityActivity.dAddress;
            donations.add(recent);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, donations);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    startActivity(new Intent(RiderHistory.this, RiderMapActivity.class));
                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }
}