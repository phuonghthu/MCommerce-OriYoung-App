package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group6.adapters.CheckoutCartAdapter;
import com.group6.models.CheckoutCart;
import com.group6.oriyoung.databinding.ActivityOrderDetailsBinding;

import java.util.ArrayList;

public class OrderDetails extends AppCompatActivity {
    private CheckoutCartAdapter adapter;
    private ArrayList<CheckoutCart> cartItems;
    ActivityOrderDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.toolbarTitle.setText("Chi tiết đơn hàng");
        addEvents();
        cartItems = new ArrayList<>();
        adapter = new CheckoutCartAdapter(this, cartItems);
        RecyclerView recyclerView = findViewById(R.id.lvCheckoutCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        cartItems.add(new CheckoutCart("Cocoon Kem Ủ Tóc Bưởi Giảm Gãy Rụng & Dưỡng Mềm Tóc 200ml", 155000, R.drawable.img_product, 2));
        cartItems.add(new CheckoutCart("Cocoon Kem Ủ Tóc Bưởi Giảm Gãy Rụng & Dưỡng Mềm Tóc 200ml", 155000, R.drawable.cocon, 3));
        cartItems.add(new CheckoutCart("Cocoon Kem Ủ Tóc Bưởi Giảm Gãy Rụng & Dưỡng Mềm Tóc 200ml", 155000, R.drawable.cocon, 1));
    }

    private void addEvents() {
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetails.this, Checkout.class);
                startActivity(intent);
            }
        });
    }
}