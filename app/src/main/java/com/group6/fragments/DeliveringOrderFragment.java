package com.group6.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.group6.adapters.DeliveringAdapter;
import com.group6.models.CheckoutCart;
import com.group6.models.OrderHistory;
import com.group6.oriyoung.R;
import com.group6.oriyoung.databinding.FragmentDeliveringOrderBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeliveringOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeliveringOrderFragment extends Fragment {

    FragmentDeliveringOrderBinding binding;
    DeliveringAdapter adapter;
    ArrayList<OrderHistory> orderHistory;
    ArrayList<CheckoutCart> orderItems;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DeliveringOrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeliveringOrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeliveringOrderFragment newInstance(String param1, String param2) {
        DeliveringOrderFragment fragment = new DeliveringOrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDeliveringOrderBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        orderItems = new ArrayList<>();

        loadItems();

        return view;
    }

    private void loadItems() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rvDelivering.setLayoutManager(linearLayoutManager);
        orderItems = new ArrayList<>(); // Khởi tạo danh sách orderItems

        CheckoutCart orderItem = new CheckoutCart("Cocoon Kem Ủ Tóc Bưởi Giảm Gãy Rụng & Dưỡng Mềm Tóc 200ml", 155000, R.drawable.img_product, 2);
        orderItems.add(orderItem);

        orderHistory = new ArrayList<>(); // Khởi tạo danh sách orderHistory

        OrderHistory historyItem = new OrderHistory("MD123", "Đang giao", orderItems, 4, 400000);
        orderHistory.add(historyItem);

        adapter = new DeliveringAdapter(getContext(), orderHistory, orderItems);
        binding.rvDelivering.setAdapter(adapter);
    }
}