package com.example.factorialapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FactorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factorial);

        int factorial = getIntent().getIntExtra("factorial", 0);

        TextView resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setText("Factorial is: " + factorial);
    }

    // Go back to MainActivity when the button is clicked
    public void goBackToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();  // Optional: Close FactorialActivity so it is removed from the stack
    }
}
