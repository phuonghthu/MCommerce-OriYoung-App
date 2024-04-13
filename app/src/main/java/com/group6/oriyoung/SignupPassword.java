package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.group6.oriyoung.databinding.ActivitySignupPassBinding;

public class SignupPassword extends AppCompatActivity {
    ActivitySignupPassBinding binding;
    ImageView imvback;
    Button Xacnhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupPassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imvback = findViewById(R.id.imvreturn);
        Xacnhan = findViewById(R.id.btnXacnhan);

        addEvents();
    }

    // Phương thức để thêm sự kiện cho các thành phần
    private void addEvents() {
        // Sự kiện khi nhấn vào imvback
        imvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kết thúc activity hiện tại và quay về activity trước đó trong stack
                finish();
            }
        });

        Xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    // Phương thức để hiển thị AlertDialog
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog_success, null);

        Button btnKhamPhaNgay = dialogView.findViewById(R.id.btnCallAction);

        builder.setView(dialogView);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // Bắt sự kiện khi nút "Khám phá ngay" được nhấn
        btnKhamPhaNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển hướng về màn hình chính khi người dùng nhấn nút "Khám phá ngay"
                Intent intent = new Intent(SignupPassword.this, HomeActivity.class);
                startActivity(intent);
                alertDialog.dismiss(); // Đóng dialog sau khi chuyển hướng
            }
        });
    }
}
