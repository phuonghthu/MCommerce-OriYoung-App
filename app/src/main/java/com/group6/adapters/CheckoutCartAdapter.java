package com.group6.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group6.models.CheckoutCart;
import com.group6.oriyoung.Checkout;
import com.group6.oriyoung.R;

import java.util.ArrayList;

public class CheckoutCartAdapter extends RecyclerView.Adapter<CheckoutCartAdapter.ViewHolder> {
    private Context mcontext;
    private ArrayList<CheckoutCart> mCartList;

    public CheckoutCartAdapter(Context context, ArrayList<CheckoutCart> cartList) {
        mcontext = context;
        mCartList = cartList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_checkout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CheckoutCart cartItem = mCartList.get(position);
        holder.imvproductThumb.setImageResource(cartItem.getProductThumb());
        holder.txtproductName.setText(cartItem.getProductName());
        holder.txtproductPrice.setText(String.valueOf(cartItem.getProductPrice()));
        holder.txtproductQuantity.setText(String.valueOf(cartItem.getProductQuantity()));
        holder.txtproductAmount.setText(String.valueOf(cartItem.getProductAmount()));
    }

    @Override
    public int getItemCount() {
        return mCartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvproductThumb;
        TextView txtproductName, txtproductPrice, txtproductQuantity, txtproductAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvproductThumb = itemView.findViewById(R.id.imvCart);
            txtproductName = itemView.findViewById(R.id.txtProductNameCart);
            txtproductPrice = itemView.findViewById(R.id.txtProductPriceCart);
            txtproductQuantity = itemView.findViewById(R.id.txtProductQuantityCart);
            txtproductAmount = itemView.findViewById(R.id.txtProductAmountCart);
        }
    }
}