package com.group6.oriyoung;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
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
import java.util.Calendar;

public class SignUp extends AppCompatActivity {
    ActivitySignUpBinding binding;

    FirebaseAuth mAuth;
    String email, name, dob;
    boolean isValidEmail = false;
    boolean isValidName = false;
    boolean isValidDOB = false;
    boolean checked = false;
    Calendar calendar;
    int day, month, year;
    RadioButton Male, Female, Other;
    RadioGroup gender;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Male = findViewById(R.id.radioButtonMale);
        Female = findViewById(R.id.radioButtonFemale);
        Other = findViewById(R.id.radioButtonOther);
        mAuth =  FirebaseAuth.getInstance();






        addEvents();
    }

    private void addEvents() {
        binding.txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, Login.class));
            }
        });
        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, SignupPassword.class));
            }
        });
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Kiểm tra xem RadioButton nào được chọn
                if (checkedId == R.id.radioButtonMale) {
                    // Nếu là RadioButton Nam được chọn, loại bỏ chọn các RadioButton khác
                    Female.setChecked(false);
                    Other.setChecked(false);
                } else if (checkedId == R.id.radioButtonFemale) {
                    // Nếu là RadioButton Nữ được chọn, loại bỏ chọn các RadioButton khác
                    Male.setChecked(false);
                    Other.setChecked(false);
                } else if (checkedId == R.id.radioButtonOther) {
                    // Nếu là RadioButton Không tiết lộ được chọn, loại bỏ chọn các RadioButton khác
                    Male.setChecked(false);
                    Female.setChecked(false);
                }
            }
        });
        binding.txtemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                email = binding.txtemail.getText().toString().trim();
                isValidEmail = false;
                if (!email.matches("^(.+)@(.+)$"))  {
                    binding.inputEmail.setError(getString(R.string.Login_mail_error));
                    binding.inputEmail.setErrorIconDrawable(null);
                    binding.inputEmail.getBoxStrokeErrorColor();

                }else {
                    isValidEmail = true;
                    binding.inputEmail.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.txtname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name = binding.txtname.getText().toString().trim();
                isValidName = false;
                if (!name.matches("^[a-zA-ZÀ-ÿ]+(?: [a-zA-ZÀ-ÿ]+)*$"))  {
                    binding.inputName.setError(getString(R.string.Login_mail_error));
                    binding.inputName.setErrorIconDrawable(null);
                }else {
                    isValidName = true;
                    binding.inputName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.inputday.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                day = calendar.get(Calendar.DAY_OF_MONTH);
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(SignUp.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        binding.txtday.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                },year, month, day);

                datePickerDialog.show();
                datePickerDialog.getButton(datePickerDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(SignUp.this, R.color.error));
                datePickerDialog.getButton(datePickerDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(SignUp.this, R.color.primarygreen));

            }
        });
        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = binding.txtemail.getText().toString().trim();
                name = binding.txtname.getText().toString().trim();
                dob = binding.txtday.getText().toString().trim();

                if(email.isEmpty() && name.isEmpty() && dob.isEmpty() && (!Male.isChecked() || !Female.isChecked() || !Other.isChecked())){
                    binding.inputEmail.setError(getString(R.string.Empty_error));
                    binding.inputName.setError(getString(R.string.Empty_error));
                    binding.inputday.setError(getString(R.string.Empty_error));
                    binding.inputday.setErrorIconDrawable(null);
                    binding.inputday.setEndIconDrawable(R.drawable.ic_calendar);
                    binding.inputEmail.requestFocus();
                    binding.errorgender.setVisibility(View.VISIBLE);
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.inputEmail.setError(getString(R.string.Login_mail_error));
                } else if (name.isEmpty()) {
                    binding.inputName.setError(getString(R.string.Empty_error));
                    binding.inputName.requestFocus();
                } else if( email.isEmpty()) {
                    binding.inputEmail.setError(getString(R.string.Empty_error));
                    binding.inputEmail.requestFocus();
                } else if (dob.isEmpty()) {
                    binding.inputday.setError(getString(R.string.Empty_error));
                    binding.inputday.setErrorIconDrawable(null);
                    binding.inputday.setEndIconDrawable(R.drawable.ic_calendar);
                } else if (!Male.isChecked() || !Female.isChecked() || !Other.isChecked()) {
                    binding.errorgender.setVisibility(View.VISIBLE);
                } else{
                    NewUser();
                }


            }
        });
    }

    private void showDialog() {
        // Tạo dialog
        Dialog dialog = new Dialog(SignUp.this);
        dialog.setContentView(R.layout.custom_dialog_success);

        // Xác định nút "Khám phá ngay" trong dialog
        Button btnCTA = dialog.findViewById(R.id.btnCTA);

        // Xử lý sự kiện khi người dùng nhấp vào nút "Thử lại"
        btnCTA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đóng dialog
                startActivity(new Intent(SignUp.this, HomeActivity.class));
                dialog.dismiss();

                // Quay lại LoginActivity
            }
        });

        // Thiết lập dialog không thể bị hủy khi nhấn bên ngoài
        dialog.setCanceledOnTouchOutside(false);

        // Hiển thị dialog
        dialog.show();
    }
    private void NewUser(){

    }


}
