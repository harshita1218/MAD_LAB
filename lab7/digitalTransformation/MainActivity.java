package com.example.digitaltransformation;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.*;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    TextView contentTextView;
    EditText inputField;
    Button searchButton, highlightButton, sortButton;
    String originalContent = "Digital transformation is the integration of digital technology into all areas of a business. "
            + "It fundamentally changes how you operate and deliver value to customers. "
            + "It also requires cultural change that encourages innovation.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentTextView = findViewById(R.id.contentTextView);
        inputField = findViewById(R.id.inputField);
        searchButton = findViewById(R.id.searchButton);
        highlightButton = findViewById(R.id.highlightButton);
        sortButton = findViewById(R.id.sortButton);

        contentTextView.setText(originalContent);

        searchButton.setOnClickListener(v -> {
            String keyword = inputField.getText().toString().trim();
            if (!keyword.isEmpty()) {
                if (originalContent.toLowerCase().contains(keyword.toLowerCase())) {
                    Toast.makeText(this, "Keyword found!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Keyword not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        highlightButton.setOnClickListener(v -> {
            String keyword = inputField.getText().toString().trim();
            SpannableString spannable = new SpannableString(originalContent);

            if (!keyword.isEmpty()) {
                int index = originalContent.toLowerCase().indexOf(keyword.toLowerCase());
                while (index >= 0) {
                    spannable.setSpan(new BackgroundColorSpan(Color.YELLOW), index, index + keyword.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    index = originalContent.toLowerCase().indexOf(keyword.toLowerCase(), index + keyword.length());
                }
                contentTextView.setText(spannable);
            }
        });

        sortButton.setOnClickListener(v -> {
            String[] words = originalContent.split("\\s+");
            Arrays.sort(words, String.CASE_INSENSITIVE_ORDER);
            StringBuilder sortedContent = new StringBuilder();
            for (String word : words) {
                sortedContent.append(word).append(" ");
            }
            contentTextView.setText(sortedContent.toString().trim());
        });
    }
}
