package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group6.adapters.CartAdapter;
import com.group6.adapters.CheckoutCartAdapter;
import com.group6.helpers.ChangeNumberItemsListener;
import com.group6.helpers.ManagementCart;
import com.group6.models.CheckoutCart;
import com.group6.models.Product;
import com.group6.helpers.TinyDB;
import com.group6.oriyoung.databinding.ActivityCheckoutBinding;

import java.util.ArrayList;


public class Checkout extends AppCompatActivity {
    private CheckoutCartAdapter adapter;
    private ArrayList<CheckoutCart> cartItems;
    ActivityCheckoutBinding binding;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEvents();

        managementCart = new ManagementCart(this);
        binding.toolbar.toolbarTitle.setText("Thanh toán");

        loadCheckoutCart();


    }

    private void loadCheckoutCart() {

        // Thiết lập RecyclerView để hiển thị danh sách sản phẩm
        ArrayList<Product> cartItems = (ArrayList<Product>) getIntent().getSerializableExtra("cart_items");

        // Thiết lập RecyclerView
        binding.rvCheckoutCart.setLayoutManager(new LinearLayoutManager(this));

        // Thiết lập Adapter
        CheckoutCartAdapter adapter = new CheckoutCartAdapter(getApplicationContext(), cartItems);
        binding.rvCheckoutCart.setAdapter(adapter);

        // Tính tổng số tiền cần thanh toán
        double totalAmount = 0;
        for (Product product : cartItems) {
            totalAmount += product.getProductPrice() * product.getNumberInCart(); // Tính tổng giá
        }

        binding.txtTotalAmount.setText(String.format("%.0f VNĐ", totalAmount));

        int totalQuantity = 0;

        // Duyệt qua các sản phẩm trong giỏ và cộng dồn số lượng
        for (Product product : cartItems) {
            totalQuantity += product.getNumberInCart(); // Cộng dồn số lượng của mỗi sản phẩm
        }
        binding.txtProductQuantity.setText(String.valueOf(totalQuantity) + " sản phẩm");
    }

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