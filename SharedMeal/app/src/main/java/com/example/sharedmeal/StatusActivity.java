package com.example.sharedmeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;

public class StatusActivity extends AppCompatActivity {

    private boolean approved = true;

    private CardView card1;
    private CardView card2;
    private CardView card3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        GetCardViews();
        UpdateStatus();
    }

    private void GetCardViews() {
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
    }

    private void UpdateStatus() {
        if (approved) {
            //TODO:the donation is not yet updated - show ui correspondingly
        } else {
            /*TODO:show the approved or not with a string
            TODO: add a tick mark or something indication for the approved boolean
             */
        }
    }

}