package com.group6.oriyoung;

import static android.util.Patterns.*;

import static androidx.constraintlayout.motion.widget.TransitionBuilder.validate;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
import com.group6.oriyoung.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;
    TextInputLayout inputEmail, inputPass;
    TextInputEditText txtinputEmail, txtinputPass;
    String email, password;
    boolean isValidEmail = false;
    boolean isValidPass = false;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        inputEmail = findViewById(R.id.inputEmail);
        inputPass = findViewById(R.id.inputPassword);
        mAuth = FirebaseAuth.getInstance();
        txtinputEmail = findViewById(R.id.editTextEmail);
        txtinputPass = findViewById(R.id.editTextPassword);






        addEvents();

    }



    private void addEvents() {
        binding.txtForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, ForgetPassword.class));
            }
        });
        binding.txtSignupNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, SignUp.class));
                finish();
            }
        });
        binding.editTextInputMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isValidEmail = false;
                email = binding.editTextInputMail.getText().toString().trim();
                if (!email.matches("^(.+)@(.+)$"))  {
                   inputEmail.setError(getString(R.string.Login_mail_error));
                   inputEmail.setErrorIconDrawable(null);
                   inputEmail.getBoxStrokeErrorColor();

                } else {
                    isValidEmail = true;
                    inputEmail.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isValidPass = false;
                password = binding.editTextPassword.getText().toString().trim();

                if (!password.matches("^(?=.*\\d).{8,}$")) {
                    inputPass.setError(getString(R.string.Login_pass_error));
                    inputPass.setErrorIconDrawable(null);
                    inputPass.setBoxStrokeErrorColor(ColorStateList.valueOf(getColor(R.color.error)));

                }else{
                    isValidPass = true;
                    inputPass.setError(null);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.editTextInputMail.getText().toString().trim();
                String password = binding.editTextPassword.getText().toString().trim();



                if(email.isEmpty() && password.isEmpty() ) {
                    inputEmail.setError(getString(R.string.Empty_error));
                    inputPass.setError(getString(R.string.Empty_error));
                    inputEmail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    inputEmail.setError(getString(R.string.Login_mail_error));
                } else if (password.isEmpty()) {
                    inputPass.setError(getString(R.string.Empty_error));
                } else if(password.length() < 8 || !password.matches("^(?=.*\\d).{8,}$")) {
                    inputPass.setError(getString(R.string.Login_pass_error));
                } else if( email.isEmpty()) {
                    inputEmail.setError(getString(R.string.Empty_error));
                    inputEmail.requestFocus();
                }else{

                    loginUser();
                }
                }




        });










    }
    private void loginUser(){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(Login.this, HomeActivity.class));
                            finish();
                        } else {
                            showDialog();

                        }

                    }
                });
        }
    //     Phương thức hiển thị dialog thông báo

    private void showDialog() {
        // Tạo dialog
        Dialog dialog = new Dialog(Login.this);
        dialog.setContentView(R.layout.custom_dialog_fail);

        // Xác định nút "Thử lại" trong dialog
        Button btnTryAgain = dialog.findViewById(R.id.btnTryAgain);

        // Xử lý sự kiện khi người dùng nhấp vào nút "Thử lại"
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đóng dialog
                dialog.dismiss();

                // Quay lại LoginActivity
            }
        });

        // Thiết lập dialog không thể bị hủy khi nhấn bên ngoài
        dialog.setCanceledOnTouchOutside(false);

        // Hiển thị dialog
        dialog.show();

    }



}