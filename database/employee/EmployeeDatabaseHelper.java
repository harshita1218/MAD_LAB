package com.example.employeedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EmployeeDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "employees.db";
    public static final String TABLE_NAME = "employee";
    public static final String COL_ID = "empId", COL_NAME = "name", COL_DOJ = "doj",
            COL_AGE = "age", COL_DEPT = "dept", COL_SALARY = "salary";

    public EmployeeDatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " TEXT PRIMARY KEY, " +
                COL_NAME + " TEXT, " +
                COL_DOJ + " TEXT, " +
                COL_AGE + " INTEGER, " +
                COL_DEPT + " TEXT, " +
                COL_SALARY + " REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertEmployee(String id, String name, String doj, int age, String dept, double salary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_ID, id);
        cv.put(COL_NAME, name);
        cv.put(COL_DOJ, doj);
        cv.put(COL_AGE, age);
        cv.put(COL_DEPT, dept);
        cv.put(COL_SALARY, salary);
        long result = db.insertWithOnConflict(TABLE_NAME, null, cv, SQLiteDatabase.CONFLICT_IGNORE);
        return result != -1;
    }

    public Cursor getAllEmployees() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public boolean updateEmployee(String id, String name, String doj, int age, String dept, double salary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, name);
        cv.put(COL_DOJ, doj);
        cv.put(COL_AGE, age);
        cv.put(COL_DEPT, dept);
        cv.put(COL_SALARY, salary);
        int result = db.update(TABLE_NAME, cv, COL_ID + "=?", new String[]{id});
        return result > 0;
    }

    public Cursor search(String column, String value) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + column + " LIKE ?", new String[]{"%" + value + "%"});
    }

    public Cursor sort(String column) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + column, null);
    }

    public boolean deleteEmployee(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_ID + "=?", new String[]{id}) > 0;
    }
}
