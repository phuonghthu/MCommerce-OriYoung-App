package com.group6.adapters;

import android.annotation.SuppressLint;
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
import com.group6.helpers.ChangeNumberItemsListener;
import com.group6.helpers.ManagementCart;
import com.group6.models.Cart;
import com.group6.models.Product;
import com.group6.oriyoung.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    ArrayList<Product> carts;
    private ManagementCart managementCart;
    ChangeNumberItemsListener changeNumberItemsListener;


    public CartAdapter(ArrayList<Product> carts, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.carts = carts;
        managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }


    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product cart = carts.get(position);
        holder.txtProductName.setText(cart.getProductName());
        holder.txtProductPrice.setText(String.valueOf(Math.round(cart.getProductPrice())) + " VNĐ");
        holder.txtItemTotal.setText(String.valueOf(Math.round(cart.getNumberInCart()
                *cart.getProductPrice())) + " VNĐ");
        holder.txtItemQuantity.setText(cart.getNumberInCart() + "");

        Glide.with(holder.itemView.getContext()).load(cart.getImagePath()).transform(new CenterCrop()).into(holder.imvProduct);


        holder.imvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.plusNumberItem(carts, position, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();
                    }
                });

            }
        });

        holder.imvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.minusNumberItem(carts, position, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvProduct, imvPlus, imvMinus;
        TextView txtProductName, txtProductPrice, txtItemQuantity, txtItemTotal;

        @SuppressLint("WrongViewCast")
        public ViewHolder(View view) {
            super(view);
            imvProduct = view.findViewById(R.id.imvProduct);
            txtProductName = view.findViewById(R.id.txtProductName);
            txtProductPrice = view.findViewById(R.id.txtProductPrice);
            txtItemQuantity = view.findViewById(R.id.txtItemQuantity);
            imvPlus = view.findViewById(R.id.imvPlus);
            txtItemTotal = view.findViewById(R.id.txtItemTotal);
            imvMinus = view.findViewById(R.id.imvMinus);


        }
    }
}
