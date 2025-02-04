package com.example.testapp;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        ToggleButton toggleButton = findViewById(R.id.toggleButton);

        button.setOnClickListener(view -> showCustomToast("Button Clicked!", android.R.drawable.ic_dialog_info));
        toggleButton.setOnClickListener(view -> showCustomToast("Toggle Clicked!", android.R.drawable.ic_menu_gallery));
    }

    private void showCustomToast(String message, int imageResId) {
        // Create a new LinearLayout for the Toast content
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setPadding(20, 10, 20, 10);

        // Add an ImageView
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(imageResId);
        imageView.setPadding(10, 0, 10, 0);
        layout.addView(imageView);

        // Add a TextView
        TextView textView = new TextView(this);
        textView.setText(message);
        layout.addView(textView);

        // Create and display the Toast
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 200);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
