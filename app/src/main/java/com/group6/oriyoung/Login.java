package com.group6.oriyoung;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.group6.oriyoung.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;

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