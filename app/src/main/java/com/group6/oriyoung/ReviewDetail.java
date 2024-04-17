package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group6.adapters.ReviewAdapter;
import com.group6.models.Review;
import com.group6.oriyoung.databinding.ActivityReviewDetailBinding;

import java.util.ArrayList;

public class ReviewDetail extends AppCompatActivity {
    ActivityReviewDetailBinding binding;
    ReviewAdapter adapter;
    ArrayList<Review> reviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReviewDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
        loadReview();

        binding.reviewheader.toolbarTitle.setText("Tất cả đánh giá");
        binding.txtRatingCount.setText("( " + String.valueOf(reviews.size()) + " lượt đánh giá )");
    }

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
        adapter = new ReviewAdapter(this, R.layout.item_review_layout, reviews);
        binding.lvReviewList.setAdapter(adapter);


    }

    private void addEvents() {
        binding.reviewheader.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReviewDetail.this, ProductDetail.class));
            }
        });
    }


}