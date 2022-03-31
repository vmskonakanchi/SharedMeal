package com.example.sharedmeal.Donor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sharedmeal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private ListView list = null;
    private ArrayAdapter<String> adapter;
    private List<String> result = new ArrayList<>();

    private final String databaseurl = "https://shared-meal-ce571-default-rtdb.asia-southeast1.firebasedatabase.app/";

    private DatabaseReference db;
    private FirebaseUser user;

    static List<String> riderResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_history);
        user = FirebaseAuth.getInstance().getCurrentUser();
        list = findViewById(R.id.historyList);
        db = FirebaseDatabase.getInstance(databaseurl).getReference().child("users")
                .child(MainActivity.type).child(user.getUid()).child("donations");
        try {
            UpdateList();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void UpdateList() {
        //getting the data for the updating the list
        try {
            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot d : snapshot.getChildren()) {
                            String address = d.getValue().toString();
                            result.add(address);
                        }
                        Collections.reverse(result);
                        adapter = new ArrayAdapter<>(HistoryActivity.this, android.R.layout.simple_list_item_activated_1, result);
                        riderResults = result;
                        list.setAdapter(adapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(HistoryActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("Error : ", error.getMessage());
                }
            };
            db.addValueEventListener(eventListener);
        } catch (NullPointerException e) {
            Toast.makeText(this, "Something went wrong try again", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
