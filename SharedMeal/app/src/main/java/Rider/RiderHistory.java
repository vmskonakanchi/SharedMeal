package Rider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Locale;

public class RiderHistory extends AppCompatActivity {

    private ListView list;
    private DatabaseReference db;
    private List<String> donations = new ArrayList<>();

    private static final String databaseURL = "https://shared-meal-ce571-default-rtdb.asia-southeast1.firebasedatabase.app/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rider_history);
        db = FirebaseDatabase.getInstance(databaseURL).getReference("users").child("donors");
        list = findViewById(R.id.riderHistoryList);
        UpdateList();
    }

    private void UpdateList() {
        try {
            String recent = "Donor Name : " + QuantityActivity.name + "\n"
                    + "Donor Email : " + QuantityActivity.email + "\n"
                    + "Address : " + QuantityActivity.dAddress;
            donations.add(recent);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, donations);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String address = QuantityActivity.dAddress;
                    String uri = "http://maps.google.co.in/maps?q=" + address;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(intent);
                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }
}