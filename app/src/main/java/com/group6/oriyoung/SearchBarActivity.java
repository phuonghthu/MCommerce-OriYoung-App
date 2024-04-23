package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group6.helpers.CustomMenuBarListener;
import com.group6.helpers.OnSearchFocusChangeListener;
import com.group6.oriyoung.databinding.ActivitySearchBarBinding;

public class SearchBarActivity extends AppCompatActivity implements CustomMenuBarListener {
    private CustomMenuBarListener listener;
    private ActivitySearchBarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set click listeners
        binding.btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCartClicked();
            }
        });

        binding.btnNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNotiClicked();
            }
        });

        binding.edtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                onSearchFocusChanged(hasFocus);
            }
        });
    }

    public void setCustomMenuBarListener(CustomMenuBarListener listener) {
        this.listener = listener;
    }

    public void onNotiClicked() {
        Intent intent = new Intent(SearchBarActivity.this, NotiActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCartClicked() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(SearchBarActivity.this, CartActivity.class);
            startActivity(intent);
        } else {
            showLoginDialog();
        }
    }

    public void onSearchFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            // Switch to SearchActivity when EditText gains focus
            Intent intent = new Intent(SearchBarActivity.this, MenuSearch.class);
            startActivity(intent);
        }
    }

    private void showLoginDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog_confirm, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(SearchBarActivity.this);
        builder.setView(dialogView);

        Button btndongy = dialogView.findViewById(R.id.btndongy);
        Button btnhuy = dialogView.findViewById(R.id.btnhuy);
        TextView txtTitle = dialogView.findViewById(R.id.txtTitle);
        TextView txtContent = dialogView.findViewById(R.id.txtContent);

        btndongy.setVisibility(View.GONE);
        btnhuy.setText("Đăng nhập");
        txtTitle.setText("Vui lòng đăng nhập!");
        txtContent.setText("Vui lòng đăng nhập để tiếp tục mua sắm và trải nghiệm tại OriYoung!");
        final AlertDialog dialog = builder.create();
        dialog.show();

        // Dẫn đến trang Login
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchBarActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}