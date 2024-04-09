package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;



import com.group6.Adapter.ViewPagerAdapter;
import com.group6.fragments.OnboardFirstFragment;
import com.group6.fragments.OnboardSecondFragment;
import com.group6.fragments.OnboardThirdFragment;
import com.group6.oriyoung.databinding.ActivityOnboardingBinding;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;

public class OnboardingActivity extends AppCompatActivity {


    ActivityOnboardingBinding binding;
    Button btnSkip;
    ViewPager2 viewPager;
    LinearLayout layoutBottom;
    CircleIndicator3 circleIndicator;

    ViewPagerAdapter viewPagerAdapter;
    ArrayList<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUI();
        viewPagerAdapter = new ViewPagerAdapter(fragmentList, getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(viewPagerAdapter);
        circleIndicator.setViewPager(viewPager);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 3) {
                    btnSkip.setVisibility(View.GONE);
                    layoutBottom.setVisibility(View.GONE);
                }else{
                    btnSkip.setVisibility(View.VISIBLE);
                    layoutBottom.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });



    }

    private void initUI() {
        btnSkip = findViewById(R.id.btnSkip);
        viewPager = findViewById(R.id.view_pager);
        layoutBottom= findViewById(R.id.layout_bottom);
        circleIndicator= findViewById(R.id.circle_indicator);

        // Sự kiện cho button Skip
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnboardingActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Tùy chọn: Đóng OnboardingActivity sau khi chuyển hướng
            }
        });

        // Khởi tạo danh sách fragment
        fragmentList = new ArrayList<>();
        fragmentList.add(new OnboardFirstFragment());
        fragmentList.add(new OnboardSecondFragment());
        fragmentList.add(new OnboardThirdFragment());


    }
}