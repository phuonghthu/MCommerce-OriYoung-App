package com.group6.oriyoung;

import static androidx.core.app.NavUtils.getParentActivityName;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.group6.adapters.ProductAdapter;
import com.group6.adapters.ReviewAdapter;
import com.group6.fragments.HomeFragment;
import com.group6.models.Product;
import com.group6.models.Review;
import com.group6.oriyoung.databinding.ActivityProductDetailBinding;

import java.util.ArrayList;

public class ProductDetail extends BaseActivity {

    ActivityProductDetailBinding binding;
    private Product object;
    private String callingActivity;
//    ReviewAdapter adapter;
    ArrayList<Product> product;
    ArrayList<Review> reviews;
    TextView quantity;
    private int total_quantity = 1;
    int lastVisibleItemIndex = 2; // Vị trí của item cuối cùng muốn hiển thị
    ProductAdapter productAdapter;
    ReviewAdapter reviewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        
        getIntentExtra();
        setVariable();
        addEvents();



        quantity = findViewById(R.id.txtQuantity);
//        addtoCart = findViewById(R.id.btnAddToCart);
//
    }


    private void getIntentExtra() {
        object= (Product) getIntent().getSerializableExtra("object");
            }

    private void setVariable() {
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Load ảnh từ path
        Glide.with(ProductDetail.this).load(object.getImagePath()).into(binding.imvProductThumb);
        binding.txtProductPrice.setText(String.valueOf(Math.round(object.getProductPrice())) + " VNĐ");
        binding.txtProductName.setText(object.getProductName());
        binding.txtProductDescription.setText(object.getProductDescription());

        // Initialize reviews ArrayList
        reviews = new ArrayList<>();

        // Update rating value and review count
        binding.txtRatingValue.setText(String.valueOf((int) object.getRatingValue()));
        binding.txtRatingCount.setText("( " + String.valueOf(reviews.size()) + " lượt đánh giá )");
    }




    private void addEvents() {


        binding.GroupButtons.btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetail.this, CartActivity.class);
                startActivity(intent);
            }
        });

        binding.toolbar.toolbarTitle.setText("Chi tiết sản phẩm");
        binding.GroupButtons.imvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (total_quantity < 100){
                    total_quantity ++;
                    quantity.setText(String.valueOf(total_quantity));
                }


            }
        });

        binding.GroupButtons.imvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (total_quantity > 1) {
                    total_quantity--;
                    quantity.setText(String.valueOf(total_quantity));
                }
            }
        });
        binding.reviewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductDetail.this, ReviewDetail.class));
            }
        });


    }

}
