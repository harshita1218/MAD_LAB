package com.example.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

public class OrderSummaryActivity extends AppCompatActivity {
    private TextView tvOrderSummary, tvTotalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        tvOrderSummary = findViewById(R.id.tvOrderSummary);
        tvTotalCost = findViewById(R.id.tvTotalCost);

        Intent intent = getIntent();
        HashMap<String, Integer> orderedItems = (HashMap<String, Integer>) intent.getSerializableExtra("orderedItems");

        if (orderedItems != null) {
            StringBuilder orderDetails = new StringBuilder();
            int totalCost = 0;

            for (String item : orderedItems.keySet()) {
                int price = orderedItems.get(item);
                orderDetails.append(item).append(" - ₹").append(price).append("\n");
                totalCost += price;
            }

            tvOrderSummary.setText(orderDetails.toString());
            tvTotalCost.setText("Total: ₹" + totalCost);
        }
    }
}
