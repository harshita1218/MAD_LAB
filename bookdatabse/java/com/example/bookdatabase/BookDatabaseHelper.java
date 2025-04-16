package com.example.bookdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "bookDatabase.db";
    public static final String TABLE_NAME = "books";
    public static final String COL_ID = "id"; // Column name for ID
    public static final String COL_NAME = "name"; // Column name for name
    public static final String COL_AUTHOR = "author"; // Column name for author
    public static final String COL_PAGES = "pages"; // Column name for pages

    public BookDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Updated query with correct column names
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID + " TEXT PRIMARY KEY, "
                + COL_NAME + " TEXT, "
                + COL_AUTHOR + " TEXT, "
                + COL_PAGES + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert book into database
    public boolean insertBook(String id, String name, String author, String pages) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, id);
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_AUTHOR, author);
        contentValues.put(COL_PAGES, pages);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    // Get all books from database
    public Cursor getAllBooks() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    // Delete book by ID
    public boolean deleteBook(String bookId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_ID + "=?", new String[]{bookId}) > 0;
    }

    // Update book details by ID

    public boolean updateBook(String id, String name, String author, String pages) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_AUTHOR, author);
        values.put(COL_PAGES, pages);
        int result = db.update(TABLE_NAME, values, COL_ID + " = ?", new String[]{id});
        return result > 0;
    }

    // Get a book by ID
    public Cursor getBookById(String bookId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(
                TABLE_NAME,            // Table name
                new String[] {         // Columns to retrieve
                        COL_ID, COL_NAME, COL_AUTHOR, COL_PAGES
                },
                COL_ID + " = ?",       // Selection criteria
                new String[] {bookId}, // Arguments for the selection criteria
                null,                  // Group by
                null,                  // Having
                null                   // Order by
        );
    }
}
