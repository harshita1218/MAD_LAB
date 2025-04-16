package com.example.studentdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "student_database";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "students";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ROLL_NO = "roll_no";
    public  static final String COLUMN_MARKS1 = "marks1";
    public static final String COLUMN_MARKS2 = "marks2";
    public static final String COLUMN_MARKS3 = "marks3";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ROLL_NO + " TEXT PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_MARKS1 + " INTEGER, " +
                COLUMN_MARKS2 + " INTEGER, " +
                COLUMN_MARKS3 + " INTEGER)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addStudent(String name, String rollNo, int marks1, int marks2, int marks3) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_ROLL_NO, rollNo);
        values.put(COLUMN_MARKS1, marks1);
        values.put(COLUMN_MARKS2, marks2);
        values.put(COLUMN_MARKS3, marks3);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}
