package com.group6.oriyoung;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    TextInputLayout txtinputphone,txtinputname, txtinputmail, txtinputday;
    EditText txthelp_phone, txthelp_name, txthelp_mail,txthelp_day;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;

    boolean isValidPhone = false;
    boolean isValidName = false;
    boolean isValidDOB = false;
    boolean isValidEmail = false;

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
        txtinputname = findViewById(R.id.txtinputname);
        txthelp_name = findViewById(R.id.txthelp_name);
        txtinputmail = findViewById(R.id.txtinputemail);
        txtinputmail = findViewById(R.id.txthelp_email);
        txtinputday = findViewById(R.id.txtinputday);
        txthelp_day = findViewById(R.id.txthelp_day);

        mAuth = FirebaseAuth.getInstance();



        if (binding.txthelpPhone.getText().toString().isEmpty()){
            binding.imgErrorPhone.setVisibility(View.INVISIBLE);
            binding.txtinputphone.setHelperText(null);
        }
        if (binding.txthelpName.getText().toString().isEmpty()){
            binding.imgErrorName.setVisibility(View.INVISIBLE);
            binding.txtinputname.setHelperText(null);
        }
        if (binding.txthelpDay.getText().toString().isEmpty()){
            binding.imgErrorDateOfBirth.setVisibility(View.INVISIBLE);
            binding.txtinputday.setHelperText(null);
        }
        if (binding.txthelpEmail.getText().toString().isEmpty()){
            binding.imgErrorEmail.setVisibility(View.INVISIBLE);
            binding.txtinputemail.setHelperText(null);
        }

        // Gọi phương thức addEvents để thêm sự kiện cho các thành phần
        addEvents();
    }


    // Phương thức để thêm sự kiện cho các thành phần
    private void addEvents() {
        binding.btnTieptheo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.txthelpEmail.getText().toString();
                String phone = binding.txthelpPhone.getText().toString();
                String name = binding.txthelpName.getText().toString();

                // Kiểm tra xem email và mật khẩu có đúng định dạng không
                if (!email.matches("^(.+)@(.+)$")) {
                    binding.txtinputemail.setError(getString(R.string.Login_mail_error));
                    return;
                } else if (!name.isEmpty()) {
                    binding.txtinputphone.setError(getString(R.string.Empty));
                    txtinputphone.setBoxStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.error));
                    return;
                } else {
                    binding.txtinputemail.setError(null);
                }

                if (!name.matches("^[\\p{L} \\.\\-]+$")) {
                    binding.txtinputname.setError("Tên của bạn chưa đúng");
                    return;
                }else if (!name.isEmpty()) {
                    binding.txtinputphone.setError("Tên không được để trống!");
                    txtinputphone.setBoxStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.error));
                    return;
                }else {
                    binding.txtinputname.setError(null);
                }

                if (!phone.isEmpty()) {
                    binding.txtinputphone.setError("Số điện thoại không được để trống!");
                    txtinputphone.setBoxStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.error));
                }
                else if (phone.length() > 11) {
                    txtinputphone.setHelperText("Số điện thoại không đúng!");
                    txtinputphone.setBoxStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.error));
                }
                else if (phone.length() < 10) {
                    txtinputphone.setHelperText("Số điện thoại không đúng !");
                    txtinputphone.setEndIconMode(TextInputLayout.END_ICON_CLEAR_TEXT);
                    txtinputphone.setBoxStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.error));
                } else {
                    binding.txtinputname.setError(null);
                    txtinputphone.setBoxStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.primarygreen));

                }



            }
        });
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
//        btnTiepTheo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Tạo Intent để chuyển sang activity_signup_pass
//                if (!isValidPhone) {
//                    new AlertDialog.Builder(getApplicationContext())
//                            .setTitle("Unable to continue")
//                            .setMessage("Your phone is invalid!");
//                    return;
//                }
//                if (!isValidName) {
//                    new AlertDialog.Builder(getApplicationContext())
//                            .setTitle("Unable to continue")
//                            .setMessage("Your name is invalid!");
//                    return;
//                }
//                if (!isValidDOB) {
//                    new AlertDialog.Builder(getApplicationContext())
//                            .setTitle("Unable to continue")
//                            .setMessage("Your date of birth is invalid!");
//                    return;
//                }
//                if (!isValidEmail) {
//                    new AlertDialog.Builder(getApplicationContext())
//                            .setTitle("Unable to continue")
//                            .setMessage("Your e-mail is invalid!");
//                    return;
//                }
//                Intent intent = new Intent(SignUp.this, SignupPassword.class);
//                startActivity(intent); // Chuyển sang activity mới
//            }
//        });

        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

