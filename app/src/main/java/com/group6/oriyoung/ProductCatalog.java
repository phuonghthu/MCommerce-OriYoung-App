package com.group6.oriyoung;

import static java.security.AccessController.getContext;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group6.adapters.CatalogAdapter;
import com.group6.adapters.ProductAdapter;
import com.group6.models.Product;
import com.group6.oriyoung.databinding.ActivityProductCatalogBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog extends AppCompatActivity {
    ActivityProductCatalogBinding binding;
    ArrayList<Product> catalog;
    CatalogAdapter catalogAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductCatalogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadDataCatalog();
        filterEvent();
        addIntent();
        addEvent();

    }

    private void loadDataCatalog() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2,  RecyclerView.VERTICAL, false);
        binding.rvCatalog.setLayoutManager(layoutManager);
        binding.rvCatalog.setHasFixedSize(true);
        catalog = new ArrayList<>();
        catalog.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 0, "No", R.drawable.product_place_holder,
                true, true, 5.0, 100, null ));
        catalog.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 0, "No", R.drawable.product_place_holder,
                true, true, 5.0, 100, null ));
        catalog.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 0, "No", R.drawable.product_place_holder,
                true, true, 5.0, 100, null ));
        catalog.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 0, "No", R.drawable.product_place_holder,
                true, true, 5.0, 100, null ));
        catalog.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 0, "No", R.drawable.product_place_holder,
                true, true, 5.0, 100, null ));
        catalog.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 0, "No", R.drawable.product_place_holder,
                true, true, 5.0, 100, null ));
        catalog.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 0, "No", R.drawable.product_place_holder,
                true, true, 5.0, 100, null ));
        catalog.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 0, "No", R.drawable.product_place_holder,
                true, true, 5.0, 100, null ));
        catalogAdapter = new CatalogAdapter(getApplicationContext(), catalog);
        binding.rvCatalog.setAdapter(catalogAdapter);
        List<Product> initialProducts = catalog.subList(0, 4);
        ArrayList<Product> initialProductsList = new ArrayList<>(initialProducts);
        catalogAdapter.addItems(initialProductsList);
    }
    private void addEvent() {
        binding.imvSeemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeMoreProduct();
            }
        });
    }
    //
    private void seeMoreProduct() {
        int currentSize = 4;
        int end = Math.min(currentSize + 4, catalog.size());
        List<Product> newProducts = catalog.subList(currentSize, end);
        ArrayList<Product> newProductsList = new ArrayList<>(newProducts);
        catalogAdapter.addItems(newProductsList);
        currentSize += 4;
        if(end >=catalog.size()){
            binding.imvSeemore.setVisibility(View.GONE);
        }
    }
    private void filterEvent() {
        binding.imvSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSortBottomSheet();
            }
        });
        binding.imvFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilterBottomsheet();
            }
        });
        binding.imvCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCatalogBottomSheet();
            }
        });

    }
    private void showCatalogBottomSheet() {
        Dialog dialog = new Dialog(ProductCatalog.this);
        dialog.setContentView(R.layout.bottomsheet_list);
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setWindowAnimations(R.style.DialogAnimation);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
    private void showFilterBottomsheet() {
        Dialog dialog = new Dialog(ProductCatalog.this);
        dialog.setContentView(R.layout.bottomsheet_filter);
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setWindowAnimations(R.style.DialogAnimation);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void showSortBottomSheet() {
        Dialog dialog = new Dialog(ProductCatalog.this);
        dialog.setContentView(R.layout.bottomsheet_sort);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setWindowAnimations(R.style.DialogAnimation);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
    private void addIntent() {
        binding.imvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductCatalog.this, CartActivity.class);
                startActivity(intent);
            }
        });
//        binding.imvSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }




}