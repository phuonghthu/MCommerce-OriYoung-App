package com.group6.oriyoung;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.group6.oriyoung.databinding.ActivityUserInfoEdtailBinding;

public class UserInfoEdtail extends AppCompatActivity {
    ActivityUserInfoEdtailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityUserInfoEdtailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}