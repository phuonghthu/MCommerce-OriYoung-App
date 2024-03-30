package com.group6.oriyoung;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.group6.Adapter.CartAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.group6.Adapter.CartAdapter;
import com.group6.Models.cartModel;
import com.group6.oriyoung.databinding.ActivityCartBinding;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    ActivityCartBinding binding;
    ArrayList<cartModel> carts;
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadData();
//        addEvent();
    }


    private void loadData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rvCart.setLayoutManager(layoutManager);
        carts = new ArrayList<>();
        carts.add(new cartModel(R.drawable.product, "Cocoon Kem Ủ Tóc Bưởi Giảm Gãy Rụng & Dưỡng Mềm Tóc 200ml", 180000, 0));
        carts.add(new cartModel(R.drawable.cocon, "Cocoon Kem Ủ Tóc Bưởi Giảm Gãy Rụng & Dưỡng Mềm Tóc 200ml", 180000, 18000));
        carts.add(new cartModel(R.drawable.cocon, "Cocoon Kem Ủ Tóc Bưởi Giảm Gãy Rụng & Dưỡng Mềm Tóc 200ml", 180000, 18000));
        carts.add(new cartModel(R.drawable.cocon, "Cocoon Kem Ủ Tóc Bưởi Giảm Gãy Rụng & Dưỡng Mềm Tóc 200ml", 280000, 28000));
        carts.add(new cartModel(R.drawable.cocon, "Cocoon Kem Ủ Tóc Bưởi Giảm Gãy Rụng & Dưỡng Mềm Tóc 200ml", 180000, 28000));


        adapter = new CartAdapter(getApplicationContext(), carts);
        binding.rvCart.setAdapter(adapter);


    }

//    private void addEvent() {
//        TextInputEditText editText = findViewById(R.id.edtNhapmagiamgia);
//        editText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editText.setSelected(true);
//            }
//        });
//    }
}