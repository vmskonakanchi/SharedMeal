package Rider;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharedmeal.Donor.StatusActivity;
import com.example.sharedmeal.R;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class PickUpComplete extends AppCompatActivity {

    //getting a reference for our image to show on screen
    private ImageView image;
    private ActivityResultLauncher<Intent> someActivityResultLauncher;
    private Button completeButton;
    private TextView cText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_up_complete);
        image = findViewById(R.id.foodImage);
        completeButton = findViewById(R.id.completeButton);
        cText = findViewById(R.id.cText);
        completeButton.setVisibility(View.VISIBLE);
        cText.setVisibility(View.INVISIBLE);
        try {
            someActivityResultLauncher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                //getting the result
                                assert result.getData() != null;
                                Uri uri = result.getData().getData();
                                //setting the image uri to the imageview uri
                                image.setImageURI(uri);
                                completeButton.setVisibility(View.INVISIBLE);
                                cText.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong try again", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    public void CompletePickUp(View view) {
        //called when complete pick up button is clicked
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        someActivityResultLauncher.launch(intent);
    }
}