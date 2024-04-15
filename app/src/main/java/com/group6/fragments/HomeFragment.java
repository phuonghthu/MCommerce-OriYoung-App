package com.group6.fragments;

import android.content.Intent;
import android.os.Bundle;

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
    ArrayList<Brand> brand;



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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.HORIZONTAL, false);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
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

    private void loadHotProduct() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        binding.rvHotProduct.setLayoutManager(gridLayoutManager);
        binding.rvHotProduct.setHasFixedSize(true);
        product = new ArrayList<>();

        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 0, "No", R.drawable.product_place_holder,
                true, false, 5.0, 100, null ));
        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 0, "No", R.drawable.product_place_holder,
                true, false, 5.0, 100, null ));
        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 0, "No", R.drawable.product_place_holder,
                true, false, 5.0, 100, null ));
        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 0, "No", R.drawable.product_place_holder,
                true, false, 5.0, 100, null ));
        productAdapter = new ProductAdapter(getContext(), product);
        binding.rvHotProduct.setAdapter(productAdapter);

    }

    private void loadSaleProduct() {

        int numberOfColumns = 2; // Number of columns

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.rvSaleProduct.setLayoutManager(layoutManager);
        product = new ArrayList<>();

        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 40, "No", R.drawable.product_place_holder,
                true, true, 5.0, 100, null ));
        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 40, "No", R.drawable.product_place_holder,
                true, true, 5.0, 100, null ));
        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 40, "No", R.drawable.product_place_holder,
                true, true, 5.0, 100, null ));
        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 40, "No", R.drawable.product_place_holder,
                true, true, 5.0, 100, null ));
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
        binding.searchBar.edtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang màn hình SearchActivity khi click vào thanh tìm kiếm
                Intent intent = new Intent(getActivity(), MenuSearch.class);
                startActivity(intent);
            }
        });
    }

}