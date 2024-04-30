package com.group6.oriyoung;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.group6.helpers.ManagementCart;
import com.group6.models.Product;
import com.group6.oriyoung.databinding.ActivityOrderDetailBinding;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {

    ActivityOrderDetailBinding binding;
    ArrayList<Product> cartItems;
    private ManagementCart managementCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.toolbarTitle.setText("Chi tiết đơn hàng");
        
        getInfo();

//        loadOderItem();
    }

//    private void loadOderItem() {
//        cartItems = (ArrayList<Product>) getIntent().getSerializableExtra("cart_items");
//
//            // Khởi tạo ManagementCart và thiết lập RecyclerView
//            managementCart = new ManagementCart(this);
//            binding.rvOrderItem.setLayoutManager(new LinearLayoutManager(this));
//            CheckoutCartAdapter adapter = new CheckoutCartAdapter(getApplicationContext(), cartItems);
//            binding.rvOrderItem.setAdapter(adapter);
//
//    }


    private void getInfo() {
        // Kiểm tra nếu Bundle là null
        Bundle orderInfo = getIntent().getExtras();


        if (orderInfo != null) {
            String userName = orderInfo.getString("userName", "Unknown");
            String userPhone = orderInfo.getString("userPhone", "Unknown");
            String userAddress = orderInfo.getString("userAddress", "Unknown");
            String orderID = orderInfo.getString("orderID", "Unknown");
            String orderStatus = orderInfo.getString("orderStatus", "Unknown");
            String orderDate = orderInfo.getString("orderDate", "Unknown");
            String paymentMethod = orderInfo.getString("paymentMethod", "Unknown");
            int totalQuantity = orderInfo.getInt("totalQuantity", 0);
            double totalAmount = orderInfo.getDouble("totalAmount", 0.0);

            binding.txtUserName.setText(userName);
            binding.txtUserPhone.setText(userPhone);
            binding.txtUserAddress.setText(userAddress);
            binding.txtOrderCode.setText(orderID);
            binding.txtOrderDate.setText(orderDate);
            binding.txtPaymentMethod.setText(paymentMethod);
            binding.txtTotalQuantity.setText(totalQuantity + " sản phẩm");
            binding.txtOrderStatus.setText(orderStatus);
            binding.txtTotalAmount.setText(String.format("%.0f VNĐ", totalAmount));
        }

    }
}