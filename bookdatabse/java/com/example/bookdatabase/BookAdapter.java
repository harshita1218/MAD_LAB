package com.example.bookdatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ArrayAdapter;
import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {

    private Context context;
    private List<Book> books;

    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
        this.context = context;
        this.books = books;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.book_item, parent, false);
        }

        Book currentBook = books.get(position);

        TextView bookIdText = convertView.findViewById(R.id.bookId);
        TextView bookNameText = convertView.findViewById(R.id.bookName);
        TextView authorNameText = convertView.findViewById(R.id.authorName);
        TextView pagesText = convertView.findViewById(R.id.pages);

        bookIdText.setText(String.valueOf(currentBook.getBookId()));
        bookNameText.setText(currentBook.getBookName());
        authorNameText.setText(currentBook.getAuthorName());
        pagesText.setText(String.valueOf(currentBook.getPages()));

        return convertView;
    }
}
