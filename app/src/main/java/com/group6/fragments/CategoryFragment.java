package com.group6.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group6.adapters.MenuAdapter;
import com.group6.adapters.ProductAdapter;
import com.group6.models.Category;
import com.group6.oriyoung.CartActivity;
import com.group6.oriyoung.NotiActivity;
import com.group6.oriyoung.R;
import com.group6.oriyoung.databinding.FragmentCategoryBinding;
import com.group6.oriyoung.databinding.FragmentHomeBinding;

import java.util.ArrayList;


public class CategoryFragment extends Fragment {

    FragmentCategoryBinding binding;

    MenuAdapter menuAdapter;

    ArrayList<Category> category;



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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        binding.rvMenu.setLayoutManager(gridLayoutManager);
        binding.rvMenu.setHasFixedSize(true);
        category = new ArrayList<>();

        category.add(new Category(R.drawable.cate_taytrang, "Tẩy trang"));
        category.add(new Category(R.drawable.cate_toner, "Toner"));
        category.add(new Category(R.drawable.cate_srm, "Sữa rửa mặt"));
        category.add(new Category(R.drawable.cate_serum, "Serum"));
        category.add(new Category(R.drawable.cate_kemduong, "Kem dưỡng"));
        category.add(new Category(R.drawable.cate_mask, "Mặt nạ"));
        category.add(new Category(R.drawable.cate_sunscream, "Chống nắng"));
        category.add(new Category(R.drawable.cate_all, "Xem tất cả"));

        menuAdapter = new MenuAdapter(getContext(), category);
        binding.rvMenu.setAdapter(menuAdapter);
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
    }
}