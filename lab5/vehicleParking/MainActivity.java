package com.example.parkingreg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerVehicleType;
    private EditText etVehicleNumber, etRcNumber;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        spinnerVehicleType = findViewById(R.id.spinner_vehicle_type);
        etVehicleNumber = findViewById(R.id.et_vehicle_number);
        etRcNumber = findViewById(R.id.et_rc_number);
        btnSubmit = findViewById(R.id.btn_submit);

        // Spinner values (Vehicle types)
        String[] vehicleTypes = {"Car", "Bike", "Truck", "Bus"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, vehicleTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVehicleType.setAdapter(adapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get entered details
                String vehicleType = spinnerVehicleType.getSelectedItem().toString();
                String vehicleNumber = etVehicleNumber.getText().toString();
                String rcNumber = etRcNumber.getText().toString();

                if (vehicleNumber.isEmpty() || rcNumber.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all the details.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Show details in the next activity
                Intent intent = new Intent(MainActivity.this, ConfirmationActivity.class);
                intent.putExtra("vehicleType", vehicleType);
                intent.putExtra("vehicleNumber", vehicleNumber);
                intent.putExtra("rcNumber", rcNumber);
                startActivity(intent);
            }
        });
    }
}
