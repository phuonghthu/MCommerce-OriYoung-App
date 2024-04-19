package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group6.adapters.ProductAdapter;
import com.group6.adapters.ReviewAdapter;
import com.group6.models.Product;
import com.group6.models.Review;
import com.group6.oriyoung.databinding.ActivityProductDetailBinding;

import java.util.ArrayList;

public class ProductDetail extends AppCompatActivity {

    ActivityProductDetailBinding binding;
//    ReviewAdapter adapter;
    ArrayList<Product> product;
    ArrayList<Review> reviews;
    TextView quantity;
    int total_quantity = 1;
    int lastVisibleItemIndex = 2; // Vị trí của item cuối cùng muốn hiển thị
    ProductAdapter productAdapter;
    ReviewAdapter reviewAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadReview();
        addEvents();
//        loadRelatedProducts();

        binding.layoutreview.txtRatingCount.setText("( " + String.valueOf(reviews.size()) + " lượt đánh giá )");
        binding.layoutproduct.txtRatingCount.setText("( " + String.valueOf(reviews.size()) + " lượt đánh giá )");

        quantity = findViewById(R.id.txtQuantity);
//        addtoCart = findViewById(R.id.btnAddToCart);
//
    }


    private void addEvents() {

        binding.GroupButtons.btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetail.this, CartActivity.class);
                startActivity(intent);
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ProductDetail.this, ProductList.class);
//                startActivity(intent);
            }
        });
        binding.GroupButtons.txtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (total_quantity < 100){
                    total_quantity ++;
                    quantity.setText(String.valueOf(total_quantity));
                }


            }
        });

        binding.GroupButtons.txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (total_quantity > 1) {
                    total_quantity--;
                    quantity.setText(String.valueOf(total_quantity));
                }
            }
        });
        binding.layoutreview.imvViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductDetail.this, ReviewDetail.class));
            }
        });

//        binding.layoutreview.txtRatingCount.setText(String.valueOf());

    }
//    private void loadRelatedProducts() {
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
//        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
//        binding.rvRelatedProduct.setLayoutManager(layoutManager);
//        product = new ArrayList<>();
//
//        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
//                100000, 0, "No", R.drawable.product_place_holder,
//                true, true, 5.0, 100, null ));
//        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
//                100000, 0, "No", R.drawable.product_place_holder,
//                true, true, 5.0, 100, null ));
//        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
//                100000, 0, "No", R.drawable.product_place_holder,
//                true, true, 5.0, 100, null ));
//        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
//                100000, 0, "No", R.drawable.product_place_holder,
//                true, true, 5.0, 100, null ));
//        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
//                100000, 0, "No", R.drawable.product_place_holder,
//                true, true, 5.0, 100, null ));
//
//        productAdapter= new ProductAdapter(getApplicationContext(), product);
//        binding.rvRelatedProduct.setAdapter(productAdapter);
//
//    }
    private void loadReview() {
        reviews = new ArrayList<>();
        reviews.add(new Review(1, "Hương Giang",5.0, "Mới test nhưng thấy cũng oke, shop thì nhiệt tình", 1,
                1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, R.drawable.product_place_holder, true,  true,  5.0));
        reviews.add(new Review(1, "Hương Giang",5.0, "Mới test nhưng thấy cũng oke, shop thì nhiệt tình", 1,
                1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, R.drawable.product_place_holder, true,  true,  5.0));
        reviews.add(new Review(1, "Hương Giang",5.0, "Mới test nhưng thấy cũng oke, shop thì nhiệt tình", 1,
                1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, R.drawable.product_place_holder, true,  true,  5.0));
        reviews.add(new Review(1, "Hương Giang",5.0, "Mới test nhưng thấy cũng oke, shop thì nhiệt tình", 1,
                1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, R.drawable.product_place_holder, true,  true,  5.0));
        reviews.add(new Review(1, "Hương Giang",5.0, "Mới test nhưng thấy cũng oke, shop thì nhiệt tình", 1,
                1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, R.drawable.product_place_holder, true,  true,  5.0));
        reviewAdapter = new ReviewAdapter(this, R.layout.item_review_layout, reviews);
        binding.layoutreview.lvReview.setAdapter(reviewAdapter);



    }



    }
