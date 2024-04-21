package com.group6.oriyoung;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group6.oriyoung.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;
    TextInputLayout inputEmail, inputPass;
    TextInputEditText txtinputEmail, txtinputPass;
    String email, password;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        inputEmail = findViewById(R.id.inputEmail);
        inputPass = findViewById(R.id.inputPassword);
        mAuth = FirebaseAuth.getInstance();
        txtinputEmail = findViewById(R.id.editTextEmail);
        txtinputPass = findViewById(R.id.editTextPassword);









        addEvents();
    }

    private void addEvents() {
        binding.txtForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, ForgetPassword.class));
            }
        });
        binding.txtSignupNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, SignUp.class));
                finish();
            }
        });
        binding.editTextInputMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(binding.editTextInputMail.getText().toString()) && !TextUtils.isEmpty(binding.editTextPassword.getText().toString())) {
                    binding.inputEmail.setError(getString(R.string.Empty));
                    binding.inputEmail.getBoxStrokeErrorColor();
                } else {
                    binding.inputEmail.setError(null);
                }
            }
        });
        binding.editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(binding.editTextPassword.getText().toString()) && !TextUtils.isEmpty(binding.editTextPassword.getText().toString())) {
                    binding.inputPassword.setError(getString(R.string.Empty));
                    binding.inputPassword.getBoxStrokeErrorColor();
                } else {
                    binding.inputPassword.setError(null);
                }
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email.isEmpty() || !email.matches("^(.+)@(.+)$")) {
                    binding.inputEmail.setError(getString(R.string.Login_mail_error));
                    binding.inputEmail.requestFocus();
                } else if ((password.length() < 8) || !password.matches("^(?=.*\\d)[0-9a-zA-Z]{8,}$")){

                    binding.inputPassword.setError(getString(R.string.Login_pass_error));
                    binding.inputPassword.requestFocus();
                } else {

                    loginUser(email, password);
                }
            }
        });

    }
    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        // Nếu đăng nhập thành công, intent đến HomeActivity
                        startActivity(new Intent(Login.this, HomeActivity.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Nếu đăng nhập không thành công, hiển thị dialog thông báo lỗi
                        showDialog();
                    }
                });
    };





//        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                email = binding.editTextInputMail.getText().toString();
//                password = binding.editTextPassword.getText().toString();
//
//                // Kiểm tra xem email và mật khẩu có được nhập vào không
//                if (email.isEmpty() || password.isEmpty()) {
//                    // Hiển thị lỗi nếu email hoặc mật khẩu trống
//                    if (email.isEmpty()) {
//                        binding.inputEmail.setError(getString(R.string.Empty));
//                    } else {
//                        binding.inputEmail.setError(null);
//                    }
//                    if (password.isEmpty()) {
//                        binding.inputPassword.setError(getString(R.string.Empty));
//                    } else {
//                        binding.inputPassword.setError(null);
//                    }
//                    return;
//                }
//
//                // Kiểm tra xem email có đúng định dạng không
//                if (!email.matches("^(.+)@(.+)$")) {
//                    binding.inputEmail.setError(getString(R.string.Login_mail_error_1));
//                    return;
//                } else {
//                    binding.inputEmail.setError(null);
//                }
//                if (!password.matches("^(?=.*\\d)[0-9a-zA-Z]{8,}$")) {
//                    binding.inputPassword.setError(getString(R.string.Login_pass_error));
//                    return;
//                } else {
//                    binding.inputPassword.setError(null);
//                }
//
//
//
//                mAuth.signInWithEmailAndPassword(email, password)
//                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                            @Override
//                            public void onSuccess(AuthResult authResult) {
//                                // Nếu đăng nhập thành công, intent đến HomeActivity
//                                startActivity(new Intent(Login.this, HomeActivity.class));
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                // Nếu đăng nhập không thành công, hiển thị dialog thông báo lỗi
//                                showDialog();
//                            }
//                        });
//                binding.editTextPassword.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//
//                    @Override
//                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
//                            binding.inputPassword.setHelperText("Password need more than 8 digits or numbers.");
//                        }
//                        else {
//
//                            binding.inputPassword.setHelperText(null);
//                        }
//
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable s) {}
//                });
//
//
//            }
//        });





//


//    View.OnClickListener onClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            if (v.equals(binding.btnLogin)) {
////                Create and show custom dialog
//                Dialog dialog = new Dialog(Login.this);
//                dialog.setContentView(R.layout.custom_dialog_fail);
//                Button btnTryAgain = findViewById(R.id.btnTryAgain);
//                btnTryAgain.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//                dialog.setCanceledOnTouchOutside(false);
//                dialog.show();
//
//            }
//        }
//    };


//     Phương thức hiển thị dialog thông báo

    private void showDialog() {
        // Tạo dialog
        Dialog dialog = new Dialog(Login.this);
        dialog.setContentView(R.layout.custom_dialog_fail);

        // Xác định nút "Thử lại" trong dialog
        Button btnTryAgain = dialog.findViewById(R.id.btnTryAgain);

        // Xử lý sự kiện khi người dùng nhấp vào nút "Thử lại"
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đóng dialog
                dialog.dismiss();

                // Quay lại LoginActivity
                finish();
            }
        });

        // Thiết lập dialog không thể bị hủy khi nhấn bên ngoài
        dialog.setCanceledOnTouchOutside(false);

        // Hiển thị dialog
        dialog.show();
    }
    }