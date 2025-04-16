package com.example.bookdatabase;

import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText bookId, bookName, authorName, pages;
    Button saveButton;
    BookDatabaseHelper dbHelper;
    GridView gridView;
    boolean isEditMode = false;
    String editingBookId = null;

    ListView listView;

    ArrayList<Book> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookId = findViewById(R.id.bookId);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bookName = findViewById(R.id.bookName);
        authorName = findViewById(R.id.authorName);
        pages = findViewById(R.id.pages);
        saveButton = findViewById(R.id.saveButton);

        dbHelper = new BookDatabaseHelper(this);

        gridView = findViewById(R.id.gridView);
        listView = findViewById(R.id.listView);
        registerForContextMenu(listView);
        registerForContextMenu(gridView);

        saveButton.setOnClickListener(v -> {
            if (isEditMode) {
                updateBook();
            } else {
                saveBook();
            }
        });
    }

    private void saveBook() {
        String id = bookId.getText().toString();
        String name = bookName.getText().toString();
        String author = authorName.getText().toString();
        String pagesCount = pages.getText().toString();

        if (id.isEmpty() || name.isEmpty() || author.isEmpty() || pagesCount.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isInserted = dbHelper.insertBook(id, name, author, pagesCount);
        if (isInserted) {
            Toast.makeText(this, "Book Saved!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error Saving Book", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateBook() {
        String name = bookName.getText().toString();
        String author = authorName.getText().toString();
        String pagesCount = pages.getText().toString();

        if (name.isEmpty() || author.isEmpty() || pagesCount.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isUpdated = dbHelper.updateBook(editingBookId, name, author, pagesCount);
        if (isUpdated) {
            Toast.makeText(this, "Book Updated!", Toast.LENGTH_SHORT).show();
            resetForm();
            showBooksInList(); // Refresh the list/grid view
        } else {
            Toast.makeText(this, "Error Updating Book", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetForm() {
        bookId.setText("");
        bookName.setText("");
        authorName.setText("");
        pages.setText("");
        isEditMode = false;
        editingBookId = null;
        saveButton.setText("Save");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Book selectedBook = books.get(info.position);
        String id = String.valueOf(selectedBook.getBookId());

        int itemId = item.getItemId();
        if (itemId == R.id.edit) {
            editBook(id);
            return true;
        } else if (itemId == R.id.delete) {
            deleteBook(id);
            return true;
        } else if (itemId == R.id.cancel) {
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

    private void editBook(String bookId) {
        // Fetch the book from the database to populate the fields for editing
        Cursor cursor = dbHelper.getBookById(bookId);
      //  bookId = findViewById(R.id.bookId);


        if (cursor != null && cursor.moveToFirst()) {
            //bookId.setText(cursor.getString(0));
            bookName.setText(cursor.getString(1));
            authorName.setText(cursor.getString(2));
            pages.setText(cursor.getString(3));

            //bookId.setEnabled(false); // Disable editing of Book ID
            isEditMode = true;
            editingBookId = bookId;
            saveButton.setText("Update");

            Toast.makeText(this, "Edit Book ID: " + bookId, Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteBook(String bookId) {
        boolean isDeleted = dbHelper.deleteBook(bookId);
        if (isDeleted) {
            Toast.makeText(this, "Book Deleted", Toast.LENGTH_SHORT).show();
            showBooksInList(); // Refresh view
        } else {
            Toast.makeText(this, "Error Deleting Book", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.view_grid) {
            showBooksInGrid();
            return true;
        } else if (itemId == R.id.view_list) {
            showBooksInList();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showBooksInGrid() {
        books.clear();
        Cursor cursor = dbHelper.getAllBooks();
        while (cursor.moveToNext()) {
            try {
                books.add(new Book(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3))
                ));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        BookAdapter adapter = new BookAdapter(this, books);
        gridView.setAdapter(adapter);
        listView.setAdapter(null); // Hide list view
    }

    private void showBooksInList() {
        books.clear();
        Cursor cursor = dbHelper.getAllBooks();
        while (cursor.moveToNext()) {
            try {
                books.add(new Book(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3))
                ));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        BookAdapter adapter = new BookAdapter(this, books);
        listView.setAdapter(adapter);
        gridView.setAdapter(null); // Hide grid view
    }
}
