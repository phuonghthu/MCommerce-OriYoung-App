package com.group6.oriyoung;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.group6.oriyoung.databinding.ActivityTrungTamHoTroBinding;
import com.group6.oriyoung.fragment.BaomatFragment;
import com.group6.oriyoung.fragment.DoitraFragment;
import com.group6.oriyoung.fragment.VanchuyenFragment;

public class Trung_tam_ho_tro extends AppCompatActivity {
    ActivityTrungTamHoTroBinding binding;
    Button btn_dt;
    Button btn_vc;
    Button btn_bm;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    Button selectedButton; // Biến để theo dõi button được chọn hiện tại

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrungTamHoTroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btn_dt = findViewById(R.id.btndoitra);
        btn_vc = findViewById(R.id.btnvanchuyen);
        btn_bm = findViewById(R.id.btnbaomat);
        frameLayout = findViewById(R.id.frame_in4);
        fragmentManager = getSupportFragmentManager();

        // Mặc định chọn button "Đổi trả" khi màn hình được tạo
        updateButtonState(btn_dt);
        selectedButton = btn_dt;
        DoitraFragment doitraFragment = new DoitraFragment();
        fragmentManager.beginTransaction().replace(R.id.frame_in4, doitraFragment).commit();

        btn_dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateButtonState(btn_dt);
                selectedButton = btn_dt;
                DoitraFragment doitraFragment = new DoitraFragment();
                fragmentManager.beginTransaction().replace(R.id.frame_in4, doitraFragment).commit();
            }
        });
        btn_vc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateButtonState(btn_vc);
                selectedButton = btn_vc;
                VanchuyenFragment vanchuyenFragment = new VanchuyenFragment();
                fragmentManager.beginTransaction().replace(R.id.frame_in4, vanchuyenFragment).commit();
            }
        });
        btn_bm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateButtonState(btn_bm);
                selectedButton = btn_bm;
                BaomatFragment baomatFragment = new BaomatFragment();
                fragmentManager.beginTransaction().replace(R.id.frame_in4, baomatFragment).commit();
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
