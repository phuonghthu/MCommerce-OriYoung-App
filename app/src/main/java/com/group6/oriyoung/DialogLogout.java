package com.group6.oriyoung;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

public class DialogLogout extends AppCompatActivity {

    public DialogLogout(FragmentActivity activity) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_logout);
        Button btnDongy = findViewById(R.id.btnDongy);
        Button btnHuy = findViewById(R.id.btnHuy);
        btnDongy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
    }

    public void show() {
    }
}