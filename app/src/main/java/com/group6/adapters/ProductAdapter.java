package com.group6.adapters;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.group6.models.Product;
import com.group6.oriyoung.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    Context context;
    ArrayList<Product> products;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_saleproduct, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.imvProductThumb.setImageResource(products.get(position).getProductImage());
        holder.txtName.setText(products.get(position).getProductName());

        double originalPrice = products.get(position).getProductPrice();
        double discountPercent = products.get(position).getProductDiscountPercent();
        double discountedPrice = originalPrice * (1 - (discountPercent / 100.0));
        holder.txtPrice.setText(String.valueOf(Math.round(discountedPrice)) + " VNĐ");
        if (discountPercent == 0) {
            holder.txtDiscountPercent.setVisibility(View.GONE);
        } else {
            holder.txtDiscountPercent.setVisibility(View.VISIBLE);
            holder.txtDiscountPercent.setText("-" + String.valueOf(Math.round(products.get(position).getProductDiscountPercent())) + "%");
        }

    }

    @Override
    public int getItemCount() { return
        products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView imvProductThumb, imvAddToFav;
        TextView txtName, txtPrice, txtRatingValue, btnAddToCart, txtDiscountPercent;


        public ProductViewHolder(@NonNull View itemView) {
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