//        txthelp_phone.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                isValidPhone = false;
//                String content = txthelp_phone.getText().toString();
//                if (content.isEmpty()) {
//                    txtinputphone.setHelperText("The phone number can not be empty!");
//                    binding.imgErrorPhone.setVisibility(View.VISIBLE);
//                    txtinputphone.setBoxStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.error));
//                }
//                else if (content.length() > 11) {
//                    txtinputphone.setHelperText("The phone is too long!");
//                    binding.imgErrorPhone.setVisibility(View.VISIBLE);
//                    txtinputphone.setBoxStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.error));
//                }
//                else if (content.length() < 10) {
//                    txtinputphone.setHelperText("The phone is too short !");
//                    binding.imgErrorPhone.setVisibility(View.VISIBLE);
//                    txtinputphone.setEndIconMode(TextInputLayout.END_ICON_CLEAR_TEXT);
//                    txtinputphone.setBoxStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.error));
//                }
//                else {
//                    isValidPhone = true;
//                    txtinputphone.setHelperText(null);
//                    binding.imgErrorPhone.setVisibility(View.INVISIBLE);
//                    txtinputphone.setBoxStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.primarygreen));
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {}
//        });

//        binding.txthelpName.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String content = binding.txthelpName.getText().toString();
//                isValidName = false;
//                if (content.isEmpty()) {
//                    binding.txtinputname.setHelperText("The name can not be empty!");
//                    binding.imgErrorName.setVisibility(View.VISIBLE);
//                }
//                else if (!content.matches("^[\\p{L} \\.\\-]+$")) {
//                    binding.txtinputname.setHelperText("The name can not include special character!");
//                    binding.imgErrorName.setVisibility(View.VISIBLE);
//                }
//                else {
//                    isValidName = true;
//                    binding.txtinputname.setHelperText("");
//                    binding.imgErrorName.setVisibility(View.INVISIBLE);
//                }
//
//                if (binding.imgErrorName.getVisibility() == View.VISIBLE) {
//                    binding.txtinputname.setBoxStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.error));
//                }
//                else
//                    binding.txtinputname.setBoxStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.primarygreen));
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {}
//        });

//        binding.txthelpDay.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String content = binding.txthelpDay.getText().toString();
//                isValidDOB = false;
//                if (content.isEmpty()) {
//                    binding.txtinputday.setHelperText("The date of birth can not be empty!");
//                    binding.imgErrorDateOfBirth.setVisibility(View.VISIBLE);
//                }
//                else {
//                    try {
//                        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//                        formatter.setLenient(false);
//                        formatter.parse(content);
//                        // valid
//                        isValidDOB = true;
//                        binding.txtinputday.setHelperText(null);
//                        binding.imgErrorDateOfBirth.setVisibility(View.INVISIBLE);
//                    } catch (ParseException e) {
//                        binding.txtinputday.setHelperText("Wrong format, please follow format \"dd-mm-yyyy\"! For eg: 07-01-2003");
//                        binding.imgErrorDateOfBirth.setVisibility(View.VISIBLE);
//                    }
//                }
//
//
//                if (binding.imgErrorDateOfBirth.getVisibility() == View.VISIBLE) {
//                    binding.txtinputday.setBoxStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.error));
//                }
//                else
//                    binding.txtinputday.setBoxStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.primarygreen));
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {}
//        });

//        binding.txthelpEmail.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String content = binding.txthelpEmail.getText().toString();
//                isValidEmail = false;
//                if (content.isEmpty()) {
//                    binding.txtinputemail.setHelperText("The e-mail can not be empty!");
//                    binding.imgErrorEmail.setVisibility(View.VISIBLE);
//                }
//                else if (!content.matches("^(.+)@(.+)$")) {
//                    binding.txtinputemail.setHelperText("The e-mail is invalid, please check again!");
//                    binding.imgErrorEmail.setVisibility(View.VISIBLE);
//                }
//                else {
//                    isValidEmail = true;
//                    binding.txtinputemail.setHelperText(null);
//                    binding.imgErrorEmail.setVisibility(View.INVISIBLE);
//                }
//                if (binding.imgErrorEmail.getVisibility() == View.VISIBLE) {
//                    binding.txtinputemail.setBoxStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.error));
//                }
//                else
//                    binding.txtinputemail.setBoxStrokeColor(ContextCompat.getColor(getApplicationContext(), R.color.primarygreen));
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {}
//        });

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
