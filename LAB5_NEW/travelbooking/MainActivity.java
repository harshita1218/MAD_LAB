package com.example.travelbooking;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Spinner sourceSpinner, destinationSpinner;
    private Button selectDateButton, submitButton, resetButton;
    private TextView selectedDateText;
    private ToggleButton ticketTypeToggle;
    private String selectedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sourceSpinner = findViewById(R.id.sourceSpinner);
        destinationSpinner = findViewById(R.id.destinationSpinner);
        selectDateButton = findViewById(R.id.selectDateButton);
        selectedDateText = findViewById(R.id.selectedDateText);
        ticketTypeToggle = findViewById(R.id.ticketTypeToggle);
        submitButton = findViewById(R.id.submitButton);
        resetButton = findViewById(R.id.resetButton);

        // Populate Spinners
        String[] locations = {"New York", "Los Angeles", "Chicago", "Houston", "San Francisco"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, locations);
        sourceSpinner.setAdapter(adapter);
        destinationSpinner.setAdapter(adapter);

        // Date Picker
        selectDateButton.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (view, year1, month1, dayOfMonth) -> {
                        selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                        selectedDateText.setText("Selected Date: " + selectedDate);
                    }, year, month, day);
            datePickerDialog.show();
        });

        // Submit Button Click
        submitButton.setOnClickListener(v -> {
            String source = sourceSpinner.getSelectedItem().toString();
            String destination = destinationSpinner.getSelectedItem().toString();
            String ticketType = ticketTypeToggle.isChecked() ? "Round Trip" : "One Way";

            if (selectedDate.isEmpty()) {
                selectedDateText.setText("Please select a date!");
                return;
            }

            Intent intent = new Intent(MainActivity.this, ConfirmationActivity.class);
            intent.putExtra("source", source);
            intent.putExtra("destination", destination);
            intent.putExtra("travelDate", selectedDate);
            intent.putExtra("ticketType", ticketType);
            startActivity(intent);
        });

        // Reset Button Click
        resetButton.setOnClickListener(v -> {
            sourceSpinner.setSelection(0);
            destinationSpinner.setSelection(0);
            selectedDate = "";
            selectedDateText.setText("Selected Date: Not Set");
            ticketTypeToggle.setChecked(false);
        });
    }
}
