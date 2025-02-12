package com.example.travelticketbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerSource, spinnerDestination;
    private EditText etDatePicker;
    private ToggleButton toggleTripType;
    private Button btnSubmit, btnReset;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        spinnerSource = findViewById(R.id.spinner_source);
        spinnerDestination = findViewById(R.id.spinner_destination);
        etDatePicker = findViewById(R.id.et_date_picker);
        toggleTripType = findViewById(R.id.toggle_trip_type);
        btnSubmit = findViewById(R.id.btn_submit);
        btnReset = findViewById(R.id.btn_reset);

        // Initialize Date Picker
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Set default date
        etDatePicker.setText(dateFormat.format(calendar.getTime()));

        // Spinner setup (Source & Destination)
        String[] locations = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSource.setAdapter(adapter);
        spinnerDestination.setAdapter(adapter);

        // Date Picker click handler
        etDatePicker.setOnClickListener(v -> {
            // Open the Date Picker dialog
            new android.app.DatePickerDialog(
                    MainActivity.this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        calendar.set(year, monthOfYear, dayOfMonth);
                        etDatePicker.setText(dateFormat.format(calendar.getTime()));
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            ).show();
        });

        // Submit button click handler
        btnSubmit.setOnClickListener(v -> {
            String source = spinnerSource.getSelectedItem().toString();
            String destination = spinnerDestination.getSelectedItem().toString();
            String date = etDatePicker.getText().toString();
            String tripType = toggleTripType.isChecked() ? "Round Trip" : "One Way";

            // Validate input
            if (source.equals(destination)) {
                Toast.makeText(MainActivity.this, "Source and Destination cannot be the same.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Show the entered details in a new activity
            Intent intent = new Intent(MainActivity.this, ConfirmationActivity.class);
            intent.putExtra("source", source);
            intent.putExtra("destination", destination);
            intent.putExtra("date", date);
            intent.putExtra("tripType", tripType);
            startActivity(intent);
        });

        // Reset button click handler
        btnReset.setOnClickListener(v -> {
            // Reset all inputs to their default values
            spinnerSource.setSelection(0);
            spinnerDestination.setSelection(0);
            etDatePicker.setText(dateFormat.format(Calendar.getInstance().getTime()));
            toggleTripType.setChecked(false);
        });
    }
}
