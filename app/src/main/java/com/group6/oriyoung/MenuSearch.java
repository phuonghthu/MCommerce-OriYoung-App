package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.group6.adapters.CategoryAdapter;
import com.group6.adapters.CategoryNameAdapter;
import com.group6.adapters.SearchListProductAdapter;
import com.group6.models.Category;
import com.group6.models.Product;
import com.group6.oriyoung.databinding.ActivityMenuSearchBinding;

import java.util.ArrayList;

public class MenuSearch extends BaseActivity {
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
        addEvents();
        onQuery();
        loadCategory();


        // Initialize GridView for categories


        // Initialize ListView for products
        listView = findViewById(R.id.lvSearchSanpham);


        // Initialize list of products
//        products = new ArrayList<>();
//        products.add(new Product(0, 0,"Nước Tẩy Trang L'Oreal Tươi Mát Cho Da Dầu, Hỗn Hợp 400ml Micellar Water 3-in-1 Refreshing Even For Sensitive Skin", 100000, 0, null, R.drawable.product_place_holder, false, false, 0, 0,null));
//        products.add(new Product(0, 0,"Bông Tẩy Trang L'Oreal Tươi Mát Cho Da Dầu, Hỗn Hợp 400ml Micellar Water 3-in-1 Refreshing Even For Sensitive Skin", 100000, 0, null, R.drawable.product_place_holder, false, false, 0, 0,null));
//        products.add(new Product(0, 0,"Sữa rửa mặt L'Oreal Tươi Mát Cho Da Dầu, Hỗn Hợp 400ml Micellar Water 3-in-1 Refreshing Even For Sensitive Skin", 100000, 0, null, R.drawable.product_place_holder, false, false, 0, 0,null));
//        products.add(new Product(0, 0,"Chai Tẩy Trang L'Oreal Tươi Mát Cho Da Dầu, Hỗn Hợp 400ml Micellar Water 3-in-1 Refreshing Even For Sensitive Skin", 100000, 0, null, R.drawable.product_place_holder, false, false, 0, 0,null));
//
//        // Add products here...
//
//        // Initialize Adapter and set up ListView for products
//        productAdapter = new SearchListProductAdapter(this, products);
//        listView.setAdapter(productAdapter);

        // Hide ListView initially
        listView.setVisibility(View.GONE);


    }

    private void loadCategory() {
        DatabaseReference myRef = database.getReference("Category");
        categories = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        categories.add(issue.getValue(Category.class));
                    }
                }
                if (categories.size() > 0) {
                    GridView gridView = findViewById(R.id.category_grid_view);
                    categoryNameAdapter = new CategoryNameAdapter(MenuSearch.this, categories);
                    gridView.setAdapter(categoryNameAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void onQuery() {
        // Show ListView when search bar gains focus
        binding.searchBar.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    listView.setVisibility(View.VISIBLE);
                } else {
                    listView.setVisibility(View.GONE);
                }
            }
        });

        binding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Gọi phương thức filter của adapter khi văn bản tìm kiếm thay đổi
                productAdapter.filter(newText);
                return true;
            }
        });
    }


    private void addEvents() {
        binding.imvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi nhấn vào ImageView
                Intent intent = new Intent(MenuSearch.this, HomeActivity.class);
                startActivity(intent);
                // Kết thúc Activity hiện tại nếu bạn muốn
                finish();
            }
        });
    }
}
