package com.group6.oriyoung;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.group6.models.User;
import com.group6.oriyoung.databinding.ActivityUserInfoEdtailBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInfoDetail extends AppCompatActivity {
    ActivityUserInfoEdtailBinding binding;
    private EditText edtBirth;
    private DatePickerDialog datePickerDialog;
    private Calendar selectedDateCalendar;
    String name, dob, email, phone;
    FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityUserInfoEdtailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.toolbarTitle.setText("Thông tin cá nhân");

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        addEvents();
        showUserInfo(user);


    }

    private void showUserInfo(FirebaseUser user) {
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


                    binding.txtname.setText(name);
                    binding.txtPhone.setText(phone);
                    binding.txtday.setText(dob);
                    binding.txtEmail.setText(email);
                }
                if (phone != null){
                    binding.inputPhone.setEnabled(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void editprofile(FirebaseUser user) {
        name = binding.txtname.getText().toString().trim();
        dob = binding.txtday.getText().toString().trim();
        phone = binding.txtPhone.getText().toString().trim();

        //Validate phone number
        String mobileregex = "0\\d{9}$";
        Pattern pattern = Pattern.compile(mobileregex);
        Matcher matcher = pattern.matcher(phone);


        if (TextUtils.isEmpty(name)){
            binding.inputName.setError(getString(R.string.Empty_error));
        }
        else if (TextUtils.isEmpty(dob)){
            binding.inputday.setError(getString(R.string.Empty_error));
        }
        else if (TextUtils.isEmpty(phone)){
            binding.inputPhone.setError(getString(R.string.Empty_error));
        } else if (!phone.matches(mobileregex)) {
            binding.inputPhone.setError("Số điện thoại không đúng, vui lòng kiểm tra lại!");
        } else if (!matcher.find()) {
            binding.inputPhone.setError(getString(R.string.Empty_error));
        }else{
            //get data from user
            binding.txtname.getText().toString();
            binding.txtday.getText().toString();
            binding.txtPhone.getText().toString();
            binding.inputPhone.setEnabled(false);


            //Enter into database
            User userdata = new User(name,phone, email, dob);
            reference = FirebaseDatabase.getInstance().getReference("User");
             String userID = user.getUid();
             reference.child(userID).setValue(userdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                 @Override
                 public void onComplete(@NonNull Task<Void> task) {
                     if (task.isSuccessful()){
                         UserProfileChangeRequest updateinfo =new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                         user.updateProfile(updateinfo);
                         showNotification();
                     }else {
                         try {
                             throw task.getException();
                         } catch (Exception e){
                             Toast.makeText(UserInfoDetail.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                         }
                     }
                 }
             });
        }
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
                editprofile(user);

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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 5000); // 5 giây
        showUserInfo(user);
    }

    }