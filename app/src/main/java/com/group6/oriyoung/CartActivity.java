package com.group6.oriyoung;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

//import com.group6.Adapter.CartAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.group6.adapters.CartAdapter;
import com.group6.oriyoung.databinding.ActivityCartBinding;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;
    ArrayList<com.group6.models.Cart> carts;
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadData();
        addEvent();
    }


    private void loadData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rvCart.setLayoutManager(layoutManager);
        carts = new ArrayList<>();
        carts.add(new com.group6.models.Cart(R.drawable.img_product, "Cocoon Kem Ủ Tóc Bưởi Giảm Gãy Rụng & Dưỡng Mềm Tóc 200ml", 180000, 0));
        carts.add(new com.group6.models.Cart(R.drawable.cocon, "Cocoon Kem Ủ Tóc Bưởi Giảm Gãy Rụng & Dưỡng Mềm Tóc 200ml", 180000, 18000));
        carts.add(new com.group6.models.Cart(R.drawable.cocon, "Cocoon Kem Ủ Tóc Bưởi Giảm Gãy Rụng & Dưỡng Mềm Tóc 200ml", 180000, 18000));
        carts.add(new com.group6.models.Cart(R.drawable.cocon, "Cocoon Kem Ủ Tóc Bưởi Giảm Gãy Rụng & Dưỡng Mềm Tóc 200ml", 280000, 28000));
        carts.add(new com.group6.models.Cart(R.drawable.cocon, "Cocoon Kem Ủ Tóc Bưởi Giảm Gãy Rụng & Dưỡng Mềm Tóc 200ml", 180000, 28000));
//        adapter = new CartAdapter(getApplicationContext(), carts);
        adapter = new CartAdapter(getApplicationContext(), carts);
        binding.rvCart.setAdapter(adapter);


    }

    private void addEvent() {
        TextInputEditText editText = findViewById(R.id.edtVoucher);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setSelected(true);
            }
        });
    }
}