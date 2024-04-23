package com.group6.oriyoung;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.group6.oriyoung.databinding.ActivityForgetPasswordBinding;

public class ForgetPassword extends AppCompatActivity {
    ActivityForgetPasswordBinding binding;
    String email;
    FirebaseAuth mAuth;
    boolean isValidEmail = false;
    TextInputEditText txtInputEmail;
    TextInputLayout inputEmail;
    private static String TAG = "ForgetPasswordActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        binding.inputEmail.requestFocus();
        txtInputEmail = findViewById(R.id.txtEmail);
        inputEmail = findViewById(R.id.inputEmail);

        addEvents();
    }

    private void addEvents() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetPassword.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        binding.btnCotinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidEmail) {
                    Toast.makeText(ForgetPassword.this, "Invalid Email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(ForgetPassword.this, ResetPassword.class);
                startActivity(intent);
            }
        });

        binding.txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isValidEmail = false;
                email = binding.txtEmail.getText().toString().trim();

                if (!email.matches("^(.+)@(.+)$")) {
                    inputEmail.setError(getString(R.string.Login_mail_error));

                }else{
                    isValidEmail = true;
                    inputEmail.setError(null);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
        binding.btnCotinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = binding.txtEmail.getText().toString().trim();

                if(email.isEmpty() ) {
                    inputEmail.setError(getString(R.string.Empty_error));
                    inputEmail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    inputEmail.setError(getString(R.string.Login_mail_error));
                    inputEmail.requestFocus();

                } else{

                    resetPassword  ();
                }
            }
        });

    }
    private void resetPassword() {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    showDialog();
                }else {try {
                    throw task.getException();
                }catch (FirebaseAuthInvalidUserException e){
                    inputEmail.setError("Tài khoản không tồn tại!");
                }catch (Exception e){
                    Log.e(TAG, e.getMessage());
                    Toast.makeText(ForgetPassword.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                }
            }
        });
    }
    private void showDialog() {
        // Tạo dialog
        Dialog dialog = new Dialog(ForgetPassword.this);
        dialog.setContentView(R.layout.custom_dialog_fail);
        TextView dialog_content = dialog.findViewById(R.id.txtContent);
        Button btnTryAgain = dialog.findViewById(R.id.btnTryAgain);

        dialog_content.setText("Tài khoản không tồn tại. Vui lòng thử lại!");



        // Xử lý sự kiện khi người dùng nhấp vào nút "Thử lại"
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đóng dialog
                dialog.dismiss();
                // Quay lại LoginActivity
            }
        });

        // Thiết lập dialog không thể bị hủy khi nhấn bên ngoài
        dialog.setCanceledOnTouchOutside(false);

        // Hiển thị dialog
        dialog.show();
    }
}