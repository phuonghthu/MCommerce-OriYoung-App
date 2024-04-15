package com.group6.oriyoung;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group6.adapters.FavoriteAdapter;
import com.group6.adapters.ProductAdapter;
import com.group6.adapters.ReviewAdapter;
import com.group6.models.Product;
import com.group6.models.Review;
import com.group6.oriyoung.databinding.ActivityProductDetailBinding;

import java.util.ArrayList;

public class ProductDetail extends AppCompatActivity {

    ActivityProductDetailBinding binding;
//    ReviewAdapter adapter;
//    ArrayList<Review>reviews, product;
//    TextView quantity;
//    Button addtoCart;
//    int total_quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        loadData();
        addEvents();
//        loadRelatedProducts();

//        quantity = findViewById(R.id.txtQuantity);
//        addtoCart = findViewById(R.id.btnAddToCart);
//
    }

//    private void loadRelatedProducts() {
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this
//                , LinearLayoutManager.HORIZONTAL ,false);
//        binding.rvRelatedProduct.setLayoutManager(layoutManager);
//        binding.rvRelatedProduct.setHasFixedSize(true);
//         product = new ArrayList<>();


//    }

//    private void loadData() {
//        reviews = new ArrayList<>();
//        reviews.add(new Review(1, "Cao Hương Giang", 5, "Mới test nhưng thấy cũng oke, shop thì nhiệt tình", null));
//        reviews.add(new Review(1, "Cao Hương Giang", 5, "Mới test nhưng thấy cũng oke, shop thì nhiệt tình", null));
//        adapter = new ReviewAdapter(ProductDetail.this, R.layout.item_review_layout, reviews);
//        binding.layoutreview.lvReview.setAdapter(adapter);
//
//
//    }

    private void addEvents() {

        binding.GroupButton.btnBuyNow.setOnClickListener(new View.OnClickListener() {
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
//        binding.GroupButton.txtAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (total_quantity < 10){
//                    total_quantity ++;
//                    quantity.setText(String.valueOf(total_quantity));
//                }
//
//
//            }
//        });
//        binding.GroupButton.txtDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (total_quantity > 10) {
//                    total_quantity--;
//                    quantity.setText(String.valueOf(total_quantity));
//                }
//            }
//        });

    }
}
