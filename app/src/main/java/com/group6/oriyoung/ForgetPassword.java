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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.group6.oriyoung.databinding.ActivityForgetPasswordBinding;

import java.util.Collections;
import java.util.UUID;

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
                    resetPassword();
                }
            }
        });


    }

    private void resetPassword() {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("User");

        usersRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Email tồn tại trong cơ sở dữ liệu, thực hiện quy trình đặt lại mật khẩu ở đây
                    mAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // Thành công, hiển thị thông báo và chuyển đến màn hình đăng nhập
                                        Toast.makeText(ForgetPassword.this, "Kiểm tra email của bạn để thay đổi mật khẩu!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(ForgetPassword.this, LoginActivity.class));
                                        finish();
                                    } else {
                                        // Có lỗi khi gửi yêu cầu reset mật khẩu
                                        showDialog();
                                    }
                                }
                            });
                } else {
                    // Email không tồn tại trong cơ sở dữ liệu
                    showDialog();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi khi truy vấn cơ sở dữ liệu
                Log.e(TAG, "Lỗi khi truy vấn cơ sở dữ liệu: " + databaseError.getMessage());
                Toast.makeText(ForgetPassword.this, "Đã có lỗi xảy ra. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
    }




//    private void resetPassword() {
//        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
//        Query query = usersRef.orderByChild("email").equalTo(email);
//
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    // Email tồn tại trong cơ sở dữ liệu, thực hiện quy trình đặt lại mật khẩu ở đây
//                    mAuth.sendPasswordResetEmail(email)
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        // Thành công, hiển thị thông báo và chuyển đến màn hình đăng nhập
//                                        Toast.makeText(ForgetPassword.this, "Kiểm tra email của bạn để thay đổi mật khẩu!", Toast.LENGTH_SHORT).show();
//                                        startActivity(new Intent(ForgetPassword.this, LoginActivity.class));
//                                        finish();
//                                    } else {
//                                        // Có lỗi khi gửi yêu cầu reset mật khẩu
//                                        showDialog();
//                                    }
//                                }
//                            });
//                } else {
//                    // Email không tồn tại trong cơ sở dữ liệu
//                    showDialog();
//                }
//            }



//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Xử lý lỗi khi truy vấn cơ sở dữ liệu
//                Log.e(TAG, "Lỗi khi truy vấn cơ sở dữ liệu: " + databaseError.getMessage());
//                Toast.makeText(ForgetPassword.this, "Đã có lỗi xảy ra. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }






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




