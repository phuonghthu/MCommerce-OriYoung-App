package com.group6.oriyoung;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.group6.oriyoung.databinding.ActivityChangePasswordBinding;

public class ChangePassword extends AppCompatActivity {
    ActivityChangePasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }

}