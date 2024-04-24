package com.group6.oriyoung;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.group6.oriyoung.databinding.ActivityUserInfoEdtailBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UserInfoDetail extends AppCompatActivity {
    ActivityUserInfoEdtailBinding binding;
    private EditText edtBirth;
    private DatePickerDialog datePickerDialog;
    private Calendar selectedDateCalendar;
    String username, NameUser, dob, email, phone;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityUserInfoEdtailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.toolbarTitle.setText("Thông tin cá nhân");
        addEvents();

        showUserInfo();
    }
//=============Xử lý Intent và Dialog ==============================
    private void addEvents() {
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoDetail.this, UserInformation.class);
                startActivity(intent);
                finish();
            }
        });
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNameChanged() || isDOBChanged() || isEmailChanged() || isPhoneChanged()){
                    showNotification();
                }else {
                    Toast.makeText(UserInfoDetail.this, "Không có thông mới nào được thay đổi!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoDetail.this, UserInformation.class);
                finish();
            }
        });
        binding.txtday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(UserInfoDetail.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String selectedDate = dateFormat.format(calendar.getTime());
                binding.txtday.setText(selectedDate);
                Calendar selectedCalendar = Calendar.getInstance();
                selectedCalendar.set(year, month, dayOfMonth);
                binding.txtday.setText(selectedDate);
                // Lưu trữ ngày tháng năm đã chọn vào biến tạm
                selectedDateCalendar = selectedCalendar;
            }
        }, year, month, day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }
    private void showNotification() {
        Dialog dialog = new Dialog(UserInfoDetail.this);
        dialog.setContentView(R.layout.custom_toast);
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setWindowAnimations(R.style.DialogAnimation_Zoom);
        dialog.getWindow().setGravity(Gravity.CENTER);
    }
    public boolean isNameChanged(){
        if(!username.equals( binding.txtname.getText().toString())){
            reference.child(NameUser).child("userName").setValue(binding.txtname.getText().toString());
            username = binding.txtname.getText().toString();
            return true;
        }else{
            return false;
        }
    }
    public boolean isEmailChanged(){
        if(!email.equals( binding.txtEmail.getText().toString())){
            reference.child(NameUser).child("email").setValue(binding.txtEmail.getText().toString());
            email = binding.txtEmail.getText().toString();
            return true;
        }else{
            return false;
        }
    }
    public boolean isPhoneChanged(){
        if(!phone.equals( binding.txtPhone.getText().toString())){
            reference.child(NameUser).child("phone").setValue(binding.txtPhone.getText().toString());
            phone = binding.txtPhone.getText().toString();
            return true;
        }else{
            return false;
        }
    }
    public boolean isDOBChanged(){
        if(!dob.equals( binding.txtday.getText().toString())){
            reference.child(NameUser).child("dob").setValue(binding.txtday.getText().toString());
            dob = binding.txtday.getText().toString();
            return true;
        }else{
            return false;
        }
    }
//    public void showinfo(){
//        Intent intent = getIntent();
//        username = intent.getStringExtra("userName");
//        phone = intent.getStringExtra("phone");
//        email = intent.getStringExtra("email");
//        dob = intent.getStringExtra("dob");
//
//        binding.txtname.setText(username);
//        binding.txtday.setText(dob);
//        binding.txtPhone.setText(phone);
//        binding.txtEmail.setText(email);
//
//
//    }
//public void showinfo() {
//    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("User").child(username);
//    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot snapshot) {
//            if (snapshot.exists()) {
//                String nameFromDB = snapshot.child("userName").getValue(String.class);
//                String dobFromDB = snapshot.child("dob").getValue(String.class);
//                String phoneFromDB = snapshot.child("phone").getValue(String.class);
//                String emailFromDB = snapshot.child("email").getValue(String.class);
//
//                // Hiển thị thông tin người dùng từ cơ sở dữ liệu Firebase lên giao diện
//                binding.txtname.setText(nameFromDB);
//                binding.txtday.setText(dobFromDB);
//                binding.txtPhone.setText(phoneFromDB);
//                binding.txtEmail.setText(emailFromDB);
//            }
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError error) {
//            // Xử lý lỗi (nếu cần)
//        }
//    });
//}

    private void showUserInfo(){
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            binding.txtname.setText(bundle.getString("userName"));
            binding.txtEmail.setText(bundle.getString("email"));
            binding.txtday.setText(bundle.getString("dob"));
            binding.txtPhone.setText(bundle.getString("phone"));
        }
    }

}