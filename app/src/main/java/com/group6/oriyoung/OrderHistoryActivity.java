package com.group6.oriyoung;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.group6.adapters.OrderHistoryVPAdapter;
import com.group6.fragments.AccountFirstFragment;
import com.group6.fragments.CancelOrderFragment;
import com.group6.fragments.DeliveringOrderFragment;
import com.group6.fragments.ReceivedOrderFragment;
import com.group6.oriyoung.R;
import com.group6.oriyoung.databinding.ActivityOrderHistoryBinding;

public class OrderHistoryActivity extends AppCompatActivity {
    ActivityOrderHistoryBinding binding;
    OrderHistoryVPAdapter adapter;

    ViewPager2 viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setViewPager();
        setActionBar();

    }

    private void setActionBar() {
        binding.toolbar.toolbarTitle.setText("Đơn hàng của tôi");
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderHistoryActivity.this, AccountFirstFragment.class);
                finish();
            }
        });
    }

    private void setViewPager() {
        viewPager = findViewById(R.id.OrdViewPager);
        tabLayout = findViewById(R.id.tabLayout);

        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new OrderHistoryVPAdapter(this);
        viewPager.setAdapter(adapter);


        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) {
                tab.setText("Đang giao");
            } else if (position == 1) {
                tab.setText("Đã giao");
            } else {
                tab.setText("Đã hủy");
            }


            // Apply the custom font to the tab text
        }).attach();
    }
}