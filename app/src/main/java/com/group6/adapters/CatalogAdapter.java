package com.group6.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group6.models.Product;
import com.group6.oriyoung.R;

import java.util.ArrayList;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>{
    Activity context;
    ArrayList<Product> catalog;

    public CatalogAdapter(Activity context, ArrayList<Product> catalog) {
        this.context = context;
        this.catalog = catalog;
    }

    @NonNull
    @Override
    public CatalogAdapter.CatalogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new CatalogAdapter.CatalogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogAdapter.CatalogViewHolder holder, int position) {
        holder.imvProductThumb.setImageResource(catalog.get(position).getProductImage());
        holder.txtName.setText(catalog.get(position).getProductName());
        holder.txtPrice.setText(String.valueOf(Math.round(catalog.get(position).getProductPrice())) + " VNĐ");

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CatalogViewHolder extends RecyclerView.ViewHolder {
        ImageView imvProductThumb;
        TextView txtName, txtPrice, txtRatingValue;
        public CatalogViewHolder(@NonNull View itemView) {
            super(itemView);
            imvProductThumb= itemView.findViewById(R.id.imvProductThumb);
            txtName= itemView.findViewById(R.id.txtName);
            txtPrice= itemView.findViewById(R.id.txtPrice);
            txtRatingValue = itemView.findViewById(R.id.txtRatingValue);
        }
    }
}
