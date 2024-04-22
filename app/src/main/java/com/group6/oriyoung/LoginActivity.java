package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.group6.oriyoung.databinding.ActivityLogin2Binding;

public class LoginActivity extends AppCompatActivity {

    ActivityLogin2Binding binding;
    private TextInputEditText txtInputEmail, txtInputPassword;
    private FirebaseAuth authProfile;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogin2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        authProfile = FirebaseAuth.getInstance();
        binding.txtInputEmail.requestFocus();
        
        addEvents();
        binding();
    }

    private void binding() {
        txtInputEmail = binding.txtInputEmail;
        txtInputPassword = binding.txtInputPassword;
    }

    private void addEvents() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtInputEmail.getText().toString();
                String password = txtInputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    txtInputEmail.setError("Vui lòng nhập email!", null);
                    txtInputEmail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    txtInputEmail.setError("Email không đúng định dạng!", null);
                    txtInputEmail.requestFocus();
                } else if (TextUtils.isEmpty(password)) {
                    txtInputEmail.setError("Vui lòng nhập mật khẩu!", null);
                    txtInputPassword.requestFocus();
                } else if (password.length() < 6) {
                    txtInputPassword.setError("Mật khẩu phải có ít nhất 6 kí tự", null);
                    txtInputPassword.requestFocus();
                } else {
                    binding.progressLogin.setVisibility(View.GONE);
                    loginUser(email, password);
                }
            }
        });
        binding.txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        binding.txtForgetPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgetPwActivity.class);
                startActivity(intent);
            }
        });
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                finish();
            }
        });
    }

    private void loginUser(String email, String password) {
        authProfile.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = authProfile.getCurrentUser();
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
//                    if (firebaseUser.isEmailVerified()){
//
//                    } else {
//                        firebaseUser.sendEmailVerification();
//                        authProfile.signOut();
//                        showAlertDialog();
//                    }
                } else {
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        txtInputEmail.setError("Email không tồn tại!", null);
                        txtInputEmail.requestFocus();
                    } catch (FirebaseAuthInvalidUserException e) {
                        txtInputEmail.setError("Email không tồn tại hoặc không đúng!", null);
                        txtInputEmail.requestFocus();
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                        Toast.makeText(LoginActivity.this, "Đã có lỗi xảy ra. Vui lòng đăng nhập lại!", Toast.LENGTH_SHORT).show();
                    }
                    binding.progressLogin.setVisibility(View.GONE);
                }
            }
        });
    }

    private void showAlertDialog() {
        // Dialog success navigate to homepage
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog_fail, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setView(dialogView);

        TextView txtTitle = dialogView.findViewById(R.id.txtTitle);
        TextView txtContent = dialogView.findViewById(R.id.txtContent);
        Button btnTryAgain = dialogView.findViewById(R.id.btnTryAgain);

        txtTitle.setText("Email không xác thực");
        txtContent.setText("Hãy xác thực email của bạn!");



        // Bắt sự kiện khi nút "Khám phá ngay" được nhấn
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                //to email app in new window and not within our app
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Mở new window
                startActivity(intent);

            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();

    }
}