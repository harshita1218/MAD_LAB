package com.example.sportselector;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // List of sports
        String[] sports = {"Cricket", "Football", "Basketball", "Tennis", "Badminton", "Hockey", "Volleyball"};

        // Reference to ListView
        ListView sportListView = findViewById(R.id.sportListView);

        // Create an ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sports);

        // Set the adapter to the ListView
        sportListView.setAdapter(adapter);

        // Handle item clicks
        sportListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected sport
                String selectedSport = sports[position];

                // Display a toast message
                Toast.makeText(MainActivity.this, "You selected: " + selectedSport, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
