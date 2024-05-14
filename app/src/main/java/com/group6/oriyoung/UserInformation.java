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
    FirebaseUser user;
    String name, phone, dob, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserInformationBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        binding.toolbar.toolbarTitle.setText("Thông tin cá nhân");

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
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
                startActivity(new Intent(UserInformation.this, UserInfoDetail.class));
                finish();
            }
        });
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInformation.this, AccountFragment.class);
                finish();
            }
        });
    }



    private void showUserInfo( FirebaseUser user){
        String userID = user.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userinfo = snapshot.getValue(User.class);
                if (userinfo != null){
                    name = userinfo.getUserName();
                    phone = userinfo.getPhoneNumber();
                    dob = userinfo.getDob();
                    email= user.getEmail();

                    binding.txtName.setText(name);
                    binding.txtPhone.setText(phone);
                    binding.txtDOB.setText(dob);
                    binding.txtEmail.setText(email);
                }else {
                    Toast.makeText(UserInformation.this, "Đã có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserInformation.this, "Đã có lỗi xảy ra, vui lòng thử lại!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}