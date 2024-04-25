package com.group6.oriyoung;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group6.oriyoung.databinding.ActivityChangePasswordBinding;

public class ChangePassword extends AppCompatActivity {
    ActivityChangePasswordBinding binding;
    TextInputLayout inputCurrentPass, inputNewPass, inputPassAgain;
    TextInputEditText txtCurrentPass, txtNewPass, txtPassAgain;
    String current, newpass, passagain;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String TAG = "ChangePassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.toolbarTitle.setText("Đổi mật khẩu");

        inputCurrentPass = findViewById(R.id.inputCurrentPass);
        inputNewPass = findViewById(R.id.inputNewPass);
        inputPassAgain = findViewById(R.id.inputPassAgain);
        txtCurrentPass = findViewById(R.id.txtCurrentPass);
        txtNewPass = findViewById(R.id.txtNewPass);
        txtPassAgain = findViewById(R.id.txtPassAgain);
        addEvents();




    }




    private void addEvents() {
        binding.btnHUY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePassword.this, Setting.class);
                startActivity(intent);
            }
        });
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePassword.this, Setting.class);
                finish();
            }
        });
        binding.txtCurrentPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                current = binding.txtCurrentPass.getText().toString().trim();

                if (current.length() < 6) {
                    inputCurrentPass.setError(getString(R.string.Login_pass_error));
                    inputCurrentPass.setErrorIconDrawable(null);
                    inputCurrentPass.setBoxStrokeErrorColor(ColorStateList.valueOf(getColor(R.color.error)));

                }else{
                    inputCurrentPass.setError(null);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.txtNewPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newpass = binding.txtNewPass.getText().toString().trim();

                if (newpass.length() < 6) {
                    inputNewPass.setError(getString(R.string.Login_pass_error));
                    inputNewPass.setErrorIconDrawable(null);
                    inputNewPass.setBoxStrokeErrorColor(ColorStateList.valueOf(getColor(R.color.error)));

                }else{
                    inputNewPass.setError(null);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.txtPassAgain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passagain = binding.txtPassAgain.getText().toString().trim();

                if (passagain.length() < 6 ) {
                    inputPassAgain.setError(getString(R.string.Login_pass_error));
                    inputPassAgain.setErrorIconDrawable(null);
                    inputPassAgain.setBoxStrokeErrorColor(ColorStateList.valueOf(getColor(R.color.error)));

                }else{
                    inputPassAgain.setError(null);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.btnLUUTHAYDOI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current = txtCurrentPass.getText().toString().trim();
                newpass = txtNewPass.getText().toString().trim();
                passagain = txtPassAgain.getText().toString().trim();
                if (TextUtils.isEmpty(current)){
                    inputCurrentPass.setError(getString(R.string.Empty_error));
                } else if (TextUtils.isEmpty(newpass)) {
                    inputNewPass.setError(getString(R.string.Empty_error));
                } else if (newpass.compareTo(current) == 0) {
                    inputNewPass.setError("Vui lòng nhập mật khẩu mới!");
                } else if (TextUtils.isEmpty(passagain)) {
                    inputPassAgain.setError(getString(R.string.Empty_error));
                } else if (passagain.compareTo(newpass) != 0 ) {
                    inputPassAgain.setError("Mật khẩu không khớp");
                }else {
                    changePassword();
                }

            }
        });
    }

    private void changePassword() {
        // Get auth credentials from the user for re-authentication. The example below shows
// email and password credentials but there are multiple possible providers,
// such as GoogleAuthProvider or FacebookAuthProvider.
        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), current);

        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Re-authenticate thành công, cập nhật mật khẩu mới cho người dùng
                            user.updatePassword(newpass)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                // Cập nhật mật khẩu thành công, hiển thị thông báo và thoát
                                                Toast.makeText(ChangePassword.this, "Thay đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                                                finish();
                                            } else {
                                                // Cập nhật mật khẩu thất bại, hiển thị thông báo lỗi
                                                Toast.makeText(ChangePassword.this, "Đã có lỗi xảy ra khi cập nhật mật khẩu. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            // Re-authenticate thất bại, hiển thị thông báo lỗi
                            Toast.makeText(ChangePassword.this, "Xác thực thất bại. Vui lòng kiểm tra lại mật khẩu hiện tại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }





//        user.reauthenticate(authCredential).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//
//                user.updatePassword(newpass).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        DialogNotificationSuccessfully dialogNotificationSuccessfully = new DialogNotificationSuccessfully(ChangePassword.this);
//                        dialogNotificationSuccessfully.show();
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                if (dialogNotificationSuccessfully != null && dialogNotificationSuccessfully.isShowing()) {
//                                    dialogNotificationSuccessfully.dismiss();
//                                }
//                            }
//                        }, 3000); // Đóng dialog sau 3 giây (3000 mili giây)
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(ChangePassword.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//            }
//        })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(ChangePassword.this, e.getMessage() , Toast.LENGTH_SHORT).show();
//                    }
//                });
}