package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group6.adapters.CheckoutCartAdapter;
import com.group6.models.CheckoutCart;
import com.group6.models.Product;
import com.group6.oriyoung.databinding.ActivityCheckoutBinding;

import java.util.ArrayList;


public class Checkout extends AppCompatActivity {
    private CheckoutCartAdapter adapter;
    private ArrayList<CheckoutCart> cartItems;
    ActivityCheckoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEvents();
        binding.toolbar.toolbarTitle.setText("Thanh toán");

//        loadCheckoutCart();


    }

//    private void loadCheckoutCart() {
//        Intent intent = getIntent();
//        ArrayList<Product> cartItems = intent.getParcelableArrayListExtra("cartItems")
//        RecyclerView recyclerView = findViewById(R.id.rvCheckoutCart);
//        CheckoutCartAdapter adapter = new CheckoutCartAdapter(this, cartItems);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
//
//    }

    private boolean isPaymentWhenReceiveSelected = false;
    private boolean isPaymentByMomoSelected = false;
    private void addEvents() {
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPaymentSuccessfully dialogInforReceiving = new DialogPaymentSuccessfully(Checkout.this);
                dialogInforReceiving.show();
            }
        });
        binding.imvEditInforShipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInforReceiving dialogInforReceiving = new DialogInforReceiving(Checkout.this);
                dialogInforReceiving.show();
            }
        });
        binding.PaymentWhenReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPaymentWhenReceiveSelected = !isPaymentWhenReceiveSelected;
                if (isPaymentWhenReceiveSelected) {
                    binding.PaymentWhenReceive.setBackgroundResource(R.drawable.linear_bg_selected_state);
                } else {
                    binding.PaymentWhenReceive.setBackgroundResource(R.drawable.linear_bg_state);
                }
            }
        });

        binding.PaymentByMomo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPaymentByMomoSelected = !isPaymentByMomoSelected;
                if (isPaymentByMomoSelected) {
                    binding.PaymentByMomo.setBackgroundResource(R.drawable.linear_bg_selected_state);
                } else {
                    binding.PaymentByMomo.setBackgroundResource(R.drawable.linear_bg_state);
                }
            }
        });

    }

}