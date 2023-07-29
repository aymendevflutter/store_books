package com.example.aymen.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aymen.R;
import com.example.aymen.objects.Book;

import java.util.List;
public class BookListAdapter extends ArrayAdapter<Book> {

    private LayoutInflater inflater;

    public BookListAdapter(Context context, List<Book> bookList) {
        super(context, 0, bookList);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_book, parent, false);
            holder = new ViewHolder();
            holder.titleTextView = convertView.findViewById(R.id.book_title);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Book book = getItem(position);

        if (book != null) {
            holder.titleTextView.setText(book.getTitle());


        }

        return convertView;
    }

    private static class ViewHolder {
        TextView titleTextView;
        TextView authorTextView;
        TextView priceTextView;
    }
}

