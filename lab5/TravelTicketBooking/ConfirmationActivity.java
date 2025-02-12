package com.example.travelticketbooking;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity {

    private TextView tvSource, tvDestination, tvDate, tvTripType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        // Initialize UI components
        tvSource = findViewById(R.id.tv_source);
        tvDestination = findViewById(R.id.tv_destination);
        tvDate = findViewById(R.id.tv_date);
        tvTripType = findViewById(R.id.tv_trip_type);

        // Get data from the intent
        String source = getIntent().getStringExtra("source");
        String destination = getIntent().getStringExtra("destination");
        String date = getIntent().getStringExtra("date");
        String tripType = getIntent().getStringExtra("tripType");

        // Set data into TextViews
        tvSource.setText("Source: " + source);
        tvDestination.setText("Destination: " + destination);
        tvDate.setText("Date: " + date);
        tvTripType.setText("Trip Type: " + tripType);
    }
}
