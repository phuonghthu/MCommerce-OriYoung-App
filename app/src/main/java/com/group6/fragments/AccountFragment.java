package com.group6.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group6.models.User;
import com.group6.oriyoung.ContactOriyoung;
import com.group6.oriyoung.DialogLogout;
import com.group6.oriyoung.HomeActivity;
import com.group6.oriyoung.Login;
import com.group6.oriyoung.LoginActivity;
import com.group6.oriyoung.MyVoucher;
import com.group6.oriyoung.OrderHistoryActivity;
import com.group6.oriyoung.R;
import com.group6.oriyoung.RegisterActivity;
import com.group6.oriyoung.Setting;
import com.group6.oriyoung.SupportCenter;
import com.group6.oriyoung.UserInformation;
import com.group6.oriyoung.databinding.FragmentAccountBinding;


public class AccountFragment extends Fragment {

    FragmentAccountBinding binding;
    private FirebaseAuth authProfile;

    private String userName, phoneNumber, email, dob;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        authProfile = FirebaseAuth.getInstance();
        FirebaseUser currentUser = authProfile.getCurrentUser();

        if (currentUser == null){
            blankScreen(); // xử nếu chưa đăng nhập
        }else {
            showUserProfile(currentUser);
        }

        return view;
    }

    private void showUserProfile(FirebaseUser currentUser) {

        String userID = currentUser.getUid();

        // Extract User Ref
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("User");
        myRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null){
                    userName = currentUser.getDisplayName();
                    email = currentUser.getEmail();

                    binding.userLoggedin.setVisibility(View.VISIBLE);
                    binding.userBlank.setVisibility(View.GONE);

                    binding.txtUserName.setText(userName + " ngoan xinh yêu!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        binding.thongtincanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), UserInformation.class);
                startActivity(intent);
            }
        });
        binding.hotro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), SupportCenter.class);
                startActivity(intent);
            }
        });

        binding.caidat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), Setting.class);
                startActivity(intent);
            }
        });

        binding.uudaicuatoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), MyVoucher.class);
                startActivity(intent);

            }
        });

        binding.lienheoriyoung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), ContactOriyoung.class);
                startActivity(intent);
            }
        });


        binding.lichsudonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), OrderHistoryActivity.class);
                startActivity(intent);
            }
        });

        binding.btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo một instance của DialogLogout
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_dialog_confirm, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(dialogView);

                Button btndongy = dialogView.findViewById(R.id.btndongy);
                Button btnhuy = dialogView.findViewById(R.id.btnhuy);
                TextView txtTitle = dialogView.findViewById(R.id.txtTitle);
                TextView txtContent = dialogView.findViewById(R.id.txtContent);

                txtTitle.setText("Xác nhận đăng xuất");
                txtContent.setText("Khi đăng xuất tài khoản của bạn sẽ không thể tiếp tục mua sắm và sử dụng ưu đãi của OriYoung! Bạn chắc chắn muốn đăng xuất?");
                final AlertDialog dialog = builder.create();
                dialog.show();


                // Bắt sự kiện khi nút "Khám phá ngay" được nhấn
                btndongy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        authProfile.signOut();
                        // Chuyển hướng về màn hình chính khi người dùng nhấn nút "Khám phá ngay"
                        Intent intent = new Intent(getContext(), HomeActivity.class);
                        // Prevent user from returning back to Register Activity on pressing back button
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                                | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });

                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void blankScreen() {
        binding.userLoggedin.setVisibility(View.GONE);
        binding.userBlank.setVisibility(View.VISIBLE);
        binding.btnDangnhapngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        binding.btnlogout.setVisibility(View.GONE);

        View.OnClickListener clickListener = new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        };
        binding.thongtincanhan.setOnClickListener(clickListener);
        binding.caidat.setOnClickListener(clickListener);
        binding.uudaicuatoi.setOnClickListener(clickListener);
        binding.lichsudonhang.setOnClickListener(clickListener);

    }


}