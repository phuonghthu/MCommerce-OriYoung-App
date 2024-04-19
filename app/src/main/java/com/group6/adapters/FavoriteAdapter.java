package com.group6.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group6.models.Product;
import com.group6.oriyoung.R;

import java.lang.reflect.Array;
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
        View view = inflater.inflate(R.layout.item_saleproduct, parent, false);
        return new FavoriteAdapter.FavoriteViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewholder holder, int position) {
        Product p = favProduct.get(position);

        holder.imvProductThumb.setImageResource(p.getProductImage());
        holder.txtName.setText(p.getProductName());
        holder.txtPrice.setText(String.valueOf(Math.round(p.getProductPrice())) + " VNĐ");
        holder.txtDiscountPercent.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return favProduct.size();
    }

    public class FavoriteViewholder extends RecyclerView.ViewHolder{
        ImageView imvProductThumb, imvAddToFav;
        TextView txtName, txtPrice, txtRatingValue, btnAddToCart, txtDiscountPercent;


        public FavoriteViewholder(@NonNull View itemView) {
            super(itemView);
            imvProductThumb= itemView.findViewById(R.id.imvProductThumb);
            txtName= itemView.findViewById(R.id.txtName);
            txtPrice= itemView.findViewById(R.id.txtPrice);
            txtRatingValue = itemView.findViewById(R.id.txtRatingValue);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
            imvAddToFav = itemView.findViewById(R.id.imvAddToFav);
            txtDiscountPercent = itemView.findViewById(R.id.txtDiscountPercent);
        }
    }
}