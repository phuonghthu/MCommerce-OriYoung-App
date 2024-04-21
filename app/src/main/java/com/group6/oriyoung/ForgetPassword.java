package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.group6.oriyoung.databinding.ActivityForgetPasswordBinding;

public class ForgetPassword extends AppCompatActivity {
    ActivityForgetPasswordBinding binding;
    boolean isValidEmail = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetPassword.this, Login.class);
                startActivity(intent);
            }
        });
        binding.btnCotinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidEmail) {
                    Toast.makeText(ForgetPassword.this, "Invalid Email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(ForgetPassword.this, ResetPassword.class);
                startActivity(intent);
            }
        });

        binding.editTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content = binding.editTextEmail.getText().toString();
                isValidEmail = false;
                if (!content.matches("^(.+)@(.+)$")) {
                    binding.txtEmail.setError("The e-mail is invalid, please check again!");
                }
                else {
                    isValidEmail = true;
                    binding.txtEmail.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}