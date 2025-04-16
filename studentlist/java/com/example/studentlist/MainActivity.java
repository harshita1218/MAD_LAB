package com.example.studentlist;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtStudentID, edtStudentName, edtStudentClass;
    private RadioGroup radioGroupGender;
    private CheckBox checkReading, checkMusic, checkSports;
    private Spinner spinnerSection;
    private Button btnSave, btnView;
    private ListView listView;

    private StudentDatabaseHelper dbHelper;
    private SQLiteDatabase database;

    String[] sections = {"A", "B", "C"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtStudentID = findViewById(R.id.edtStudentID);
        edtStudentName = findViewById(R.id.edtStudentName);
        edtStudentClass = findViewById(R.id.edtStudentClass);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        checkReading = findViewById(R.id.checkReading);
        checkMusic = findViewById(R.id.checkMusic);
        checkSports = findViewById(R.id.checkSports);
        spinnerSection = findViewById(R.id.spinnerSection);
        btnSave = findViewById(R.id.btnSave);
        btnView = findViewById(R.id.btnView);
        listView = findViewById(R.id.listView);

        // Setup spinner
        ArrayAdapter<String> sectionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sections);
        spinnerSection.setAdapter(sectionAdapter);

        // DB setup
        dbHelper = new StudentDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        btnSave.setOnClickListener(v -> {
            String studentID = edtStudentID.getText().toString().trim();
            String studentName = edtStudentName.getText().toString().trim();
            String studentClass = edtStudentClass.getText().toString().trim();

            int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
            String gender = "";
            if (selectedGenderId != -1) {
                RadioButton selectedRadio = findViewById(selectedGenderId);
                gender = selectedRadio.getText().toString();
            }

            StringBuilder hobbiesBuilder = new StringBuilder();
            if (checkReading.isChecked()) hobbiesBuilder.append("Reading ");
            if (checkMusic.isChecked()) hobbiesBuilder.append("Music ");
            if (checkSports.isChecked()) hobbiesBuilder.append("Sports ");
            String hobbies = hobbiesBuilder.toString().trim();

            String section = spinnerSection.getSelectedItem().toString();

            if (!studentID.isEmpty() && !studentName.isEmpty() && !studentClass.isEmpty()) {
                try {
                    database.execSQL("INSERT INTO " + StudentDatabaseHelper.TABLE_STUDENTS +
                                    " (" + StudentDatabaseHelper.COLUMN_ID + ", " +
                                    StudentDatabaseHelper.COLUMN_NAME + ", " +
                                    StudentDatabaseHelper.COLUMN_CLASS + ", " +
                                    StudentDatabaseHelper.COLUMN_GENDER + ", " +
                                    StudentDatabaseHelper.COLUMN_HOBBIES + ", " +
                                    StudentDatabaseHelper.COLUMN_SECTION + ") VALUES (?, ?, ?, ?, ?, ?)",
                            new String[]{studentID, studentName, studentClass, gender, hobbies, section});

                    Toast.makeText(MainActivity.this, "Student saved!", Toast.LENGTH_SHORT).show();

                    edtStudentID.setText("");
                    edtStudentName.setText("");
                    edtStudentClass.setText("");
                    radioGroupGender.clearCheck();
                    checkReading.setChecked(false);
                    checkMusic.setChecked(false);
                    checkSports.setChecked(false);
                    spinnerSection.setSelection(0);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });

        btnView.setOnClickListener(v -> {
            Cursor cursor = database.rawQuery("SELECT * FROM " + StudentDatabaseHelper.TABLE_STUDENTS, null);

            ArrayList<String> studentsList = new ArrayList<>();
            if (cursor.moveToFirst()) {
                do {
                    String student = "ID: " + cursor.getString(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_ID)) +
                            "\nName: " + cursor.getString(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_NAME)) +
                            "\nClass: " + cursor.getString(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_CLASS)) +
                            "\nGender: " + cursor.getString(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_GENDER)) +
                            "\nHobbies: " + cursor.getString(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_HOBBIES)) +
                            "\nSection: " + cursor.getString(cursor.getColumnIndexOrThrow(StudentDatabaseHelper.COLUMN_SECTION));
                    studentsList.add(student);
                } while (cursor.moveToNext());
            } else {
                Toast.makeText(MainActivity.this, "No data found", Toast.LENGTH_SHORT).show();
            }
            cursor.close();

            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, studentsList);
            listView.setAdapter(adapter);
        });
    }
}
