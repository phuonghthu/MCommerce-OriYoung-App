package com.group6.oriyoung;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.group6.oriyoung.databinding.ActivityDialogInforReceivingBinding;
public class DialogInforReceiving extends Dialog {
    ArrayAdapter<CharSequence> provinceAdapter;
    ArrayAdapter<CharSequence> districtAdapter;
    ArrayAdapter<CharSequence> villageAdapter;
    ActivityDialogInforReceivingBinding binding;

    public DialogInforReceiving(Context context) {
        super(context);
        Typeface typeface = ResourcesCompat.getFont(context, R.font.gilroy_regular);
        provinceAdapter = createAdapter(context, R.array.province_array, typeface);
        districtAdapter = createAdapter(context, R.array.district_array, typeface);
        villageAdapter = createAdapter(context, R.array.village_array, typeface);
    }

    private ArrayAdapter<CharSequence> createAdapter(Context context, int provinceArray, Typeface typeface) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, provinceArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return adapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDialogInforReceivingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnHuy.setOnClickListener(new View.OnClickListener() {
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
