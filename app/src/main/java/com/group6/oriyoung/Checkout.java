package com.group6.oriyoung;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.adapters.CheckoutCartAdapter;
import com.group6.helpers.ManagementCart;
import com.group6.models.Cart;
import com.group6.models.Order;
import com.group6.models.Product;
import com.group6.oriyoung.databinding.ActivityCheckoutBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Checkout extends AppCompatActivity {
    private CheckoutCartAdapter adapter;
    private ArrayList<Product> cartItems; // Chứa dữ liệu của giỏ hàng đã được chuyển từ Intent
    private double subTotal; // Biến chứa tổng số tiền trước phí vận chuyển
    private int totalQuantity; // Biến chứa tổng số lượng sản phẩm
    ActivityCheckoutBinding binding;
    private ManagementCart managementCart;
    private DatabaseReference myRef;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String userUID = mAuth.getCurrentUser().getUid();

    private boolean isPaymentWhenReceiveSelected = false;
    private boolean isPaymentByMomoSelected = false;
    public String getCurrentDate() {
        // Định dạng ngày, bạn có thể thay đổi theo ý muốn
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date currentDate = new Date();
        return dateFormat.format(currentDate);  // Trả về ngày hiện tại dưới dạng chuỗi
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.toolbarTitle.setText("Thanh toán");
        cartItems = (ArrayList<Product>) getIntent().getSerializableExtra("cart_items");

        managementCart = new ManagementCart(this); // Khởi tạo ManagementCart

        loadCheckoutCart();
        makePayment();
        addEvents();
        setReceivedInfor();
    }

    private void loadCheckoutCart() {
        // Chuyển dữ liệu sang adapter
        binding.rvCheckoutCart.setLayoutManager(new LinearLayoutManager(this));
        CheckoutCartAdapter adapter = new CheckoutCartAdapter(getApplicationContext(), cartItems);
        binding.rvCheckoutCart.setAdapter(adapter);

        // Tính toán tổng số tiền và số lượng
        double subTotal = 0;
        int totalQuantity = 0;

        for (Product product : cartItems) {
            subTotal += product.getProductPrice() * product.getNumberInCart();
            totalQuantity += product.getNumberInCart();
        }

        double shippingFee = 30000.0;
        double totalAmount = subTotal + shippingFee;
        binding.txtTotalTemp.setText(String.format("%.0f VNĐ", subTotal));
        binding.txtSubTotal.setText(String.format("%.0f VNĐ", subTotal));
        binding.txtShippingFee.setText(String.format("%.0f VNĐ", shippingFee));
        binding.txtTotalAmount.setText(String.format("%.0f VNĐ", totalAmount));
        binding.txtProductQuantity.setText(String.format("%d sản phẩm", totalQuantity));
    }

    private void makePayment() {
        binding.btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra xem người dùng đã chọn phương thức thanh toán chưa
                if (!isPaymentWhenReceiveSelected && !isPaymentByMomoSelected) {
                    Toast.makeText(getApplicationContext(), "Vui lòng chọn phương thức thanh toán!", Toast.LENGTH_SHORT).show();
                    return; // Dừng thực hiện nếu không có phương thức nào được chọn
                }
                // Kiểm tra nếu txtUserPhone hoặc txtUserAddress trống
                // Giá trị mặc định cho số điện thoại và địa chỉ
                String defaultPhone = "Số điện thoại";
                String defaultAddress = "Địa chỉ";

                // Kiểm tra nếu nội dung là giá trị mặc định
                if (binding.txtUserPhone.getText().toString().equals(defaultPhone) ||
                        binding.txtUserAddress.getText().toString().equals(defaultAddress)) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập thông tin nhận hàng", Toast.LENGTH_SHORT).show();

                    // Mở dialog để người dùng nhập thông tin
                    DialogInforReceiving dialog = new DialogInforReceiving(Checkout.this);
                    dialog.setOwnerActivity(Checkout.this); // Để cập nhật dữ liệu trong Activity
                    dialog.show();

                    return; // Dừng tiến trình thanh toán
                }

                // Lưu dữ liệu vào Firebase khi thanh toán
                ArrayList<Cart> cartList = new ArrayList<>();

                for (Product product : cartItems) {
                    Cart cart = new Cart();
                    cart.setProductID(String.valueOf(product.getProductID()));
                    cart.setProductName(product.getProductName());
                    cart.setProductPrice(product.getProductPrice());
                    cart.setQuantity(product.getNumberInCart());
                    cartList.add(cart);
                }

                double subTotal = 0;
                int totalQuantity = 0;

                for (Cart cart : cartList) {
                    subTotal += cart.getProductPrice() * cart.getQuantity();
                    totalQuantity += cart.getQuantity();
                }

                double shippingFee = 30000.0;
                double totalAmount = subTotal + shippingFee;

                String paymentMethod = isPaymentWhenReceiveSelected ? "COD" : "MoMo";

                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                String orderID = database.child("Order").push().getKey();
                String orderStatus = "Đang giao"; // Trạng thái đơn hàng
                String orderDate = getCurrentDate(); // Ngày đặt hàng

                Order newOrder = new Order(orderID, userUID, orderStatus, paymentMethod, orderDate,
                        String.format("%.0f", shippingFee), null, cartList, totalQuantity, totalAmount);

                int finalTotalQuantity = totalQuantity;
                database.child("Order").child(orderID).setValue(newOrder).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        // Tạo danh sách sản phẩm và thông tin người dùng
                        ArrayList<Product> cartItems = managementCart.getListCart();
                        String userName = binding.txtUserName.getText().toString();
                        String userPhone = binding.txtUserPhone.getText().toString();
                        String userAddress = binding.txtUserAddress.getText().toString();



// Tạo DialogPaymentSuccessfully với dữ liệu cần thiết
                        DialogPaymentSuccessfully dialog = new DialogPaymentSuccessfully(
                                Checkout.this,  // Ngữ cảnh
                                cartItems,  // Danh sách sản phẩm
                                userName,  // Tên người nhận
                                userPhone,  // Số điện thoại
                                userAddress,  // Địa chỉ
                                orderID,  // Mã đơn hàng
                                orderStatus,  // Trạng thái đơn hàng
                                orderDate,  // Ngày đặt hàng
                                paymentMethod,
                                finalTotalQuantity,  // Tổng số lượng sản phẩm
                                totalAmount  // Tổng giá trị đơn hàng
                        );


                        dialog.setOwnerActivity(Checkout.this); // Thiết lập OwnerActivity để truy cập tới Checkout


                        dialog.show();
//                        managementCart.clearCart(); // Đặt hàng thành công thì xóa cart
                    } else {
                        Toast.makeText(getApplicationContext(), "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void setReceivedInfor() {
        // Truy xuất thông tin người dùng từ Realtime Database
        myRef = FirebaseDatabase.getInstance().getReference("User/" + userUID);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String userName = snapshot.child("userName").getValue(String.class);
                String phoneNumber = snapshot.child("phoneNumber").getValue(String.class);

                // Hiển thị thông tin trong TextView
                binding.txtUserName.setText(userName);
                if (phoneNumber == null || phoneNumber.isEmpty()) {
                    binding.txtUserPhone.setText("Số điện thoại");
                } else {
                    binding.txtUserPhone.setText(phoneNumber);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý nếu có lỗi
            }
        });

        binding.imvEditInforShipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInforReceiving dialog = new DialogInforReceiving(Checkout.this);
                dialog.setOwnerActivity(Checkout.this); // Để truy cập tới Activity chính
                dialog.show();
            }
        });

    }

    private void addEvents() {
        // Sự kiện khi nhấn nút quay lại
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Xử lý khi người dùng chọn phương thức thanh toán
        binding.PaymentWhenReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPaymentWhenReceiveSelected = true;
                isPaymentByMomoSelected = false; // Hủy chọn cái còn lại
                binding.PaymentWhenReceive.setBackgroundResource(R.drawable.bg_frame);
                binding.PaymentByMomo.setBackgroundResource(R.drawable.linear_bg_state);
            }
        });

        binding.PaymentByMomo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPaymentByMomoSelected = true;
                isPaymentWhenReceiveSelected = false; // Hủy chọn cái còn lại
                binding.PaymentByMomo.setBackgroundResource(R.drawable.bg_frame);
                binding.PaymentWhenReceive.setBackgroundResource(R.drawable.linear_bg_state);
            }
        });
    }
}