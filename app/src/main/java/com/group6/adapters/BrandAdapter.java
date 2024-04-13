package com.group6.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group6.models.Brand;
import com.group6.models.Category;
import com.group6.oriyoung.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.BrandViewHolder> {

    Context context;
    ArrayList<Brand> brands;

    public BrandAdapter(Context context, ArrayList<Brand> brands) {
        this.context = context;
        this.brands = brands;
    }

    @NonNull
    @Override
    public BrandAdapter.BrandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_brand, parent, false);
        return new BrandAdapter.BrandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandViewHolder holder, int position) {
        holder.imvBrandImage.setImageResource(brands.get(position).getBrandImage());
    }


    @Override
    public int getItemCount() {return brands.size();
    }

    public class BrandViewHolder extends RecyclerView.ViewHolder {

        // Khai báo

        CircleImageView imvBrandImage;

        public BrandViewHolder(@NonNull View itemView) {
            super(itemView);
            // ánh xạ
            imvBrandImage = itemView.findViewById(R.id.imvBrandImage);

        }
    }
}
