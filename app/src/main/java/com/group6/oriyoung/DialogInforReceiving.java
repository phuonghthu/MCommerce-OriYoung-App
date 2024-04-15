package com.group6.oriyoung;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.group6.oriyoung.databinding.ActivityDialogInforReceivingBinding;

public class DialogInforReceiving extends Dialog {
    ActivityDialogInforReceivingBinding binding;

    public DialogInforReceiving(Context context) {
        super(context);
        // Lưu lại tham chiếu đến Checkout nếu cần thiết
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDialogInforReceivingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }


}
