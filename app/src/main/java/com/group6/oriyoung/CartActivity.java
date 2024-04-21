package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

//import com.group6.Adapter.CartAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.group6.adapters.CartAdapter;
import com.group6.helpers.ChangeNumberItemsListener;
import com.group6.helpers.ManagementCart;
import com.group6.oriyoung.databinding.ActivityCartBinding;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;
    ArrayList<com.group6.models.Cart> carts;
    CartAdapter adapter;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.toolbarTitle.setText("Giỏ hàng");

        managementCart = new ManagementCart(this);

        setVariable();
        calculateCart();
        loadCartItem();
        addEvent();
    }

    private void addEvent() {
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        binding.cartbill.btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, Checkout.class);
                startActivity(intent);
            }
        });


    }

    private void calculateCart() {
        double discount = 0;

        double total = Math.round(managementCart.getTotalFee());
        double subTotal = Math.round(managementCart.getTotalFee());

        binding.cartbill.txtSubTotal.setText(String.valueOf(subTotal) + "VNĐ");
        binding.cartbill.txtTotal.setText(total + "VNĐ");
    }

    private void loadCartItem() {
        if (managementCart.getListCart().isEmpty()) {
            binding.blankCart.setVisibility(View.VISIBLE);
            binding.scrollViewCart.setVisibility(View.GONE);
        } else {
            binding.blankCart.setVisibility(View.GONE);
            binding.scrollViewCart.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rvCart.setLayoutManager(layoutManager);
        adapter = new CartAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                calculateCart();
            }
        });

        binding.rvCart.setAdapter(adapter);

    }

    private void setVariable() {
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}