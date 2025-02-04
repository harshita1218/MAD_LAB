package com.example.calculator;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView resultText;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Corrected ID reference
        resultText = findViewById(R.id.resultText);
        backButton = findViewById(R.id.backButton);

        // Get the result passed from MainActivity
        String result = getIntent().getStringExtra("result");

        // Check if result is not null before setting text
        if (result != null) {
            resultText.setText(result);
        } else {
            resultText.setText("No result received");
        }

        // Back button to go back to MainActivity
        backButton.setOnClickListener(view -> finish());
    }
}
