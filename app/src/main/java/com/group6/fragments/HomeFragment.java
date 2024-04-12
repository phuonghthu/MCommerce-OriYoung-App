package com.group6.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.group6.adapters.CategoryAdapter;
import com.group6.adapters.ProductAdapter;
import com.group6.models.Category;
import com.group6.models.Product;
import com.group6.models.Review;
import com.group6.oriyoung.R;
import com.group6.oriyoung.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ImageSlider bannerSlider;

    CategoryAdapter categoryAdapter;
    ProductAdapter productAdapter;

    ArrayList<Category> category;
    ArrayList<Product> product;



    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        loadCategory();
        loadBanner();
        loadProduct();

        return view;
    }

    private void loadBanner() {
        bannerSlider = binding.bannerSlider;

        ArrayList<SlideModel> slideModels = new ArrayList<>(); //Create image list
        slideModels.add(new SlideModel(R.drawable.home_banner, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.home_banner, ScaleTypes.FIT));

        bannerSlider.setImageList(slideModels, ScaleTypes.FIT);
    }

    private void loadCategory() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.HORIZONTAL, false);
        binding.rvCategory.setLayoutManager(gridLayoutManager);
        binding.rvCategory.setHasFixedSize(true);
        category = new ArrayList<>();

        category.add(new Category(R.drawable.cate_taytrang, "Tẩy trang"));
        category.add(new Category(R.drawable.cate_toner, "Toner"));
        category.add(new Category(R.drawable.cate_srm, "Sữa rửa mặt"));
        category.add(new Category(R.drawable.cate_serum, "Serum"));
        category.add(new Category(R.drawable.cate_kemduong, "Kem dưỡng"));
        category.add(new Category(R.drawable.cate_mask, "Mặt nạ"));
        category.add(new Category(R.drawable.cate_sunscream, "Chống nắng"));
        category.add(new Category(R.drawable.cate_all, "Xem tất cả"));

        categoryAdapter = new CategoryAdapter(getContext(), category);
        binding.rvCategory.setAdapter(categoryAdapter);
    }

    private void loadProduct() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        binding.rvHotProduct.setLayoutManager(gridLayoutManager);
        binding.rvHotProduct.setHasFixedSize(true);
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
        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 0, "No", R.drawable.product_place_holder,
                true, true, 5.0, 100, null ));
        productAdapter = new ProductAdapter(getContext(), product);
        binding.rvHotProduct.setAdapter(productAdapter);

    }
}