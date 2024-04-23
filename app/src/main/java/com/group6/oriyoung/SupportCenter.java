package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.group6.fragments.AccountFragment;
import com.group6.fragments.DeliveryFragment;
import com.group6.fragments.ReturnFragment;
import com.group6.fragments.SecurityFragment;
import com.group6.oriyoung.databinding.ActivitySupportCenterBinding;

public class SupportCenter extends AppCompatActivity {
    ActivitySupportCenterBinding binding;
    Button btn_dt;
    Button btn_vc;
    Button btn_bm;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    Button selectedButton; // Biến để theo dõi button được chọn hiện tại
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySupportCenterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setActionBar();


        btn_dt = findViewById(R.id.btndoitra);
        btn_vc = findViewById(R.id.btnvanchuyen);
        btn_bm = findViewById(R.id.btnbaomat);
        frameLayout = findViewById(R.id.frame_in4);
        fragmentManager = getSupportFragmentManager();
        title = findViewById(R.id.toolbarTitle);

        // Mặc định chọn button "Đổi trả" khi màn hình được tạo
        updateButtonState(btn_dt);
        selectedButton = btn_dt;
        ReturnFragment doitraFragment = new ReturnFragment();
        fragmentManager.beginTransaction().replace(R.id.frame_in4, doitraFragment).commit();

        btn_dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateButtonState(btn_dt);
                selectedButton = btn_dt;
                ReturnFragment doitraFragment = new ReturnFragment();
                fragmentManager.beginTransaction().replace(R.id.frame_in4, doitraFragment).commit();
            }
        });
        btn_vc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateButtonState(btn_vc);
                selectedButton = btn_vc;
                DeliveryFragment vanchuyenFragment = new DeliveryFragment();
                fragmentManager.beginTransaction().replace(R.id.frame_in4, vanchuyenFragment).commit();
            }
        });
        btn_bm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateButtonState(btn_bm);
                selectedButton = btn_bm;
                SecurityFragment baomatFragment = new SecurityFragment();
                fragmentManager.beginTransaction().replace(R.id.frame_in4, baomatFragment).commit();
            }
        });

    }

    private void setActionBar() {
        binding.toolbar.toolbarTitle.setText("Trung tâm hỗ trợ");
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SupportCenter.this, AccountFragment.class);
                finish();
            }
        });
    }

    private void updateButtonState(Button button) {
        if (button == null) return;

        // Đặt lại background và màu chữ của button trước đó (nếu có)
        if (selectedButton != null) {
            selectedButton.setBackgroundResource(R.drawable.button_layout_white_green);
            selectedButton.setTextColor(getResources().getColor(android.R.color.black));
        }

        // Đặt background và màu chữ cho button mới
        button.setBackgroundResource(R.drawable.button_layout_cancel);
        button.setTextColor(getResources().getColor(android.R.color.white));
    }
}
