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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.group6.adapters.CatalogAdapter;
import com.group6.adapters.ProductAdapter;
import com.group6.models.Product;
import com.group6.oriyoung.databinding.ActivityProductCatalogBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog extends BaseActivity {
    ActivityProductCatalogBinding binding;
    ArrayList<Product> catalog;
    CatalogAdapter catalogAdapter;

    int categoryID;
    String categoryName;
    String searchText;
    boolean isSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductCatalogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getIntentExtra();
        addIntent();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
    private void getIntentExtra() {
        categoryID = getIntent().getIntExtra("categoryID", 0);
        categoryName = getIntent().getStringExtra("categoryName");
        searchText = getIntent().getStringExtra("text");
        isSearch = getIntent().getBooleanExtra("isSearch", false);

            binding.toolbar.toolbarTitle.setText(categoryName);
            binding.toolbar.btnBack.setOnClickListener(v -> finish());
            loadDataCatalog();
            filterEvent();

    }
    private void loadDataCatalog() {
        DatabaseReference myRef = database.getReference("Product");
        binding.progressBarListProduct.setVisibility(View.VISIBLE);
        catalog = new ArrayList<>();

        Query query;
        if (isSearch) {
            query = myRef.orderByChild("productName").startAt(searchText).endAt(searchText + '\uf8ff');
        } else {
            if (categoryID == 7) {
                query = myRef;
            } else {
                query = myRef.orderByChild("categoryID").equalTo(categoryID);
            }
        }

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        catalog.add(issue.getValue(Product.class));
                    }
                    if(catalog.size()>0){
                        GridLayoutManager layoutManager = new GridLayoutManager(ProductCatalog.this, 2,  RecyclerView.VERTICAL, false);
                        binding.rvCatalog.setLayoutManager(layoutManager);
                        binding.rvCatalog.setHasFixedSize(true);
                        catalogAdapter = new CatalogAdapter(getApplicationContext(), catalog);
                        binding.rvCatalog.setAdapter(catalogAdapter);
                    }
                    binding.progressBarListProduct.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

//============Xử lý dialog=============================
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
//        LinearLayout btnSortCancel = dialog.findViewById(R.id.btnSortCancel);
//        btnSortCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setWindowAnimations(R.style.DialogAnimation);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
    //================Xử lý Intent ==================================
    private void addIntent() {

    }




}