

package com.example.testapplab4;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the views by their IDs
        button = findViewById(R.id.button);
        toggleButton = findViewById(R.id.toggleButton);

        // Set an OnClickListener for the Button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomToast("Button clicked!", R.drawable.button_image);
            }
        });

        // Set an OnClickListener for the ToggleButton
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton.isChecked()) {
                    showCustomToast("Toggle ON clicked!", R.drawable.toggle_on_image);
                } else {
                    showCustomToast("Toggle OFF clicked!", R.drawable.toggle_off_image);
                }
            }
        });
    }

    // Helper method to show custom Toast with an image
    private void showCustomToast(String message, int imageResId) {
        // Create the custom layout for the Toast
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, null);

        // Set the image and text in the custom layout
        ImageView imageView = layout.findViewById(R.id.toastImage);
        TextView textView = layout.findViewById(R.id.toastMessage);

        imageView.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, imageResId));
        textView.setText(message);

        // Create the Toast and set its duration
        Toast toast = new Toast(MainActivity.this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setGravity(Gravity.CENTER, 0, 0); // Center the toast on the screen

        // Show the Toast
        toast.show();
    }
}
