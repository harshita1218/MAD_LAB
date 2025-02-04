package com.example.palindromeandvowels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private Button buttonCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = findViewById(R.id.editTextInput);
        buttonCheck = findViewById(R.id.buttonCheck);

        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editTextInput.getText().toString().trim();
                if (!input.isEmpty()) {
                    String result = checkPalindrome(input);
                    result += "\n" + vowelFrequency(input);

                    // Start OutputActivity and pass the result
                    Intent intent = new Intent(MainActivity.this, OutputActivity.class);
                    intent.putExtra("result", result);
                    startActivity(intent);
                }
            }
        });
    }

    // Method to check if a string is a palindrome
    private String checkPalindrome(String input) {
        String reversed = new StringBuilder(input).reverse().toString();
        if (input.equalsIgnoreCase(reversed)) {
            return "The string is a palindrome!";
        } else {
            return "The string is not a palindrome.";
        }
    }

    // Method to count the frequency of vowels
    private String vowelFrequency(String input) {
        int[] vowelCount = new int[5]; // a, e, i, o, u

        for (char c : input.toLowerCase().toCharArray()) {
            switch (c) {
                case 'a':
                    vowelCount[0]++;
                    break;
                case 'e':
                    vowelCount[1]++;
                    break;
                case 'i':
                    vowelCount[2]++;
                    break;
                case 'o':
                    vowelCount[3]++;
                    break;
                case 'u':
                    vowelCount[4]++;
                    break;
            }
        }

        return "Vowel Frequency:\n" +
                "a: " + vowelCount[0] + "\n" +
                "e: " + vowelCount[1] + "\n" +
                "i: " + vowelCount[2] + "\n" +
                "o: " + vowelCount[3] + "\n" +
                "u: " + vowelCount[4];
    }
}
