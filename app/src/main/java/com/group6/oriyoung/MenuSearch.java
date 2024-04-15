package com.group6.oriyoung;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import com.group6.adapters.CategoryNameAdapter;
import com.group6.adapters.SearchListProductAdapter;
import com.group6.models.Category;
import com.group6.models.Product;
import com.group6.oriyoung.databinding.ActivityMenuSearchBinding;
import java.util.ArrayList;

public class MenuSearch extends AppCompatActivity {
    ActivityMenuSearchBinding binding;
    CategoryNameAdapter categoryNameAdapter;
    SearchListProductAdapter productAdapter;
    ListView listView;

    ArrayList<Product> products;
    ArrayList<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize GridView for categories
        GridView gridView = findViewById(R.id.category_grid_view);

        // Initialize ListView for products
        listView = findViewById(R.id.lvSearchSanpham);

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

        // Initialize Adapter and set up GridView for categories
        categoryNameAdapter = new CategoryNameAdapter(this, categories);
        gridView.setAdapter(categoryNameAdapter);

        // Initialize list of products
        products = new ArrayList<>();
        products.add(new Product(0, 0,"Nước Tẩy Trang L'Oreal Tươi Mát Cho Da Dầu, Hỗn Hợp 400ml\n" +
                "    Micellar Water 3-in-1 Refreshing Even For Sensitive Skin", 100000, 0, null, R.drawable.product_place_holder, false, false, 0, 0,null));
        // Add products here...

        // Initialize Adapter and set up ListView for products
        productAdapter = new SearchListProductAdapter(this, products);
        listView.setAdapter(productAdapter);
    }
}
