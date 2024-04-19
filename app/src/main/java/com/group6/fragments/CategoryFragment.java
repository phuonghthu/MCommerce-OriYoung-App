package com.group6.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.adapters.CategoryAdapter;
import com.group6.adapters.MenuAdapter;
import com.group6.adapters.ProductAdapter;
import com.group6.models.Category;
import com.group6.oriyoung.CartActivity;
import com.group6.oriyoung.MenuSearch;
import com.group6.oriyoung.NotiActivity;
import com.group6.oriyoung.R;
import com.group6.oriyoung.databinding.FragmentCategoryBinding;
import com.group6.oriyoung.databinding.FragmentHomeBinding;

import java.util.ArrayList;


public class CategoryFragment extends Fragment {

    FragmentCategoryBinding binding;

    MenuAdapter menuAdapter;

    ArrayList<Category> category;

    private DatabaseReference myRef;
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        loadCategory();

        addEvents();
        return view;
    }

    private void loadCategory() {

        myRef = database.getReference("Category");
        category = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        category.add(issue.getValue(Category.class));
                    }
                }
                if (category.size() > 0) {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
                    binding.rvMenu.setLayoutManager(gridLayoutManager);
                    binding.rvMenu.setHasFixedSize(true);
                    menuAdapter = new MenuAdapter(getContext(), category);
                    binding.rvMenu.setAdapter(menuAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void addEvents() {
        binding.searchBar.btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CartActivity.class);
                startActivity(intent);
            }
        });

        binding.searchBar.btnNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NotiActivity.class);
                startActivity(intent);
            }
        });
        binding.searchBar.edtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Chuyển sang màn hình SearchActivity khi EditText nhận focus
                    Intent intent = new Intent(getActivity(), MenuSearch.class);
                    startActivity(intent);
                }
            }
        });
    }
}