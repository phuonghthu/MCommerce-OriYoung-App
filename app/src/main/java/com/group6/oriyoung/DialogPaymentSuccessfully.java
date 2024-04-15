package com.group6.oriyoung;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.group6.oriyoung.databinding.ActivityDialogPaymentSuccessfullyBinding;

public class DialogPaymentSuccessfully extends Dialog {

    public DialogPaymentSuccessfully(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Sử dụng DataBinding để gắn layout
        ActivityDialogPaymentSuccessfullyBinding binding = ActivityDialogPaymentSuccessfullyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
