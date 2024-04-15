package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.group6.fragments.AccountFirstFragment;
import com.group6.oriyoung.databinding.ActivityUserInfoEdtailBinding;

public class UserInfoDetail extends AppCompatActivity {
    ActivityUserInfoEdtailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityUserInfoEdtailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.toolbarTitle.setText("Thông tin cá nhân");
        addEvents();
    }

    private void addEvents() {
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoDetail.this, UserInformation.class);
                finish();
            }
        });

    }
}