package com.example.movieticketbooking;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        TextView confirmationText = findViewById(R.id.confirmationText);
        String details = "Booking Confirmed!\nMovie: " + getIntent().getStringExtra("movie");
        confirmationText.setText(details);
    }
}
