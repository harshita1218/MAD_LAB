package com.example.squaresimple;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editTextNumber;
    Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber = findViewById(R.id.editTextNumber);
        buttonCalculate = findViewById(R.id.buttonCalculate);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberInput = editTextNumber.getText().toString();
                if (!numberInput.isEmpty()) {
                    int number = Integer.parseInt(numberInput);
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("number", number);
                    startActivity(intent);
                }
            }
        });
    }
}
