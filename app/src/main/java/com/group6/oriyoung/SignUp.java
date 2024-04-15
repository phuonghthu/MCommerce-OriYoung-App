package com.group6.oriyoung;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.group6.oriyoung.databinding.ActivitySignUpBinding;

public class SignUp extends AppCompatActivity {
    ActivitySignUpBinding binding;
    Button btnTiepTheo;
    LinearLayout btnTrove;
    RadioGroup radioGroup;
    RadioButton radioButtonMale, radioButtonFemale, radioButtonOther;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Ánh xạ
        btnTiepTheo = findViewById(R.id.btnTieptheo);
        btnTrove = findViewById(R.id.imvreturn);
        radioGroup = findViewById(R.id.radioGroup);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        radioButtonOther = findViewById(R.id.radioButtonOther);

        // Gọi phương thức addEvents để thêm sự kiện cho các thành phần
        addEvents();
    }

    // Phương thức để thêm sự kiện cho các thành phần
    private void addEvents() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Kiểm tra xem RadioButton nào được chọn
                if (checkedId == R.id.radioButtonMale) {
                    // Nếu là RadioButton Nam được chọn, loại bỏ chọn các RadioButton khác
                    radioButtonFemale.setChecked(false);
                    radioButtonOther.setChecked(false);
                } else if (checkedId == R.id.radioButtonFemale) {
                    // Nếu là RadioButton Nữ được chọn, loại bỏ chọn các RadioButton khác
                    radioButtonMale.setChecked(false);
                    radioButtonOther.setChecked(false);
                } else if (checkedId == R.id.radioButtonOther) {
                    // Nếu là RadioButton Không tiết lộ được chọn, loại bỏ chọn các RadioButton khác
                    radioButtonMale.setChecked(false);
                    radioButtonFemale.setChecked(false);
                }
            }
        });
        // Sự kiện khi nhấn vào button "Tiếp theo"
        btnTiepTheo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chuyển sang activity_signup_pass
                Intent intent = new Intent(SignUp.this, SignupPassword.class);
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
        View dialogView = inflater.inflate(R.layout.custom_dialog_confirm, null);

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
                Intent intent = new Intent(SignUp.this, OnboardingActivity.class);
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
