package com.group6.oriyoung;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.group6.oriyoung.databinding.ActivitySettingBinding;
public class Setting extends AppCompatActivity {
    ActivitySettingBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
    }
}
