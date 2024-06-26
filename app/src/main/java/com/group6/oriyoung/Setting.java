package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.group6.fragments.AccountFragment;
import com.group6.oriyoung.databinding.ActivitySettingBinding;

public class Setting extends AppCompatActivity {
    ActivitySettingBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        binding.toolbar.toolbarTitle.setText("Cài đặt");
        addEvents();
    }

    private void addEvents() {
        binding.cardviewdoimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, ChangePassword.class);
                startActivity(intent);
            }
        });
        binding.cardviewxoataikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDeleteAccount dialogDeleteAccount = new DialogDeleteAccount(Setting.this);
                dialogDeleteAccount.show();
            }
        });
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, AccountFragment.class);
                finish();
            }
        });
    }
}
