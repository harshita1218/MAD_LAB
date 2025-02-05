package com.example.inputdisplay;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputField;
    private Button submitButton;
    private TextView displayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        inputField = findViewById(R.id.inputField);
        submitButton = findViewById(R.id.submitButton);
        displayText = findViewById(R.id.displayText);

        // Set click listener for the button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = inputField.getText().toString();
                if (!userInput.isEmpty()) {
                    displayText.setText("Entered: " + userInput);
                } else {
                    displayText.setText("Please enter something!");
                }
            }
        });
    }
}
