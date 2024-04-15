package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.group6.adapters.NotiAdapter;
import com.group6.oriyoung.databinding.ActivityNotiBinding;
import com.group6.oriyoung.databinding.CustomToolbarBackBinding;

import java.util.ArrayList;

public class NotiActivity extends AppCompatActivity {

    ActivityNotiBinding binding;

    ArrayList<ArrayList<String>> notifications;
    NotiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setToolbar();

        loadNoti();
    }

    private void setToolbar() {
        binding.toolbar.toolbarTitle.setText("Thông báo");
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotiActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadNoti() {
        notifications = new ArrayList<>();

        ArrayList<String> notification1 = new ArrayList<>();
        notification1.add("Đại tiệc thương hiệu");
        notification1.add("1 phút trước");
        notification1.add("Sản phẩm COCOON đang sale đậm, mọi người vào mua nhé");
        notifications.add(notification1);

        ArrayList<String> notification2 = new ArrayList<>();
        notification2.add("Miễn phí vận chuyển");
        notification2.add("3 ngày trước");
        notification2.add("Ori ưu đãi khách hàng nhân dịp 30/4-1/5, miễn phí vận chuyển mọi đơn hàng cho quý khách!");
        notifications.add(notification2);

        // Thêm các thông báo khác vào notifications (nếu có)

        // Khởi tạo và thiết lập adapter
        adapter = new NotiAdapter(this, R.layout.item_noti, notifications);
        binding.lvNoti.setAdapter(adapter);
    }
}