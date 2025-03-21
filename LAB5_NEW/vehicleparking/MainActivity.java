package com.example.vehicleparking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner vehicleTypeSpinner;
    private EditText vehicleNumber, rcNumber;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vehicleTypeSpinner = findViewById(R.id.vehicleTypeSpinner);
        vehicleNumber = findViewById(R.id.vehicleNumber);
        rcNumber = findViewById(R.id.rcNumber);
        submitButton = findViewById(R.id.submitButton);

        // Spinner options
        String[] vehicleTypes = {"Car", "Bike", "Truck", "Bus", "Scooter"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, vehicleTypes);
        vehicleTypeSpinner.setAdapter(adapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedVehicle = vehicleTypeSpinner.getSelectedItem().toString();
                String vehicleNum = vehicleNumber.getText().toString();
                String rcNum = rcNumber.getText().toString();

                if (vehicleNum.isEmpty() || rcNum.isEmpty()) {
                    vehicleNumber.setError("Required");
                    rcNumber.setError("Required");
                    return;
                }

                // Pass data to ConfirmationActivity
                Intent intent = new Intent(MainActivity.this, ConfirmationActivity.class);
                intent.putExtra("vehicleType", selectedVehicle);
                intent.putExtra("vehicleNumber", vehicleNum);
                intent.putExtra("rcNumber", rcNum);
                startActivity(intent);
            }
        });
    }
}
