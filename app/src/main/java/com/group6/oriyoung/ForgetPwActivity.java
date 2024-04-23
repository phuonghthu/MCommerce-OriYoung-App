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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.group6.oriyoung.databinding.ActivityForgetPwBinding;

public class ForgetPwActivity extends AppCompatActivity {

    ActivityForgetPwBinding binding;
    FirebaseAuth mAuth;
    String email, password, passwordagain;
    TextInputEditText txtInputEmail, txtInputPassword, txtInputPasswordAgain;
    boolean isValidEmail, isValidPass, isValidPassAgain;
    private static String TAG = "ForgetPwActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetPwBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        binding.txtInputEmail.requestFocus();
        txtInputEmail = findViewById(R.id.txtInputEmail);
        txtInputPassword = findViewById(R.id.txtInputPassword);
        txtInputPasswordAgain = findViewById(R.id.txtInputPasswordAgain);

        addEvents();
    }

    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetPwActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        binding.txtInputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isValidPass = false;
                password = binding.txtInputPassword.getText().toString().trim();

                if (!password.matches("^(?=.*\\d).{8,}$")) {
                    txtInputPassword.setError(getString(R.string.Login_pass_error), null);

                }else{
                    isValidPass = true;
                    txtInputPassword.setError(null);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
        binding.txtInputPasswordAgain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isValidPassAgain = false;
                passwordagain = binding.txtInputPasswordAgain.getText().toString().trim();

                if (!passwordagain.matches("^(?=.*\\d).{8,}$")) {
                    txtInputPasswordAgain.setError(getString(R.string.Login_pass_error), null);

                }else{
                    isValidPassAgain = true;
                    txtInputPasswordAgain.setError(null);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
        binding.txtInputEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isValidEmail = false;
                email = binding.txtInputEmail.getText().toString().trim();

                if (!email.matches("^(.+)@(.+)$")) {
                    txtInputEmail.setError(getString(R.string.Login_mail_error));

                }else{
                    isValidEmail = true;
                    txtInputEmail.setError(null);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 email = binding.txtInputEmail.getText().toString().trim();
                 password = binding.txtInputPassword.getText().toString().trim();
                 passwordagain = binding.txtInputPasswordAgain.getText().toString().trim();
                if(email.isEmpty() ) {
                    txtInputEmail.setError(getString(R.string.Empty_error));
                    txtInputEmail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    txtInputEmail.setError(getString(R.string.Login_mail_error));
                    txtInputEmail.requestFocus();
                } else if (password.isEmpty() || password.length() < 8 || !password.matches("^(?=.*\\d).{8,}$")) {
                    txtInputPassword.setError(getString(R.string.Login_pass_error), null);
                    txtInputPassword.requestFocus();
                } else if(passwordagain.isEmpty() || passwordagain.length() < 8 || !passwordagain.matches("^(?=.*\\d).{8,}$")) {
                    txtInputPasswordAgain.setError(getString(R.string.Login_pass_error), null);
                    txtInputPasswordAgain.requestFocus();
                } else if ((txtInputPassword.getText().toString()) != (txtInputPasswordAgain.getText().toString())) {
                    txtInputPasswordAgain.setError(getString(R.string.Login_pass_error), null);
                    txtInputPasswordAgain.requestFocus();
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
                    txtInputEmail.setError("Tài khoản không tồn tại!");
                }catch (Exception e){
                    Log.e(TAG, e.getMessage());
                    Toast.makeText(ForgetPwActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                }
            }
        });
    }

    private void showDialog() {
        // Tạo dialog
        Dialog dialog = new Dialog(ForgetPwActivity.this);
        dialog.setContentView(R.layout.custom_dialog_success);
        TextView dialog_title = dialog.findViewById(R.id.txtTitle);
        TextView dialog_content = dialog.findViewById(R.id.txtContent);
        Button login_again = dialog.findViewById(R.id.btnCTA);

        dialog_title.setText("Thay đổi mật khẩu thành công");
        dialog_content.setText("Bạn đã thay đổi mật khẩu thành công. Hãy đăng nhập để khám phá ngay hàng ngàn ưu đãi từ OriYoung nhé!");
        login_again.setText("Đăng nhập");



        // Xử lý sự kiện khi người dùng nhấp vào nút "Dắng nhập"
        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đóng dialog
                startActivity(new Intent(ForgetPwActivity.this, LoginActivity.class));
                finish();
                // Quay lại LoginActivity
            }
        });

        // Thiết lập dialog không thể bị hủy khi nhấn bên ngoài
        dialog.setCanceledOnTouchOutside(false);

        // Hiển thị dialog
        dialog.show();
    }

}