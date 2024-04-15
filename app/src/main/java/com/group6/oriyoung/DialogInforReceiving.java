package com.group6.oriyoung;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.group6.oriyoung.databinding.ActivityDialogInforReceivingBinding;

public class DialogInforReceiving extends Dialog {
    ArrayAdapter<CharSequence> provinceAdapter;
    ArrayAdapter<CharSequence> districtAdapter;
    ArrayAdapter<CharSequence> villageAdapter;
    ActivityDialogInforReceivingBinding binding;

    public DialogInforReceiving(Context context) {
        super(context);
        provinceAdapter = ArrayAdapter.createFromResource(context, R.array.province_array, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        districtAdapter = ArrayAdapter.createFromResource(context, R.array.district_array, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        villageAdapter = ArrayAdapter.createFromResource(context, R.array.village_array, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDialogInforReceivingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnHUY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Spinner spinnerProvince = findViewById(R.id.spinnerProvince);
        spinnerProvince.setAdapter(provinceAdapter);

        Spinner spinnerDistrict = findViewById(R.id.spinnerDistrict);
        spinnerDistrict.setAdapter(districtAdapter);

        Spinner spinnerVillage = findViewById(R.id.spinnerVillage);
        spinnerVillage.setAdapter(villageAdapter);
    }
}
