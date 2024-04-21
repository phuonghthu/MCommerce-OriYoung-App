package com.group6.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewbinding.ViewBinding;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.group6.adapters.BrandAdapter;
import com.group6.adapters.CategoryAdapter;
import com.group6.adapters.ProductAdapter;
import com.group6.adapters.SaleProductAdapter;
import com.group6.models.Brand;
import com.group6.models.Category;
import com.group6.models.Product;
import com.group6.models.Review;
import com.group6.oriyoung.CartActivity;
import com.group6.oriyoung.MenuSearch;
import com.group6.oriyoung.NotiActivity;
import com.group6.oriyoung.R;
import com.google.firebase.database.DatabaseReference;
import com.group6.oriyoung.SearchBarActivity;
import com.group6.oriyoung.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ImageSlider bannerSlider;

    CategoryAdapter categoryAdapter;
    ProductAdapter productAdapter;
    SaleProductAdapter saleProductAdapter;
    BrandAdapter brandAdapter;

    ArrayList<Category> category;
    ArrayList<Product> product;
    ArrayList<Product> hotproduct;
    ArrayList<Brand> brand;
    private DatabaseReference myRef;
    FirebaseDatabase database = FirebaseDatabase.getInstance();



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
        loadHotProduct();
        loadSaleProduct();
        loadBrand();
        addEvents();


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
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    categoryAdapter = new CategoryAdapter(getContext(), category);
                    binding.rvCategory.setAdapter(categoryAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadHotProduct() {
        myRef = database.getReference("Product");
        binding.progressBarHotProduct.setVisibility(View.VISIBLE);

        hotproduct = new ArrayList<>();
        Query query = myRef.orderByChild("isHot").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        hotproduct.add(issue.getValue(Product.class));
                        }
                    }
                    if (hotproduct.size() > 0) {
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
                        binding.rvHotProduct.setLayoutManager(gridLayoutManager);
                        binding.rvHotProduct.setHasFixedSize(true);
                        productAdapter = new ProductAdapter(getContext(), hotproduct);

                        binding.rvHotProduct.setAdapter(productAdapter);
                    }
                    binding.progressBarHotProduct.setVisibility(View.GONE);
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadSaleProduct() {
        myRef = database.getReference("Product");
        binding.progressBarSaleProduct.setVisibility(View.VISIBLE);

        product = new ArrayList<>();
        Query query = myRef.orderByChild("productDiscountPercent").startAt(0.00001);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        Product saleProduct = issue.getValue(Product.class);
                        if (saleProduct.getProductDiscountPercent() > 0) {
                            product.add(saleProduct);
                        }
                    }
                    if (product.size() > 0) {
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
                        binding.rvSaleProduct.setLayoutManager(layoutManager);
                        saleProductAdapter = new SaleProductAdapter(getContext(), product);
                        binding.rvSaleProduct.setAdapter(saleProductAdapter);

                        DisplayMetrics displayMetrics = new DisplayMetrics();
                        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                        int parentWidth = displayMetrics.widthPixels;
                        float paddingDp = 20; // Padding ngang theo dp
                        float density = displayMetrics.density;

                        // Chuyển đổi padding từ dp sang px
                        int paddingPx = (int) (paddingDp * density);

                        // Tính toán kích thước itemWidth
                        int itemWidth = (parentWidth - (2 * paddingPx)) / 2;

                        saleProductAdapter.setItemWidth(itemWidth);
                    }
                    binding.progressBarSaleProduct.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void loadBrand() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.HORIZONTAL, false);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager.canScrollHorizontally();
        brand = new ArrayList<>();

        brand.add(new Brand(R.drawable.br_cosrx, "null"));
        brand.add(new Brand(R.drawable.br_beplain, "null"));
        brand.add(new Brand(R.drawable.br_klairs, "null"));
        brand.add(new Brand(R.drawable.br_cocoon, "null"));
        brand.add(new Brand(R.drawable.br_skin1004, "null"));
        brand.add(new Brand(R.drawable.br_anua, "null"));
        brand.add(new Brand(R.drawable.br_innisfree, "null"));
        brand.add(new Brand(R.drawable.br_roundlab, "null"));
        brand.add(new Brand(R.drawable.br_comem, "null"));
        brand.add(new Brand(R.drawable.br_simple, "null"));
        brand.add(new Brand(R.drawable.br_herbario, "null"));
        brand.add(new Brand(R.drawable.br_vegick, "null"));


        brandAdapter = new BrandAdapter(getContext(), brand);
        binding.rvBrand.setAdapter(brandAdapter);

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