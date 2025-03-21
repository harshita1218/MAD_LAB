package com.example.vehicleparking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class ConfirmationActivity extends AppCompatActivity {

    private TextView confirmationText;
    private Button editButton, confirmButton;
    private String vehicleType, vehicleNumber, rcNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        confirmationText = findViewById(R.id.confirmationText);
        editButton = findViewById(R.id.editButton);
        confirmButton = findViewById(R.id.confirmButton);

        // Retrieve data from intent
        Intent intent = getIntent();
        vehicleType = intent.getStringExtra("vehicleType");
        vehicleNumber = intent.getStringExtra("vehicleNumber");
        rcNumber = intent.getStringExtra("rcNumber");

        // Display the details
        confirmationText.setText("Vehicle Type: " + vehicleType + "\nVehicle No: " + vehicleNumber + "\nRC No: " + rcNumber);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Go back to MainActivity for editing
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int uniqueSerial = new Random().nextInt(100000) + 10000; // Generate random serial number
                Toast.makeText(ConfirmationActivity.this, "Parking Confirmed! Serial No: " + uniqueSerial, Toast.LENGTH_LONG).show();
            }
        });
    }
}
