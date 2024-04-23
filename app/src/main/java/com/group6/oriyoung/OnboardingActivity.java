package com.group6.oriyoung;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;




import com.group6.adapters.ViewPagerAdapter;
import com.group6.adapters.ViewPagerAdapter;

import com.group6.fragments.OnboardFirstFragment;
import com.group6.fragments.OnboardSecondFragment;
import com.group6.fragments.OnboardThirdFragment;
import com.group6.oriyoung.databinding.ActivityOnboardingBinding;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;

public class OnboardingActivity extends AppCompatActivity {


    ActivityOnboardingBinding binding;
//    Button btnSkip;
    ViewPager2 viewPager;
//    LinearLayout layoutBottom;
//    CircleIndicator3 circleIndicator;

    ViewPagerAdapter viewPagerAdapter;
    ArrayList<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Kiểm tra trạng thái đăng nhập từ SharedPreferences
//        SharedPreferences sharedPreferences = getSharedPreferences("login_status", MODE_PRIVATE);
//        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
//
//        if (isLoggedIn) {
//            // Nếu người dùng đã đăng nhập, chuyển đến HomeActivity
//            Intent intent = new Intent(OnboardingActivity.this, HomeActivity.class);
//            startActivity(intent);
//            finish(); // Kết thúc OnboardingActivity để ngăn người dùng quay lại nó
//        } else {
            binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            // Các mã khác trong onCreate() của OnboardingActivity



        initUI();
//        addEvents();
        viewPagerAdapter = new ViewPagerAdapter(fragmentList, getSupportFragmentManager(), getLifecycle());
        binding.viewPager.setAdapter(viewPagerAdapter);
        binding.circleIndicator.setViewPager(viewPager);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 3) {
                    binding.btnSkip.setVisibility(View.GONE);
                    binding.layoutBottom.setVisibility(View.GONE);
                }else{
                    binding.btnSkip.setVisibility(View.VISIBLE);
                    binding.layoutBottom.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });



    }

    private void initUI() {
        viewPager = binding.viewPager;

        binding.btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnboardingActivity.this, HomeActivity.class);
                startActivity(intent);
                finish(); // Tùy chọn: Đóng OnboardingActivity sau khi chuyển hướng
            }
        });
        binding.txtSignupNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnboardingActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnboardingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



        // Khởi tạo danh sách fragment
        fragmentList = new ArrayList<>();
        fragmentList.add(new OnboardFirstFragment());
        fragmentList.add(new OnboardSecondFragment());
        fragmentList.add(new OnboardThirdFragment());


    }


}