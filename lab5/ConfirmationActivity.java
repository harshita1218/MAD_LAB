package com.example.parkingreg;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ConfirmationActivity extends AppCompatActivity {

    private TextView tvVehicleType, tvVehicleNumber, tvRcNumber;
    private Button btnConfirm, btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        // Initialize UI components
        tvVehicleType = findViewById(R.id.tv_vehicle_type);
        tvVehicleNumber = findViewById(R.id.tv_vehicle_number);
        tvRcNumber = findViewById(R.id.tv_rc_number);
        btnConfirm = findViewById(R.id.btn_confirm);
        btnEdit = findViewById(R.id.btn_edit);

        // Get data from intent
        String vehicleType = getIntent().getStringExtra("vehicleType");
        String vehicleNumber = getIntent().getStringExtra("vehicleNumber");
        String rcNumber = getIntent().getStringExtra("rcNumber");

        // Display the data
        tvVehicleType.setText("Vehicle Type: " + vehicleType);
        tvVehicleNumber.setText("Vehicle Number: " + vehicleNumber);
        tvRcNumber.setText("RC Number: " + rcNumber);

        // Edit Button Logic
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to MainActivity
            }
        });

        // Confirm Button Logic
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate a unique serial number for confirmation
                String serialNumber = "SN" + new Random().nextInt(10000);

                // Show the Toast with serial number
                Toast.makeText(ConfirmationActivity.this, "Parking Allotted! Serial Number: " + serialNumber, Toast.LENGTH_LONG).show();
                finish(); // Go back to MainActivity
            }
        });
    }
}
