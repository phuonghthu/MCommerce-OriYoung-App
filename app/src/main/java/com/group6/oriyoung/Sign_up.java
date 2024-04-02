package com.group6.oriyoung;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.group6.oriyoung.databinding.ActivitySignUpBinding;

public class Sign_up extends AppCompatActivity {
    ActivitySignUpBinding binding;
    Button btnTiepTheo;
    ImageView btnTrove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Ánh xạ button "Tiếp theo"
        btnTiepTheo = findViewById(R.id.btnTieptheo);
        btnTrove = findViewById(R.id.imvreturn);

        // Gọi phương thức addEvents để thêm sự kiện cho các thành phần
        addEvents();
    }

    // Phương thức để thêm sự kiện cho các thành phần
    private void addEvents() {
        // Sự kiện khi nhấn vào button "Tiếp theo"
        btnTiepTheo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chuyển sang activity_signup_pass
                Intent intent = new Intent(Sign_up.this, signup_pass.class);
                startActivity(intent); // Chuyển sang activity mới
            }
        });

        btnTrove.setOnClickListener(new View.OnClickListener() {
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
        View dialogView = inflater.inflate(R.layout.activity_signup_back, null);

        Button btnDongy = dialogView.findViewById(R.id.btndongy);
        Button btnHuy = dialogView.findViewById(R.id.btnhuy);

        builder.setView(dialogView);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // Bắt sự kiện khi nút "Đồng ý" được nhấn
        btnDongy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển hướng về màn hình chính khi người dùng nhấn nút "Đồng ý"
                Intent intent = new Intent(Sign_up.this, MainActivity.class);
                startActivity(intent);
                alertDialog.dismiss(); // Đóng dialog sau khi chuyển hướng
            }
        });

        // Bắt sự kiện khi nút "Hủy" được nhấn
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss(); // Đóng dialog mà không làm gì khác
            }
        });
    }
}
