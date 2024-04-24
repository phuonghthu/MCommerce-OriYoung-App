package com.group6.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.group6.models.Product;
import com.group6.oriyoung.R;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewholder>{

    Context context;
    ArrayList<Product> favProduct;

    public FavoriteAdapter(Context context, ArrayList<Product> favProduct) {
        this.context = context;
        this.favProduct = favProduct;
    }

    @NonNull
    @Override
    public FavoriteViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_favproduct, parent, false);
        return new FavoriteAdapter.FavoriteViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewholder holder, int position) {
        Product p = favProduct.get(position);

        Glide.with(context).load(p.getImagePath()).transform(new CenterCrop(),
                new RoundedCorners(30)).into(holder.imvProductThumb);
        holder.txtName.setText(p.getProductName());
        double originalPrice = p.getProductPrice();
        double discountPercent = p.getProductDiscountPercent();
        double discountedPrice = originalPrice * (1 - (discountPercent / 100.0));
        holder.txtPrice.setText(String.valueOf(Math.round(discountedPrice)) + " VNĐ");
        if (discountPercent == 0) {
            holder.txtDiscountPercent.setVisibility(View.GONE);
        } else {
            holder.txtDiscountPercent.setVisibility(View.VISIBLE);
            holder.txtDiscountPercent.setText("-" + String.valueOf(Math.round(p.getProductDiscountPercent())) + "%");
        }
    }

    @Override
    public int getItemCount() {
        return favProduct.size();
    }

    public class FavoriteViewholder extends RecyclerView.ViewHolder{
        ImageView imvProductThumb, imvAddToFav;
        TextView txtName, txtPrice, txtRatingValue, txtDiscountPercent;


        public FavoriteViewholder(@NonNull View itemView) {
            super(itemView);
            imvProductThumb= itemView.findViewById(R.id.imvProductThumb);
            txtName= itemView.findViewById(R.id.txtName);
            txtPrice= itemView.findViewById(R.id.txtPrice);
            txtRatingValue = itemView.findViewById(R.id.txtRatingValue);
            imvAddToFav = itemView.findViewById(R.id.imvAddToFav);
            txtDiscountPercent = itemView.findViewById(R.id.txtDiscountPercent);
        }
    }
}