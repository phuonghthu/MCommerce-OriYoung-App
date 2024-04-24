package com.group6.oriyoung;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group6.models.User;
import com.group6.oriyoung.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    TextInputEditText txtInputName, txtInputEmail, txtInputPassword, txtInputPasswordAgain;

    private static final String TAG="RegisterActivity";
//    boolean isValidName = false;
//    boolean isValidEmail = false;
//    boolean isValidPassword = false;
//    boolean isValidPasswordAgain = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.txtInputName.requestFocus();

        binding();

        addEvents();


    }

    private void binding() {
        txtInputName = binding.txtInputName;
        txtInputEmail = binding.txtInputEmail;
        txtInputPassword = binding.txtInputPassword;
        txtInputPasswordAgain = binding.txtInputPasswordAgain;
    }


    private void addEvents() {
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = txtInputName.getText().toString();
                String email = txtInputEmail.getText().toString();
                String txtPw = txtInputPassword.getText().toString();
                String txtPwAgain = txtInputPasswordAgain.getText().toString();

                if (TextUtils.isEmpty(userName)) {
//                    binding.inputName.setHelperText("Hãy nhập tên của bạn!");
                    txtInputName.setError("Tên không được để trống", null);
                    txtInputName.requestFocus();
                } else if (TextUtils.isEmpty(email)) {
//                    binding.inputEmail.setHelperText("Hãy nhập email của bạn!");
                    txtInputEmail.setError("Email không được để trống", null);
                    txtInputEmail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                    binding.inputEmail.setHelperText("Hãy nhập đúng định dạng email");
                    txtInputEmail.setError("Email không đúng định dạng",null);
                    txtInputEmail.requestFocus();
                } else if (TextUtils.isEmpty(txtPw)) {
//                    binding.inputPw.setHelperText("Hãy nhập mật khẩu!");
                    txtInputPassword.setError("Mật khẩu không được để trống", null) ;
                    txtInputPassword.requestFocus();
                } else if (txtPw.length() < 6) {
//                    binding.inputPw.setHelperText("Mật khẩu phải có ít nhất 6 kí tự");
                    txtInputPassword.setError("Mật khẩu phải có ít nhất 6 kí tự", null);
                    txtInputPassword.requestFocus();
                } else if (TextUtils.isEmpty(txtPwAgain)) {
//                    binding.inputPwAgain.setHelperText("Hãy nhập lại mật khẩu!");
                    txtInputPasswordAgain.setError("Xin hãy xác nhận mật khẩu", null);
                    txtInputPasswordAgain.requestFocus();
                } else if (!txtPw.equals(txtPwAgain)) {
//                    binding.inputPwAgain.setHelperText("Mật khẩu không khớp!");
                    txtInputPasswordAgain.setError("Mật khẩu không khớp", null);
                    txtInputPasswordAgain.requestFocus();
                    //Clear the entered password
                    txtInputPassword.clearComposingText();
                    txtInputPasswordAgain.clearComposingText();
                } else {
//                    binding.inputName.setHelperTextEnabled(false);
//                    binding.inputEmail.setHelperTextEnabled(false);
//                    binding.inputPw.setHelperTextEnabled(false);
//                    binding.inputPwAgain.setHelperTextEnabled(false);
                    registerUser(userName, email, txtPw);
                }
            }
        });
        binding.txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // Register user using the credentials given
    private void registerUser(String userName, String email, String txtPw) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email, txtPw).addOnCompleteListener(RegisterActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            FirebaseUser firebaseUser = auth.getCurrentUser();

                            //Update display name of user
                            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(userName).build();
                            assert firebaseUser != null;
                            firebaseUser.updateProfile(profileChangeRequest);


                            // Enter user to Firebase
                            User user = new User(userName, null, email, null);

                            // Extract user ref from Db
                            DatabaseReference refUser = FirebaseDatabase.getInstance().getReference("User");

                            refUser.child(firebaseUser.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        // Send Verification Email
                                        firebaseUser.sendEmailVerification();

                                        // Dialog success navigate to homepage
                                        LayoutInflater inflater = getLayoutInflater();
                                        View dialogView = inflater.inflate(R.layout.custom_dialog_success, null);

                                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                        builder.setView(dialogView);

                                        Button btnKhamPhaNgay = dialogView.findViewById(R.id.btnCTA);

                                        final AlertDialog dialog = builder.create();
                                        dialog.show();


                                        // Bắt sự kiện khi nút "Khám phá ngay" được nhấn
                                        btnKhamPhaNgay.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialog.dismiss();
                                                // Chuyển hướng về màn hình chính khi người dùng nhấn nút "Khám phá ngay"
                                                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                                                // Prevent user from returning back to Register Activity on pressing back button
                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                                                        | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);
                                                finish();
                                            }
                                        });
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Đăng ký thất bại. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });





                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                txtInputPassword.setError("Mật khẩu yếu!", null);
                                txtInputPassword.requestFocus();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                txtInputEmail.setError("Email không đúng hoặc đã được sử dụng", null);
                                txtInputEmail.requestFocus();
                            } catch (FirebaseAuthUserCollisionException e) {
                                Toast.makeText(RegisterActivity.this, "Email đã tồn tại!", Toast.LENGTH_SHORT).show();
                                txtInputEmail.requestFocus();
                            } catch (Exception e) {
                                Log.e(TAG, e.getMessage());
                                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                });
    }


}