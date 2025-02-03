package com.example.myapplication1;



import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LifecycleEvent";
    private TextView lifecycleText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lifecycleText = findViewById(R.id.lifecycleText);

        String message = "onCreate() called";
        lifecycleText.setText(message);
        Log.d(TAG, message);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String message = "onStart() called";
        lifecycleText.setText(message);
        Log.d(TAG, message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String message = "onResume() called";
        lifecycleText.setText(message);
        Log.d(TAG, message);
    }

    @Override
    protected void onPause() {
        super.onPause();
        String message = "onPause() called";
        Log.d(TAG, message);
    }

    @Override
    protected void onStop() {
        super.onStop();
        String message = "onStop() called";
        Log.d(TAG, message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String message = "onDestroy() called";
        Log.d(TAG, message);
    }
}
