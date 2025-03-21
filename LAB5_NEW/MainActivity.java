package com.example.datetimepicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView dateText, timeText;
    private Button pickDateBtn, pickTimeBtn, submitBtn;
    private int selectedYear, selectedMonth, selectedDay, selectedHour, selectedMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateText = findViewById(R.id.dateText);
        timeText = findViewById(R.id.timeText);
        pickDateBtn = findViewById(R.id.pickDateBtn);
        pickTimeBtn = findViewById(R.id.pickTimeBtn);
        submitBtn = findViewById(R.id.submitBtn);

        Calendar calendar = Calendar.getInstance();

        pickDateBtn.setOnClickListener(view -> {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (view1, year1, month1, dayOfMonth) -> {
                        selectedYear = year1;
                        selectedMonth = month1 + 1;  // Month is 0-based
                        selectedDay = dayOfMonth;
                        dateText.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
                    }, year, month, day);
            datePickerDialog.show();
        });

        pickTimeBtn.setOnClickListener(view -> {
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                    (view1, hourOfDay, minute1) -> {
                        selectedHour = hourOfDay;
                        selectedMinute = minute1;
                        timeText.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
                    }, hour, minute, true);
            timePickerDialog.show();
        });

        submitBtn.setOnClickListener(view -> {
            String dateTime = "Selected Date: " + selectedDay + "/" + selectedMonth + "/" + selectedYear +
                    "\nSelected Time: " + String.format("%02d:%02d", selectedHour, selectedMinute);
            Toast.makeText(MainActivity.this, dateTime, Toast.LENGTH_LONG).show();
        });
    }
}
