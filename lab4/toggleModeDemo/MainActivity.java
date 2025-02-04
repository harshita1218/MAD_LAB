package com.example.togglemodedemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ToggleButton toggleButton;
    private ImageView modeImage;
    private Button changeModeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = findViewById(R.id.toggleButton);
        modeImage = findViewById(R.id.modeImage);
        changeModeButton = findViewById(R.id.changeModeButton);

        // Set initial state
        updateMode(toggleButton.isChecked());

        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> updateMode(isChecked));

        changeModeButton.setOnClickListener(v -> {
            boolean newState = !toggleButton.isChecked();
            toggleButton.setChecked(newState);
            updateMode(newState);
        });
    }

    private void updateMode(boolean isWiFi) {
        if (isWiFi) {
            modeImage.setImageResource(R.drawable.wifi_img); // Replace with actual Wi-Fi image
            Toast.makeText(this, "Wi-Fi Mode Enabled", Toast.LENGTH_SHORT).show();
        } else {
            modeImage.setImageResource(R.drawable.mobile_data_img); // Replace with actual Mobile Data image
            Toast.makeText(this, "Mobile Data Mode Enabled", Toast.LENGTH_SHORT).show();
        }
    }
}
