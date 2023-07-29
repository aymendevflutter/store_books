package com.example.aymen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.aymen.adapters.CategoryAdapter;
import com.example.aymen.objects.Category;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the RecyclerView and its layout manager
        categoryRecyclerView = findViewById(R.id.category_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);

        // Create your category data
        categories = new ArrayList<>();
        categories.add(new Category("Fictional", R.drawable.fictional));
        categories.add(new Category("Comics", R.drawable.comics));
        categories.add(new Category("Self Help", R.drawable.self));
        categories.add(new Category("Autobiography", R.drawable.biography3));

        // Initialize the CategoryAdapter with the category data
        categoryAdapter = new CategoryAdapter(categories, this);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }
}
