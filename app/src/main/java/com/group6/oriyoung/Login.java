package com.group6.oriyoung;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.group6.oriyoung.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;
    boolean isValidEmail = false;
    boolean isValidPassword = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


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
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.editTextInputMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content = binding.editTextInputMail.getText().toString();
                isValidEmail = false;
                if (!content.matches("^(.+)@(.+)$")) {
                    binding.inputPhone.setError("The e-mail is invalid, please check again!");
                }
                else {
                    isValidEmail = true;
                    binding.inputPhone.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        binding.editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content = binding.editTextPassword.getText().toString();
                isValidPassword = false;
                if (!content.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
                    binding.inputPassword.setHelperText("Password need more than 8 digits or numbers.");
                }
                else {
                    isValidPassword = true;
                    binding.inputPassword.setHelperText(null);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidEmail) {
                    Toast.makeText(v.getContext(), "Invalid email!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!isValidPassword) {
                    Toast.makeText(v.getContext(), "Invalid password!", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(Login.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.equals(binding.btnLogin)) {
//                Create and show custom dialog
                Dialog dialog = new Dialog(Login.this);
                dialog.setContentView(R.layout.custom_dialog_fail);
                Button btnTryAgain = findViewById(R.id.btnTryAgain);
                btnTryAgain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

            }
        }
    };
}