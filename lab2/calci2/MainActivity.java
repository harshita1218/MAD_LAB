package com.example.calculator;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText num1EditText, num2EditText;
    private Button addButton, subtractButton, multiplyButton, divideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        num1EditText = findViewById(R.id.num1EditText);
        num2EditText = findViewById(R.id.num2EditText);
        addButton = findViewById(R.id.addButton);
        subtractButton = findViewById(R.id.subtractButton);
        multiplyButton = findViewById(R.id.multiplyButton);
        divideButton = findViewById(R.id.divideButton);

        // OnClickListener for addition
        addButton.setOnClickListener(view -> performOperation("+"));

        // OnClickListener for subtraction
        subtractButton.setOnClickListener(view -> performOperation("-"));

        // OnClickListener for multiplication
        multiplyButton.setOnClickListener(view -> performOperation("*"));

        // OnClickListener for division
        divideButton.setOnClickListener(view -> performOperation("/"));
    }

    private void performOperation(String operator) {
        try {
            double num1 = Double.parseDouble(num1EditText.getText().toString());
            double num2 = Double.parseDouble(num2EditText.getText().toString());

            // Perform the selected operation
            double result;
            String resultText = "";
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    resultText = num1 + " + " + num2 + " = " + result;
                    break;
                case "-":
                    result = num1 - num2;
                    resultText = num1 + " - " + num2 + " = " + result;
                    break;
                case "*":
                    result = num1 * num2;
                    resultText = num1 + " * " + num2 + " = " + result;
                    break;
                case "/":
                    if (num2 == 0) {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = num1 / num2;
                    resultText = num1 + " / " + num2 + " = " + result;
                    break;
            }

            // Send the result to ResultActivity
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("result", resultText);  // Send the result
            startActivity(intent);


        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }
}
