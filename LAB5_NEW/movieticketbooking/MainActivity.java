package com.example.movieticketbooking;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Spinner movieSpinner, theatreSpinner;
    private Button selectDateButton, selectTimeButton, bookNowButton, resetButton;
    private TextView selectedDateText, selectedTimeText;
    private ToggleButton ticketTypeToggle;
    private String selectedDate = "", selectedTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieSpinner = findViewById(R.id.movieSpinner);
        theatreSpinner = findViewById(R.id.theatreSpinner);
        selectDateButton = findViewById(R.id.selectDateButton);
        selectedDateText = findViewById(R.id.selectedDateText);
        selectTimeButton = findViewById(R.id.selectTimeButton);
        selectedTimeText = findViewById(R.id.selectedTimeText);
        ticketTypeToggle = findViewById(R.id.ticketTypeToggle);
        bookNowButton = findViewById(R.id.bookNowButton);
        resetButton = findViewById(R.id.resetButton);

        // Populate Spinners
        String[] movies = {"Avatar", "Inception", "Titanic", "The Dark Knight", "Interstellar"};
        String[] theatres = {"PVR Cinemas", "IMAX", "Cinepolis", "INOX"};

        ArrayAdapter<String> movieAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, movies);
        ArrayAdapter<String> theatreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, theatres);
        movieSpinner.setAdapter(movieAdapter);
        theatreSpinner.setAdapter(theatreAdapter);

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

        // Time Picker
        selectTimeButton.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                    (view, hourOfDay, minute1) -> {
                        selectedTime = hourOfDay + ":" + (minute1 < 10 ? "0" + minute1 : minute1);
                        selectedTimeText.setText("Selected Time: " + selectedTime);
                        validatePremiumBooking(hourOfDay);
                    }, hour, minute, false);
            timePickerDialog.show();
        });

        // Premium Ticket Restriction
        ticketTypeToggle.setOnCheckedChangeListener((buttonView, isChecked) -> validatePremiumBooking(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)));

        // Book Now Button Click
        bookNowButton.setOnClickListener(v -> {
            String movie = movieSpinner.getSelectedItem().toString();
            String theatre = theatreSpinner.getSelectedItem().toString();
            String ticketType = ticketTypeToggle.isChecked() ? "Premium" : "Standard";

            Intent intent = new Intent(MainActivity.this, ConfirmationActivity.class);
            intent.putExtra("movie", movie);
            intent.putExtra("theatre", theatre);
            intent.putExtra("showDate", selectedDate);
            intent.putExtra("showTime", selectedTime);
            intent.putExtra("ticketType", ticketType);
            startActivity(intent);
        });

        // Reset Button Click
        resetButton.setOnClickListener(v -> {
            movieSpinner.setSelection(0);
            theatreSpinner.setSelection(0);
            selectedDate = "";
            selectedTime = "";
            selectedDateText.setText("Selected Date: Not Set");
            selectedTimeText.setText("Selected Time: Not Set");
            ticketTypeToggle.setChecked(false);
            bookNowButton.setEnabled(false);
        });
    }

    private void validatePremiumBooking(int hourOfDay) {
        if (ticketTypeToggle.isChecked() && hourOfDay < 12) {
            bookNowButton.setEnabled(false);
            Toast.makeText(MainActivity.this, "Premium tickets can be booked only after 12:00 PM", Toast.LENGTH_LONG).show();
        } else {
            bookNowButton.setEnabled(true);
        }
    }
}
