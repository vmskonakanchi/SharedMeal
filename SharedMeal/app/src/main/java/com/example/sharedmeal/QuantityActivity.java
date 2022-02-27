package com.example.sharedmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import kotlin.collections.ArrayDeque;

public class QuantityActivity extends AppCompatActivity {
    private Spinner quantitySpinner;
    private int quantityNumber;
    private String address;
    static List<String> quantities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantity);
        ShowQuantity();
    }

    private void ShowQuantity() {
        //called when user touches quantity drop down columns
        quantities = new ArrayList<>();
        for (int i = 10; i < 110; i += 10) {
            quantities.add(i + "kg");
        }
        quantitySpinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, quantities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(adapter);
    }

    public void GoToHome(View view) {
        //called when user clicks the submit button
        //TODO: validate inputs
        //TODO: go to dashboard only if the inputs are valid
        if (quantitySpinner != null) {
            quantitySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    quantityNumber = parent.getCount();
                }
            });
        }
        EditText userAddress = findViewById(R.id.address);
        if (!TextUtils.isEmpty(userAddress.getText().toString())) {
            address = userAddress.getText().toString();
        }
        String userNumber = quantities.get(quantityNumber);
        int quant = Integer.parseInt(String.valueOf(userNumber.charAt(0) + userNumber.charAt(1)));
        if (quant > 9 && !TextUtils.isEmpty(address)) {
            startActivity(new Intent(this, DashBoardActivity.class));
        }
    }
}