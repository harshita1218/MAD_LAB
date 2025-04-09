package com.example.sharedprefdemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editName, editEmail;
    SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "MyPrefs";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // Load saved data
        String savedName = sharedPreferences.getString(KEY_NAME, "");
        String savedEmail = sharedPreferences.getString(KEY_EMAIL, "");

        editName.setText(savedName);
        editEmail.setText(savedEmail);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Save data
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, editName.getText().toString());
        editor.putString(KEY_EMAIL, editEmail.getText().toString());
        editor.apply();
    }
}
