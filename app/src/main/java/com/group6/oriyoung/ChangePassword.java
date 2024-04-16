package com.group6.oriyoung;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.group6.oriyoung.databinding.ActivityChangePasswordBinding;

public class ChangePassword extends AppCompatActivity {
    ActivityChangePasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.toolbarTitle.setText("Đổi mật khẩu");
        addEvents();



    }

    private void addEvents() {
        binding.btnHUY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePassword.this, Setting.class);
                startActivity(intent);
            }
        });
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePassword.this, Setting.class);
                startActivity(intent);
            }
        });
        binding.btnLUUTHAYDOI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogNotificationSuccessfully dialogNotificationSuccessfully = new DialogNotificationSuccessfully(ChangePassword.this);
                dialogNotificationSuccessfully.show();
            }
        });
    }

}