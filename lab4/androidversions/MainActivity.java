package com.example.androidversions;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button iceCreamButton = findViewById(R.id.button_iceCream);
        Button jellyBeanButton = findViewById(R.id.button_jellyBean);
        Button kitKatButton = findViewById(R.id.button_kitKat);
        Button lollipopButton = findViewById(R.id.button_lollipop);

        iceCreamButton.setOnClickListener(v -> showToast("Ice Cream Sandwich", R.drawable.ic_ice_cream));
        jellyBeanButton.setOnClickListener(v -> showToast("Jelly Bean", R.drawable.ic_jelly_bean));
        kitKatButton.setOnClickListener(v -> showToast("KitKat", R.drawable.ic_kitkat));
        lollipopButton.setOnClickListener(v -> showToast("Lollipop", R.drawable.ic_lollipop));
    }

    private void showToast(String message, int iconResId) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, null);

        ImageView icon = layout.findViewById(R.id.toast_icon);
        TextView text = layout.findViewById(R.id.toast_text);

        icon.setImageResource(iconResId);
        text.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setGravity(Gravity.BOTTOM, 0, 100);
        toast.show();
    }
}
