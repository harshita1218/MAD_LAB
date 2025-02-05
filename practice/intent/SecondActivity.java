package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Retrieve the data sent from MainActivity
        String message = getIntent().getStringExtra("message");

        // Display the received data
        TextView textView = new TextView(this);
        textView.setText(message != null ? message : "No message received");
        setContentView(textView);

        // Button to go back to MainActivity
        Button backButton = new Button(this);
        backButton.setText("Go Back");
        setContentView(backButton);

        // Go back to MainActivity when the button is clicked
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Use Intent to go back to MainActivity
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Optional: Finish this activity to prevent going back to it
            }
        });
    }
}
