package com.group6.oriyoung;

import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.group6.models.User;
import com.group6.oriyoung.databinding.ActivityLogin2Binding;

public class LoginActivity extends AppCompatActivity {

    ActivityLogin2Binding binding;
    ImageView googleAuth, facebookAuth;
    FirebaseDatabase database;
    GoogleSignInClient signInClient;
    GoogleSignInOptions signInOptions;
    BeginSignInRequest signInRequest;
    int RC_SIGN_IN = 1234;
    private TextInputEditText txtInputEmail, txtInputPassword;
    private FirebaseAuth authProfile;
    private static final String TAG = "LoginActivity";

    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS_FILE = "login_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogin2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences(SHARED_PREFS_FILE, MODE_PRIVATE);

        authProfile = FirebaseAuth.getInstance();
        binding.txtInputEmail.requestFocus();


        googleAuth = findViewById(R.id.imvGoogle);
        facebookAuth = findViewById(R.id.imvFacebook);
        database = FirebaseDatabase.getInstance();

//        signInClient = Identity.getSignInClient(this);
//        signInRequest = BeginSignInRequest.builder()
//                .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
//                        .setSupported(true)
//                        .build())
//                        .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                                .setSupported(true)
//                                .setServerClientId(getString(R.string.default_web_client_id))
//                                .setFilterByAuthorizedAccounts(false)
//                                .build())
//                                .setAutoSelectEnabled(true)
//                                        .build();


        signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        signInClient = GoogleSignIn.getClient(this,signInOptions);


        googleAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();
            }

        });





        addEvents();
        binding();
    }


    private void googleSignIn() {
        Intent intent = signInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                authProfile(account.getIdToken());
            } catch (ApiException e) {

                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();;
            }
        }
    }

    private void authProfile(String idToken) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(idToken,null);
        authProfile.signInWithCredential(authCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = authProfile.getCurrentUser();
                            User user = new User();
                            firebaseUser.getUid();
                            user.setUserName(firebaseUser.getDisplayName());
                            user.setEmail(firebaseUser.getEmail());
//
                            database.getReference().child("User").child(firebaseUser.getUid()).setValue(user);
//

                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        }else {
                            Toast.makeText(LoginActivity.this, "Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

//    private void googleSignIn() {
//        signInClient.beginSignIn(signInRequest)
//                .addOnSuccessListener(this, new OnSuccessListener<BeginSignInResult>() {
//                    @Override
//                    public void onSuccess(BeginSignInResult beginSignInResult) {
//                        try {
//                            startIntentSenderForResult(
//                                    beginSignInResult.getPendingIntent().getIntentSender(), REQ_ONE_TAP, null, 0, 0, 0);
//
//                        }catch (IntentSender.SendIntentException e){
//                            Log.e (TAG ,"Đã xảy ra lỗi!" +e.getLocalizedMessage());
//                        }
//                    }
//                })
//                .addOnFailureListener(this, (OnFailureListener) e -> Log.d(TAG, e.getLocalizedMessage()));
//    }






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
                    binding.progressLogin.setVisibility(View.VISIBLE);
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
                Intent intent = new Intent(LoginActivity.this, ForgetPassword.class);
                startActivity(intent);
            }
        });
        binding.imvBack.setVisibility(View.GONE);
    }

    private void loginUser(String email, String password) {
        authProfile.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();

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


//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        // Kiểm tra trạng thái đăng nhập của người dùng
//        boolean isUserLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
//        boolean hasShownOnboarding = sharedPreferences.getBoolean("hasShownOnboarding", false);
//        FirebaseUser currentUser = authProfile.getCurrentUser();
//
//        if (currentUser == null && hasShownOnboarding) {
//            Intent intent = new Intent(LoginActivity.this, OnboardingActivity.class);
//            startActivity(intent);
//            finish();
//        } else if (currentUser != null && !hasShownOnboarding) {
//            return;
//        }
////            // Người dùng đã đăng nhập và chưa hiển thị onboarding
////            FirebaseUser currentUser = authProfile.getCurrentUser();
////            if (currentUser != null) {
////                // Đã đăng nhập và chưa đăng xuất, điều hướng đến HomeActivity
////                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
////                startActivity(intent);
////                finish();
////            }
//         else {
//            // Người dùng đã đăng xuất hoặc đã hiển thị onboarding, điều hướng đến OnboardingActivity
//            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//        }
//    }
}