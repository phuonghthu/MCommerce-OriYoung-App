package com.group6.oriyoung;

import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import com.group6.adapters.CategoryNameAdapter;
import com.group6.models.Category;
import com.group6.oriyoung.databinding.ActivityMenuSearchBinding;
import java.util.ArrayList;

public class MenuSearch extends AppCompatActivity {
    ActivityMenuSearchBinding binding;
    CategoryNameAdapter CategoryNameAdapter;

    ArrayList<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize list of categories
        categories = new ArrayList<>();
        categories.add(new Category(0, "Tẩy trang"));
        categories.add(new Category(0, "Sữa rửa mặt"));
        categories.add(new Category(0, "Serum"));
        categories.add(new Category(0, "Kem dưỡng"));
        categories.add(new Category(0, "Chống nắng"));
        categories.add(new Category(0, "Mặt nạ"));
        categories.add(new Category(0, "Xịt khoáng"));
        categories.add(new Category(0, "Dưỡng mắt"));
        categories.add(new Category(0, "Dưỡng môi"));
        // Add other categories here...

        // Initialize Adapter and set up GridView
        CategoryNameAdapter = new CategoryNameAdapter(this, categories);
        GridView gridView = findViewById(R.id.category_grid_view);
        gridView.setAdapter(CategoryNameAdapter);
    }
}
