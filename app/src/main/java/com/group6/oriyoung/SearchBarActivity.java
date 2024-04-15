package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.group6.oriyoung.databinding.ActivitySearchBarBinding;


public class SearchBarActivity extends AppCompatActivity {
    ActivitySearchBarBinding binding;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        addEvents();
    }

    private void addEvents() {
        binding.btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchBarActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        binding.btnNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchBarActivity.this, NotiActivity.class);
                startActivity(intent);
            }
        });

    }


}
