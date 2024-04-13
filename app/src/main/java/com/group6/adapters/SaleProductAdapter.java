package com.group6.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.group6.models.Product;
import com.group6.oriyoung.R;

import java.util.ArrayList;

public class SaleProductAdapter extends RecyclerView.Adapter<SaleProductAdapter.SaleProductViewHolder>{
    Context context;
    ArrayList<Product> products;
    private int itemWidth;

    public SaleProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public SaleProductAdapter.SaleProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_saleproduct, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams(); // Use 'view' instead of 'itemView'
        layoutParams.width = itemWidth;
        view.setLayoutParams(layoutParams);
        return new SaleProductAdapter.SaleProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleProductViewHolder holder, int position) {
        holder.imvProductThumb.setImageResource(products.get(position).getProductImage());
        holder.txtName.setText(products.get(position).getProductName());

        double originalPrice = products.get(position).getProductPrice();
        double discountPercent = products.get(position).getProductDiscountPercent();
        double discountedPrice = originalPrice * (1 - (discountPercent / 100.0));
        holder.txtPrice.setText(String.valueOf(Math.round(discountedPrice)) + " VNĐ");
        holder.txtDiscountPercent.setText("-" + String.valueOf(Math.round(products.get(position).getProductDiscountPercent())) + "%");
    }

    @Override
    public int getItemCount() { return
            products.size();
    }

    public void setItemWidth(int itemWidth) {
        this.itemWidth = itemWidth;
        notifyDataSetChanged();
    }

    public class SaleProductViewHolder extends RecyclerView.ViewHolder{
        ImageView imvProductThumb;
        TextView txtName, txtPrice, txtRatingValue, txtDiscountPercent;


        public SaleProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imvProductThumb= itemView.findViewById(R.id.imvProductThumb);
            txtName= itemView.findViewById(R.id.txtName);
            txtPrice= itemView.findViewById(R.id.txtPrice);
            txtRatingValue = itemView.findViewById(R.id.txtRatingValue);
            txtDiscountPercent = itemView.findViewById(R.id.txtDiscountPercent);
        }
    }
}
