package com.group6.oriyoung;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;
import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group6.adapters.FavoriteAdapter;
import com.group6.adapters.ProductAdapter;
import com.group6.adapters.ReviewAdapter;
import com.group6.models.Product;
import com.group6.models.Review;
import com.group6.oriyoung.databinding.ActivityProductDetailBinding;

import java.util.ArrayList;

public class ProductDetail extends AppCompatActivity {

    ActivityProductDetailBinding binding;
//    ReviewAdapter adapter;
    ArrayList<Product> product;
    TextView quantity;
    int total_quantity = 1;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        loadData();
        addEvents();
        loadRelatedProducts();

        quantity = findViewById(R.id.txtQuantity);
//        addtoCart = findViewById(R.id.btnAddToCart);
//
    }


    private void addEvents() {

        binding.GroupButton.btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetail.this, CartActivity.class);
                startActivity(intent);
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ProductDetail.this, ProductList.class);
//                startActivity(intent);
            }
        });
        binding.GroupButton.txtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (total_quantity < 100){
                    total_quantity ++;
                    quantity.setText(String.valueOf(total_quantity));
                }


            }
        });
        binding.GroupButton.txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (total_quantity > 1) {
                    total_quantity--;
                    quantity.setText(String.valueOf(total_quantity));
                }
            }
        });

    }
    private void loadRelatedProducts() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.HORIZONTAL,false);
        binding.rvRelatedProduct.setLayoutManager(gridLayoutManager);
        binding.rvRelatedProduct.setHasFixedSize(true);
        product = new ArrayList<>();

        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 0, "No", R.drawable.product_place_holder,
                true, true, 5.0, 100, null ));
        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 0, "No", R.drawable.product_place_holder,
                true, true, 5.0, 100, null ));
        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 0, "No", R.drawable.product_place_holder,
                true, true, 5.0, 100, null ));
        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 0, "No", R.drawable.product_place_holder,
                true, true, 5.0, 100, null ));
        product.add(new Product(1, 1, "Nước tẩy trang hoa hồng Cocoon tẩy sạch makeup và cấp ẩm 301ml",
                100000, 0, "No", R.drawable.product_place_holder,
                true, true, 5.0, 100, null ));

        adapter= new ProductAdapter(this, product);
        binding.rvRelatedProduct.setAdapter(adapter);
    }
}
