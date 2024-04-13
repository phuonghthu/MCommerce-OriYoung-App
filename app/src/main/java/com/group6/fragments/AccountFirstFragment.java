package com.group6.fragments;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group6.oriyoung.ContactOriyoung;
import com.group6.oriyoung.HomeActivity;
import com.group6.oriyoung.MyVoucher;
import com.group6.oriyoung.OnboardingActivity;
import com.group6.oriyoung.R;
import com.group6.oriyoung.Setting;
import com.group6.oriyoung.SupportCenter;
import com.group6.oriyoung.UserInformation;
import com.group6.oriyoung.databinding.FragmentAccountFirstBinding;
import com.group6.oriyoung.databinding.FragmentHomeBinding;


public class AccountFirstFragment extends Fragment {

    FragmentAccountFirstBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAccountFirstBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        addEvents();
        return view;
    }

    private void addEvents() {

        binding.thongtincanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), UserInformation.class);
                startActivity(intent);
            }
        });
        binding.hotro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), SupportCenter.class);
                startActivity(intent);
            }
        });

        binding.caidat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), Setting.class);
                startActivity(intent);
            }
        });

        binding.uudaicuatoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyVoucher.class);
                startActivity(intent);

            }
        });

        binding.lienheoriyoung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ContactOriyoung.class);
                startActivity(intent);
            }
        });
    }
}