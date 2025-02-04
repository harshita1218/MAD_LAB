package com.example.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private HashMap<CheckBox, Integer> foodItems;
    private ArrayList<String> selectedItems;
    private HashMap<String, Integer> orderedItems;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // Hide the title bar
        setContentView(R.layout.activity_main);

        // Initialize UI components
        CheckBox cbPizza = findViewById(R.id.cbPizza);
        CheckBox cbBurger = findViewById(R.id.cbBurger);
        CheckBox cbPasta = findViewById(R.id.cbPasta);
        CheckBox cbSandwich = findViewById(R.id.cbSandwich);
        CheckBox cbFries = findViewById(R.id.cbFries);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Store food items with their prices
        foodItems = new HashMap<>();
        foodItems.put(cbPizza, 200);
        foodItems.put(cbBurger, 150);
        foodItems.put(cbPasta, 180);
        foodItems.put(cbSandwich, 120);
        foodItems.put(cbFries, 100);

        selectedItems = new ArrayList<>();
        orderedItems = new HashMap<>();

        btnSubmit.setOnClickListener(view -> {
            selectedItems.clear();
            orderedItems.clear();

            for (CheckBox checkBox : foodItems.keySet()) {
                if (checkBox.isChecked()) {
                    String itemName = checkBox.getText().toString();
                    int itemPrice = foodItems.get(checkBox);
                    selectedItems.add(itemName);
                    orderedItems.put(itemName, itemPrice);
                }
            }

            if (selectedItems.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please select at least one item!", Toast.LENGTH_SHORT).show();
            } else {
                // Disable checkboxes after submitting
                for (CheckBox checkBox : foodItems.keySet()) {
                    checkBox.setEnabled(false);
                }
                btnSubmit.setEnabled(false);

                // Navigate to Order Summary Activity
                Intent intent = new Intent(MainActivity.this, OrderSummaryActivity.class);
                intent.putExtra("orderedItems", orderedItems);
                startActivity(intent);
            }
        });
    }
}
