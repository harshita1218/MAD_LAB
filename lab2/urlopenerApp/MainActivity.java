package com.example.urlopenerapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText urlEditText;
    private Button openUrlButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the views
        urlEditText = findViewById(R.id.urlEditText);
        openUrlButton = findViewById(R.id.openUrlButton);

        // Set onClickListener to open the URL when the button is clicked
        openUrlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = urlEditText.getText().toString().trim();

                // Validate the URL
                if (url.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a URL", Toast.LENGTH_SHORT).show();
                    return;
                }

                // If the URL does not start with http:// or https://, add https:// by default
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "https://" + url;
                }

                // Create an intent to open the URL in a web browser
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}
