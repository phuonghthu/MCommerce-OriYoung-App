package com.group6.oriyoung;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.group6.models.Product;
import com.group6.oriyoung.databinding.ActivityDialogPaymentSuccessfullyBinding;

import java.util.ArrayList;

public class DialogPaymentSuccessfully extends Dialog {
    ActivityDialogPaymentSuccessfullyBinding binding;

    private ArrayList<Product> cartItems;
    private String userName;
    private String userPhone;
    private String userAddress;
    private String orderID;
    private String orderStatus;
    private String orderDate;
    private String paymentMethod;
    private int totalQuantity;
    private double totalAmount;

    // Constructor để nhận thông tin từ Checkout
    public DialogPaymentSuccessfully(
            Context context,
            ArrayList<Product> cartItems,
            String userName,
            String userPhone,
            String userAddress,
            String orderID,
            String orderStatus,
            String orderDate,
            String paymentMethod,
            int totalQuantity,
            double totalAmount
    ) {
        super(context);
        this.cartItems = cartItems;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.orderID = orderID;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.paymentMethod = paymentMethod;
        this.totalQuantity = totalQuantity;
        this.totalAmount = totalAmount;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDialogPaymentSuccessfullyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setCancelable(false);
        setCanceledOnTouchOutside(false);

        addEvents();
    }

    private void addEvents() {
        // Sự kiện khi nhấn nút "xem chi tiết đơn hàng"
        binding.btnOrderDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để mở OrderDetailActivity
                Intent intent = new Intent(getContext(), OrderDetailActivity.class);


                // Tạo Bundle để truyền dữ liệu
                Bundle bundle = new Bundle();
                bundle.putSerializable("cart_items", cartItems); // Truyền danh sách sản phẩm
                bundle.putString("userName", userName);
                bundle.putString("userPhone", userPhone);
                bundle.putString("userAddress", userAddress);
                bundle.putString("orderID", orderID);
                bundle.putString("orderStatus", orderStatus);
                bundle.putString("orderDate", orderDate);
                bundle.putString("paymentMethod", paymentMethod);
                bundle.putInt("totalQuantity", totalQuantity);
                bundle.putDouble("totalAmount", totalAmount);

                // Đính kèm Bundle vào Intent
                intent.putExtras(bundle);

                // Khởi chạy OrderDetailActivity
                getContext().startActivity(intent);
            }
        });
    }
}
