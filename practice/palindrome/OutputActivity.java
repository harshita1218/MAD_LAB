package com.example.palindromeandvowels;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class OutputActivity extends AppCompatActivity {

    private TextView textViewOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        textViewOutput = findViewById(R.id.textViewOutput);

        // Get the result from the intent
        String result = getIntent().getStringExtra("result");
        textViewOutput.setText(result);
    }
}
