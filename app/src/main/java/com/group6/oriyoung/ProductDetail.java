package com.group6.oriyoung;

import static androidx.core.app.NavUtils.getParentActivityName;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group6.adapters.ProductAdapter;
import com.group6.adapters.ReviewAdapter;
import com.group6.fragments.HomeFragment;
import com.group6.helpers.ManagementCart;
import com.group6.models.Product;
import com.group6.models.Review;
import com.group6.oriyoung.databinding.ActivityProductDetailBinding;

import java.util.ArrayList;

public class ProductDetail extends BaseActivity {

    ActivityProductDetailBinding binding;
    private Product object;
    private String callingActivity;
//    ReviewAdapter adapter;
    ArrayList<Product> product;
    ArrayList<Review> reviews;
    TextView quantity;
    private int num = 1;
    private ManagementCart managementCart;
    int lastVisibleItemIndex = 2; // Vị trí của item cuối cùng muốn hiển thị
    ProductAdapter productAdapter;
    ReviewAdapter reviewAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.toolbarTitle.setText("Chi tiết sản phẩm");
        
        getIntentExtra();
        setVariable();
        addEvents();



        quantity = findViewById(R.id.txtQuantity);

//        addtoCart = findViewById(R.id.btnAddToCart);
//
    }


    private void getIntentExtra() {
        object= (Product) getIntent().getSerializableExtra("object");
            }

    private void setVariable() {
        managementCart = new ManagementCart(this);
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Load ảnh từ path
        Glide.with(ProductDetail.this).load(object.getImagePath()).into(binding.imvProductThumb);
        binding.txtProductPrice.setText(String.valueOf(Math.round(object.getProductPrice())) + " VNĐ");
        binding.txtProductName.setText(object.getProductName());
        binding.txtProductDescription.setText(object.getProductDescription());

        // Initialize reviews ArrayList
        reviews = new ArrayList<>();

        // Update rating value and review count
        binding.txtRatingValue.setText(String.valueOf((int) object.getRatingValue()));
        binding.txtRatingCount.setText("( " + String.valueOf(reviews.size()) + " lượt đánh giá )");
    }




    private void addEvents() {

        binding.reviewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductDetail.this, ReviewDetail.class));
            }
        });
        binding.GroupButtons.imvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = num+1;
                quantity.setText(num+" ");

            }
        });

        binding.GroupButtons.imvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num>1){
                    num=num-1;
                    quantity.setText(num+"");
                }
            }
        });
        binding.GroupButtons.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser != null) {
                    object.setNumberInCart(num);
                    managementCart.insertProduct(object);
                } else {

                    showLoginDialog();

                }
            }
        });

    }

    private void showLoginDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog_confirm, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(ProductDetail.this);
        builder.setView(dialogView);

        Button btndongy = dialogView.findViewById(R.id.btndongy);
        Button btnhuy = dialogView.findViewById(R.id.btnhuy);
        TextView txtTitle = dialogView.findViewById(R.id.txtTitle);
        TextView txtContent = dialogView.findViewById(R.id.txtContent);

        btndongy.setVisibility(View.GONE);
        btnhuy.setText("Đăng nhập");
        txtTitle.setText("Vui lòng đăng nhập!");
        txtContent.setText("Vui lòng đăng nhập để tiếp tục mua sắm và trải nghiệm tại OriYoung!");
        final AlertDialog dialog = builder.create();
        dialog.show();


        // Dẫn đến trang Login
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetail.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


}
