package com.group6.oriyoung;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.group6.oriyoung.databinding.ActivitySignUpBinding;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SignUp extends AppCompatActivity {
    ActivitySignUpBinding binding;
    Button btnTiepTheo;
    LinearLayout btnTrove;
    RadioGroup radioGroup;
    RadioButton radioButtonMale, radioButtonFemale, radioButtonOther;
    TextInputLayout txtinputphone;
    EditText txthelp_phone;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_sign_up);

        // Ánh xạ
        btnTiepTheo = findViewById(R.id.btnTieptheo);
        btnTrove = findViewById(R.id.imvreturn);
        radioGroup = findViewById(R.id.radioGroup);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        radioButtonOther = findViewById(R.id.radioButtonOther);
        txtinputphone = findViewById(R.id.txtinputphone);
        txthelp_phone = findViewById(R.id.txthelp_phone);
        if (binding.txthelpPhone.getText().toString().isEmpty()){
            binding.imgErrorPhone.setVisibility(View.INVISIBLE);
            binding.txtinputphone.setError(null);
        }
        if (binding.txthelpName.getText().toString().isEmpty()){
            binding.imgErrorName.setVisibility(View.INVISIBLE);
            binding.txtinputname.setError(null);
        }
        if (binding.txthelpDay.getText().toString().isEmpty()){
            binding.imgErrorDateOfBirth.setVisibility(View.INVISIBLE);
            binding.txtinputday.setError(null);
        }
        if (binding.txthelpEmail.getText().toString().isEmpty()){
            binding.imgErrorEmail.setVisibility(View.INVISIBLE);
            binding.txtinputemail.setError(null);
        }

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

        txthelp_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content = txthelp_phone.getText().toString();
                if (content.isEmpty()) {
//                    txtinputphone.setErrorEnabled(true);
                    txtinputphone.setError("The phone number can not be empty!");
                    binding.imgErrorPhone.setVisibility(View.VISIBLE);
                }
                else if (content.length() > 11) {
//                    txtinputphone.setErrorEnabled(true);
                    txtinputphone.setError("The phone is too long!");
                    binding.imgErrorPhone.setVisibility(View.VISIBLE);
                }
                else {
//                    txtinputphone.setErrorEnabled(false);
                    txtinputphone.setError(null);
                    binding.imgErrorPhone.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        binding.txthelpName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content = binding.txthelpName.getText().toString();
                if (content.isEmpty()) {
                    binding.txtinputname.setError("The name can not be empty!");
                    binding.imgErrorName.setVisibility(View.VISIBLE);
                }
                else if (!content.matches("^[\\p{L} \\.\\-]+$")) {
                    binding.txtinputname.setError("The name can not include special character!");
                    binding.imgErrorName.setVisibility(View.VISIBLE);
                }
                else {
                    binding.txtinputname.setError(null);
                    binding.imgErrorName.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        binding.txthelpDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content = binding.txthelpDay.getText().toString();
                if (content.isEmpty()) {
                    binding.txtinputday.setError("The date of birth can not be empty!");
                    binding.imgErrorDateOfBirth.setVisibility(View.VISIBLE);
                }
                else {
                    try {
                        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                        formatter.setLenient(false);
                        formatter.parse(content);
                        binding.txtinputday.setError(null);
                        binding.imgErrorDateOfBirth.setVisibility(View.INVISIBLE);
                    } catch (ParseException e) {
                        binding.txtinputday.setError("Wrong format, please follow format \"dd-mm-yyyy\"! For eg: 07-01-2003");
                        binding.imgErrorDateOfBirth.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        binding.txthelpEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content = binding.txthelpEmail.getText().toString();
                if (content.isEmpty()) {
                    binding.txtinputemail.setError("The e-mail can not be empty!");
                    binding.imgErrorEmail.setVisibility(View.VISIBLE);
                }
                else if (!content.matches("^(.+)@(.+)$")) {
                    binding.txtinputemail.setError("The e-mail is invalid, please check again!");
                    binding.imgErrorEmail.setVisibility(View.VISIBLE);
                }
                else {
                    binding.txtinputemail.setError(null);
                    binding.imgErrorEmail.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
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
