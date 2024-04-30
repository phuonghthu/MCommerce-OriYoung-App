package com.group6.oriyoung;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.group6.oriyoung.databinding.ActivityDialogInforReceivingBinding;
public class DialogInforReceiving extends Dialog {
    private EditText edtName, edtPhone, edtAddress;
    private Button btnSave, btnHuy;

    public DialogInforReceiving(Context context) {
        super(context);
        setContentView(R.layout.activity_dialog_infor_receiving); // Sử dụng layout tùy chỉnh

        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        btnSave = findViewById(R.id.btnSave);
        btnHuy = findViewById(R.id.btnHuy);

        // Xử lý khi nhấn nút "Lưu"
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String phone = edtPhone.getText().toString();
                String address = edtAddress.getText().toString();

                if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    // Truyền thông tin về Activity chính hoặc cập nhật trực tiếp
                    Checkout activity = (Checkout) getOwnerActivity();
                    activity.binding.txtUserName.setText(name);
                    activity.binding.txtUserPhone.setText(phone);
                    activity.binding.txtUserAddress.setText(address);
                    dismiss(); // Đóng dialog sau khi lưu
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // Đóng dialog khi người dùng nhấn nút "Hủy"
            }
        });

    }
}
