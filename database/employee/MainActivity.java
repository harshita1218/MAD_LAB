package com.example.employeedb;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText etEmpId, etName, etDOJ, etAge, etDept, etSalary, etSearchValue;
    Spinner spinnerSearch, spinnerSort;
    TextView tvResult;
    EmployeeDatabaseHelper dbHelper;
    String[] columns = {"empId", "name", "doj", "age", "dept", "salary"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new EmployeeDatabaseHelper(this);

        etEmpId = findViewById(R.id.etEmpId);
        etName = findViewById(R.id.etName);
        etDOJ = findViewById(R.id.etDOJ);
        etAge = findViewById(R.id.etAge);
        etDept = findViewById(R.id.etDept);
        etSalary = findViewById(R.id.etSalary);
        etSearchValue = findViewById(R.id.etSearchValue);
        spinnerSearch = findViewById(R.id.spinnerColumnSearch);
        spinnerSort = findViewById(R.id.spinnerSort);
        tvResult = findViewById(R.id.tvResult);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, columns);
        spinnerSearch.setAdapter(adapter);
        spinnerSort.setAdapter(adapter);

        findViewById(R.id.btnAdd).setOnClickListener(v -> addEmployee());
        findViewById(R.id.btnView).setOnClickListener(v -> viewEmployees(dbHelper.getAllEmployees()));
        findViewById(R.id.btnEdit).setOnClickListener(v -> updateEmployee());
        findViewById(R.id.btnSearch).setOnClickListener(v -> {
            String col = spinnerSearch.getSelectedItem().toString();
            String val = etSearchValue.getText().toString();
            viewEmployees(dbHelper.search(col, val));
        });
        findViewById(R.id.btnSort).setOnClickListener(v -> {
            String col = spinnerSort.getSelectedItem().toString();
            viewEmployees(dbHelper.sort(col));
        });
        findViewById(R.id.btnDelete).setOnClickListener(v -> deleteEmployee());
    }

    void addEmployee() {
        String id = etEmpId.getText().toString(), name = etName.getText().toString(),
                doj = etDOJ.getText().toString(), dept = etDept.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());
        double salary = Double.parseDouble(etSalary.getText().toString());

        boolean inserted = dbHelper.insertEmployee(id, name, doj, age, dept, salary);
        Toast.makeText(this, inserted ? "Employee Added" : "ID Already Exists", Toast.LENGTH_SHORT).show();
    }

    void updateEmployee() {
        String id = etEmpId.getText().toString(), name = etName.getText().toString(),
                doj = etDOJ.getText().toString(), dept = etDept.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());
        double salary = Double.parseDouble(etSalary.getText().toString());

        boolean updated = dbHelper.updateEmployee(id, name, doj, age, dept, salary);
        Toast.makeText(this, updated ? "Employee Updated" : "ID Not Found", Toast.LENGTH_SHORT).show();
    }

    void viewEmployees(Cursor cursor) {
        StringBuilder sb = new StringBuilder();
        if (cursor.getCount() == 0) {
            sb.append("No Records Found");
        } else {
            while (cursor.moveToNext()) {
                sb.append("ID: ").append(cursor.getString(0))
                        .append(", Name: ").append(cursor.getString(1))
                        .append(", DOJ: ").append(cursor.getString(2))
                        .append(", Age: ").append(cursor.getInt(3))
                        .append(", Dept: ").append(cursor.getString(4))
                        .append(", Salary: ").append(cursor.getDouble(5)).append("\n\n");
            }
        }
        cursor.close();
        tvResult.setText(sb.toString());
    }

    void deleteEmployee() {
        String id = etEmpId.getText().toString();
        if (id.isEmpty()) {
            Toast.makeText(this, "Enter Employee ID", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean deleted = dbHelper.deleteEmployee(id);
        Toast.makeText(this, deleted ? "Deleted Successfully" : "Employee Not Found", Toast.LENGTH_SHORT).show();
    }
}

