package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.group6.oriyoung.databinding.CustomDialogSuccessBinding;

        public class SuccessDialog extends AppCompatActivity {
            CustomDialogSuccessBinding binding;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                binding = CustomDialogSuccessBinding.inflate(getLayoutInflater());
                setContentView(binding.getRoot());
            }
        }

