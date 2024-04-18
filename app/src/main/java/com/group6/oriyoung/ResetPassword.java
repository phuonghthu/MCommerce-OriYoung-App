package com.group6.oriyoung;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.group6.oriyoung.databinding.ActivityResetPasswordBinding;

public class ResetPassword extends AppCompatActivity {
    ActivityResetPasswordBinding binding;
    boolean isValidPassword = false;
    boolean isValidRepassword = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        addEvents();
    }

    private void addEvents() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPassword.this, ForgetPassword.class);
                startActivity(intent);
            }
        });

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidPassword && !isValidRepassword) {
                    Toast.makeText(ResetPassword.this, "Different Password!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (binding.editTextPassword.getText().toString().equals(binding.edittextRepass.getText().toString())) {
                    Toast.makeText(ResetPassword.this, "Different Password!", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(ResetPassword.this, HomeActivity.class);
                startActivity(intent);
            }
        });


        binding.editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content = binding.editTextPassword.getText().toString();
                isValidPassword = false;
                if (!content.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
                    binding.inputPassword.setError("Password need more than 8 digits or numbers.");
                }
                else {
                    isValidPassword = true;
                    binding.inputPassword.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.edittextRepass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content = binding.edittextRepass.getText().toString();
                isValidRepassword = false;
                if (!content.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
                    binding.inputPasswordAgain.setHelperText("Password need more than 8 digits or numbers.");
                }
                else {
                    isValidRepassword = true;
                    binding.inputPasswordAgain.setHelperText(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
