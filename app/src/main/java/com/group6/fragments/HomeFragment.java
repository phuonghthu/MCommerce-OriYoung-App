package com.group6.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.group6.oriyoung.R;
import com.group6.oriyoung.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ImageSlider bannerSlider;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        bannerSlider = binding.bannerSlider;

        ArrayList<SlideModel> slideModels = new ArrayList<>(); //Create image list
        slideModels.add(new SlideModel(R.drawable.home_banner, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.home_banner, ScaleTypes.FIT));

        bannerSlider.setImageList(slideModels, ScaleTypes.FIT);

        return view;
    }
}