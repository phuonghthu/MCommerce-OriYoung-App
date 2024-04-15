package com.group6.oriyoung;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.group6.oriyoung.databinding.ActivityDialogInforReceivingBinding;

public class DialogInforReceiving extends Dialog {

    public DialogInforReceiving(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Sử dụng DataBinding để gắn layout
        ActivityDialogInforReceivingBinding binding = ActivityDialogInforReceivingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
