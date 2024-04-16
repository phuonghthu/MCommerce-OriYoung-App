package com.group6.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.group6.oriyoung.DialogLogout;
import com.group6.oriyoung.R;

public class AccountSecondFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_account_second, container, false);

        Button btnLogout = rootView.findViewById(R.id.btnlogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo một instance của DialogLogout
                DialogLogout dialogLogout = new DialogLogout(getActivity());
                // Hiển thị dialog
                dialogLogout.show();
            }
        });

        return rootView;
    }
}