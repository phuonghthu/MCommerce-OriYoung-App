package com.group6.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group6.adapters.BrandAdapter;
import com.group6.adapters.FavoriteAdapter;
import com.group6.adapters.SaleProductAdapter;
import com.group6.models.Category;
import com.group6.models.Product;
import com.group6.oriyoung.R;
import com.group6.oriyoung.databinding.FragmentHomeBinding;
import com.group6.oriyoung.databinding.FragmentWishlistBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WishlistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WishlistFragment extends Fragment {

    FragmentWishlistBinding binding;

    FavoriteAdapter favAdapter;

    ArrayList<Product> product;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWishlistBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        setToolbar();
        loadFavProduct();

        return view;
    }

    private void setToolbar() {
        binding.toolbar.toolbarTitle.setText("Sản phẩm yêu thích");
    }

    private void loadFavProduct() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        binding.rvFav.setLayoutManager(gridLayoutManager);
        binding.rvFav.setHasFixedSize(true);
        product = new ArrayList<>();

        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 0, "No", R.drawable.product_place_holder,
                true, true, 5.0, 100, null ));
        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 0, "No", R.drawable.product_place_holder,
                true, true, 5.0, 100, null ));
        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 0, "No", R.drawable.product_place_holder,
                true, true, 5.0, 100, null ));

        favAdapter = new FavoriteAdapter(getContext(), product);
        binding.rvFav.setAdapter(favAdapter);
    }
}