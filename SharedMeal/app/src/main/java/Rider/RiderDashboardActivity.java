package Rider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sharedmeal.Donor.HistoryActivity;
import com.example.sharedmeal.Donor.LoginActivity;
import com.example.sharedmeal.Donor.MainActivity;
import com.example.sharedmeal.Donor.ProfileActivity;
import com.example.sharedmeal.Donor.StatusActivity;
import com.example.sharedmeal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RiderDashboardActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rider_dashboard);
        auth = FirebaseAuth.getInstance();
    }


    public void RiderProfile(View view) {
        //called when profile in rider-activity is clicked
        startActivity(new Intent(this, ProfileActivity.class));
    }

    public void RiderDonations(View view) {
        //called when donations button is clicked
        startActivity(new Intent(this, RiderHistory.class));
    }

    public void RiderLogOut(View view) {
        //called when log out button is clicked in rider-dashboard
        auth.signOut();
        FirebaseAuth.IdTokenListener listener = new FirebaseAuth.IdTokenListener() {
            @Override
            public void onIdTokenChanged(@NonNull FirebaseAuth firebaseAuth) {
                startActivity(new Intent(RiderDashboardActivity.this, LoginActivity.class));
                Toast.makeText(RiderDashboardActivity.this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
            }
        };
        auth.addIdTokenListener(listener);
    }

    public void RiderHistory(View view) {
        //called when history button is clicked in rider-dashboard
        startActivity(new Intent(this, RiderHistory.class));
    }

    public void RiderStatus(View view) {
        //called when status button is clicked in rider-dashboard
        startActivity(new Intent(this, PickUpComplete.class));
    }

    public void HomeRider(View view) {
        //called when home button is clicked
        auth.signOut();
        FirebaseAuth.IdTokenListener listener = new FirebaseAuth.IdTokenListener() {
            @Override
            public void onIdTokenChanged(@NonNull FirebaseAuth firebaseAuth) {
                startActivity(new Intent(RiderDashboardActivity.this, MainActivity.class));
                Toast.makeText(RiderDashboardActivity.this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
            }
        };
        auth.addIdTokenListener(listener);

    }
}