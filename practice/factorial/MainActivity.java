package com.example.factorialapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateFactorial(View view) {
        EditText numberInput = findViewById(R.id.numberInput);
        String input = numberInput.getText().toString();

        if (input.isEmpty()) {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
        } else {
            int number = Integer.parseInt(input);
            int result = factorial(number);

            // Pass the result to the next activity
            Intent intent = new Intent(this, FactorialActivity.class);
            intent.putExtra("factorial", result);
            startActivity(intent);
        }
    }

    // Method to calculate factorial
    private int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
