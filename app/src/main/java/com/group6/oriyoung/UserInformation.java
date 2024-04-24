package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.fragments.AccountFragment;
import com.group6.models.User;
import com.group6.oriyoung.databinding.ActivityUserInformationBinding;

public class UserInformation extends AppCompatActivity {
    ActivityUserInformationBinding binding;
    FirebaseAuth mAuth;
    String name, phone, dob, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserInformationBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        binding.toolbar.toolbarTitle.setText("Thông tin cá nhân");

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            Toast.makeText(this, "Vui lòng đăng nhập!", Toast.LENGTH_LONG).show();
        }else {
            showUserInfo(user);

        }
        addEvent();
    }

    private void addEvent() {
        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                updateinfo();
                startActivity(new Intent(UserInformation.this, UserInfoDetail.class)
                        .putExtra("userName", binding.txtName.getText().toString())
                        .putExtra("phone", binding.txtPhone.getText().toString())
                        .putExtra("dob", binding.txtDOB.getText().toString())
                        .putExtra("email", binding.txtEmail.getText().toString())
                );

            }
        });
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInformation.this, AccountFragment.class);
                startActivity(intent);
                finish();
            }
        });
    }
//    private void showUserInfo(){
//        Intent intent = getIntent();
//        String UserName = intent.getStringExtra("userName");
//        String DOB = intent.getStringExtra("DOB");
//        String Phone = intent.getStringExtra("phone");
//        String Email = intent.getStringExtra("email");
//
//        binding.txtName.setText(UserName);
//        binding.txtDOB.setText(DOB);
//        binding.txtPhone.setText(Phone);
//        binding.txtEmail.setText(Email);
//
//    }
//private void showUserInfo() {
//    // Lấy tên người dùng từ SharedPreferences hoặc một nguồn khác
//    String userName = "Tên người dùng mặc định";
//
//    // Thực hiện truy vấn để lấy thông tin người dùng từ cơ sở dữ liệu Firebase
//    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("User").child(userName);
//    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot snapshot) {
//            if (snapshot.exists()) {
//                String userName = snapshot.child("userName").getValue(String.class);
//                String dob = snapshot.child("dob").getValue(String.class);
//                String phone = snapshot.child("phone").getValue(String.class);
//                String email = snapshot.child("email").getValue(String.class);
//
//                // Hiển thị thông tin người dùng lên giao diện
//                binding.txtName.setText(userName);
//                binding.txtDOB.setText(dob);
//                binding.txtPhone.setText(phone);
//                binding.txtEmail.setText(email);
//            }
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError error) {
//            // Xử lý lỗi (nếu cần)
//        }
//    });
//}

//    public void updateinfo(){
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference reference = database.getReference("User");
//
//
//        String Name = binding.txtName.getText().toString().trim();
//
//
//        Query checkUser = reference.orderByChild("userName").equalTo(Name);
//        checkUser.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if( snapshot.exists()){
//                    String NamefromDB = snapshot.child(Name).child("userName").getValue(String.class);
//                    String PhonefromDB = snapshot.child(Name).child("phone").getValue(String.class);
//                    String DOBfromDB = snapshot.child(Name).child("dob").getValue(String.class);
//                    String EmailfromDB = snapshot.child(Name).child("email").getValue(String.class);
//
//                    Intent intent = getIntent();
//                    intent.putExtra("userName", NamefromDB);
//                    intent.putExtra("phone", PhonefromDB);
//                    intent.putExtra("dob", DOBfromDB);
//                    intent.putExtra("email", EmailfromDB);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

    private void showUserInfo( FirebaseUser user){
        String userID = user.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userinfo = snapshot.getValue(User.class);
                if (userinfo != null){
                    name = user.getDisplayName();
                    phone = user.getPhoneNumber();
                    dob = userinfo.getDob();
                    email= user.getEmail();

                    binding.txtName.setText(name);
                    binding.txtPhone.setText(phone);
                    binding.txtDOB.setText(dob);
                    binding.txtEmail.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserInformation.this, "Đã có lỗi xảy ra, vui lòng thử lại!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}