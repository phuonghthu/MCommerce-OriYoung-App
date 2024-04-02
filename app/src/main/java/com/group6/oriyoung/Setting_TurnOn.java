package com.group6.oriyoung;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.group6.oriyoung.databinding.ActivitySettingTurnOnBinding;
public class Setting_TurnOn extends AppCompatActivity {
    ActivitySettingTurnOnBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        binding = ActivitySettingTurnOnBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
    }
}
