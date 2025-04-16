package com.example.studentdatabase;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;

public class ViewDatabaseActivity extends AppCompatActivity {

    GridView gridView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_database);

        gridView = findViewById(R.id.gridView);
        databaseHelper = new DatabaseHelper(this);

        Cursor cursor = databaseHelper.getAllStudents();

        String[] fromColumns = {DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_ROLL_NO};
        int[] toViews = {R.id.studentName, R.id.studentRollNo};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.grid_item, cursor, fromColumns, toViews, 0);
        gridView.setAdapter(adapter);
    }
}
