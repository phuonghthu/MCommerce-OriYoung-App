package com.group6.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.group6.fragments.CancelOrderFragment;
import com.group6.fragments.DeliveringOrderFragment;
import com.group6.fragments.ReceivedOrderFragment;

public class OrderHistoryVPAdapter extends FragmentStateAdapter {
    public OrderHistoryVPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new DeliveringOrderFragment();
        } else if (position == 1) {
            return new ReceivedOrderFragment();
        } else {
            return new CancelOrderFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
