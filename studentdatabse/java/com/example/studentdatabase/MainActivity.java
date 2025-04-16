package com.example.studentdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText nameEditText, rollEditText, marks1EditText, marks2EditText, marks3EditText;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        rollEditText = findViewById(R.id.rollEditText);
        marks1EditText = findViewById(R.id.marks1EditText);
        marks2EditText = findViewById(R.id.marks2EditText);
        marks3EditText = findViewById(R.id.marks3EditText);

        databaseHelper = new DatabaseHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Check Pass/Fail");
        menu.add("View Database");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = item.getTitle().toString();

        if (title.equals("Check Pass/Fail")) {
            checkPassFail();
        } else if (title.equals("View Database")) {
            Intent intent = new Intent(MainActivity.this, ViewDatabaseActivity.class);
            startActivity(intent);
        }

        return true;
    }

    private void checkPassFail() {
        String name = nameEditText.getText().toString();
        String rollNo = rollEditText.getText().toString();
        int marks1 = Integer.parseInt(marks1EditText.getText().toString());
        int marks2 = Integer.parseInt(marks2EditText.getText().toString());
        int marks3 = Integer.parseInt(marks3EditText.getText().toString());

        double average = (marks1 + marks2 + marks3) / 3.0;

        if (average > 40) {
            Toast.makeText(this, name + " (Roll No: " + rollNo + ") has passed!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, name + " (Roll No: " + rollNo + ") has failed!", Toast.LENGTH_SHORT).show();
        }

        // Save to database
        databaseHelper.addStudent(name, rollNo, marks1, marks2, marks3);
    }
}
