package com.group6.oriyoung;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.group6.oriyoung.databinding.ActivitySignUpBinding;
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

        btn_dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoitraFragment doitraFragment = new DoitraFragment();
                fragmentManager.beginTransaction().replace(R.id.frame_in4, doitraFragment).commit();
            }
        });
        btn_vc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VanchuyenFragment vanchuyenFragment = new VanchuyenFragment();
                fragmentManager.beginTransaction().replace(R.id.frame_in4, vanchuyenFragment).commit();
            }
        });
        btn_bm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaomatFragment baomatFragment = new BaomatFragment();
                fragmentManager.beginTransaction().replace(R.id.frame_in4, baomatFragment).commit();
            }
        });

    }
}