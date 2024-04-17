package com.group6.oriyoung;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.group6.oriyoung.databinding.ActivityReviewDetailBinding;

public class ReviewDetail extends AppCompatActivity {
    ActivityReviewDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReviewDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();

        binding.reviewheader.toolbarTitle.setText("Tất cả đánh giá");
    }

    private void addEvents() {
    }
}