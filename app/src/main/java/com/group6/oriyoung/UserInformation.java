package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.group6.fragments.AccountFragment;
import com.group6.oriyoung.databinding.ActivityUserInformationBinding;

public class UserInformation extends AppCompatActivity {
    ActivityUserInformationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserInformationBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        binding.toolbar.toolbarTitle.setText("Thông tin cá nhân");
        addEvent();
    }

    private void addEvent() {
        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInformation.this, UserInfoDetail.class);
                startActivity(intent);
            }
        });
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInformation.this, AccountFragment.class);
                finish();
            }
        });
    }
}