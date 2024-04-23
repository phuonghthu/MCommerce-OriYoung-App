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
import com.group6.helpers.ManagementCart;
import com.group6.models.CheckoutCart;
import com.group6.models.Product;
import com.group6.oriyoung.Checkout;
import com.group6.oriyoung.R;

import java.util.ArrayList;

public class CheckoutCartAdapter extends RecyclerView.Adapter<CheckoutCartAdapter.ViewHolder> {
    private Context mcontext;
    ArrayList<Product> cartItem;

    public CheckoutCartAdapter(Context mcontext, ManagementCart managementCart) {
        this.mcontext = mcontext;
        cartItem = managementCart.getListCart();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_checkout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product c = cartItem.get(position);
        holder.txtProductName.setText(c.getProductName());
        holder.txtProductPrice.setText(String.valueOf(Math.round(c.getProductPrice())) + " VNĐ");
        holder.txtItemTotal.setText(String.valueOf(Math.round(c.getNumberInCart()
                *c.getProductPrice())) + " VNĐ");
        holder.txtItemQuantity.setText(c.getNumberInCart() + "");

        Glide.with(holder.itemView.getContext()).load(c.getImagePath()).transform(new CenterCrop()).into(holder.imvProductThumb);
    }

    @Override
    public int getItemCount() {
        return cartItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvProductThumb;
        TextView txtProductName, txtProductPrice, txtItemQuantity, txtItemTotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvProductThumb = itemView.findViewById(R.id.imvProductThumb);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtProductPrice = itemView.findViewById(R.id.txtProductPrice);
            txtItemQuantity = itemView.findViewById(R.id.txtItemQuantity);
            txtItemTotal = itemView.findViewById(R.id.txtItemTotal);
        }
    }
}