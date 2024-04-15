package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.group6.fragments.AccountFirstFragment;
import com.group6.fragments.HomeFragment;
import com.group6.oriyoung.databinding.ActivityMyVoucherBinding;

public class MyVoucher extends AppCompatActivity {

    ActivityMyVoucherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyVoucherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
        binding.toolbar.toolbarTitle.setText("Ưu đãi của tôi");
    }

    private void addEvents() {
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyVoucher.this, AccountFirstFragment.class);
                finish();
            }
        });
        binding.btnGoToStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyVoucher.this, HomeFragment.class);
                startActivity(intent);
            }
        });
    }
}