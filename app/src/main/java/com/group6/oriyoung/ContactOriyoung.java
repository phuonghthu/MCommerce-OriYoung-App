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
import com.group6.oriyoung.databinding.ActivityContactOriyoungBinding;

public class ContactOriyoung extends AppCompatActivity {

    ActivityContactOriyoungBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactOriyoungBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.toolbarTitle.setText("Liên hệ với OriYoung");
        addEvents();
    }

    private void addEvents() {
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactOriyoung.this, AccountFirstFragment.class);
                finish();
            }
        });

        //Binding các nút
    }
}