package com.example.travelbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity {

    private TextView confirmationText;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        confirmationText = findViewById(R.id.confirmationText);
        backButton = findViewById(R.id.backButton);

        Intent intent = getIntent();
        String source = intent.getStringExtra("source");
        String destination = intent.getStringExtra("destination");
        String travelDate = intent.getStringExtra("travelDate");
        String ticketType = intent.getStringExtra("ticketType");

        confirmationText.setText("Travel Details:\n\n" +
                "Source: " + source + "\n" +
                "Destination: " + destination + "\n" +
                "Travel Date: " + travelDate + "\n" +
                "Ticket Type: " + ticketType);

        backButton.setOnClickListener(v -> finish());
    }
}
