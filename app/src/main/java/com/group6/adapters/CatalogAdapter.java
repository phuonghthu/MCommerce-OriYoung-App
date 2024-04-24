package com.group6.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group6.helpers.ManagementCart;
import com.group6.models.Product;
import com.group6.oriyoung.LoginActivity;
import com.group6.oriyoung.ProductDetail;
import com.group6.oriyoung.R;

import java.util.ArrayList;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>{
    Context context;
    ArrayList<Product> catalog;
    private Product object;
    private int num = 1;
    private ManagementCart managementCart;

    public CatalogAdapter(Context context, ArrayList<Product> catalog) {
        this.context = context;
        this.catalog = catalog;
        this.managementCart = new ManagementCart(context);
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
        Glide.with(holder.itemView.getContext()).load(catalog.get(position).getImagePath()).transform(new CenterCrop(),
                new RoundedCorners(30)).into(holder.imvProductThumb);
        holder.txtName.setText(catalog.get(position).getProductName());
        double originalPrice = catalog.get(position).getProductPrice();
        double discountPercent = catalog.get(position).getProductDiscountPercent();
        double discountedPrice = originalPrice * (1 - (discountPercent / 100.0));
        holder.txtPrice.setText(String.valueOf(Math.round(discountedPrice)) + " VNĐ");
        if (discountPercent == 0) {
            holder.txtDiscountPercent.setVisibility(View.GONE);
        } else {
            holder.txtDiscountPercent.setVisibility(View.VISIBLE);
            holder.txtDiscountPercent.setText("-" + String.valueOf(Math.round(catalog.get(position).getProductDiscountPercent())) + "%");
        }

        // Nhấn vào từng item gửi tt mở detail
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ProductDetail.class);
                intent.putExtra("object", catalog.get(position));
                // Set flag to indicate the calling activity
                // Thay đổi cờ trong Intent
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser != null) {
                    object = catalog.get(position); // Assign the correct Product object
                    object.setNumberInCart(num);
                    managementCart.insertProduct(object);
                } else {

                    showLoginDialog();

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return catalog.size();
    }

    public void addItems(ArrayList<Product> newProducts) {
        catalog.addAll(newProducts);
        notifyDataSetChanged();
    }
    // Add the updateData method to your RecyclerView adapter (CatalogAdapter)
    public void updateData(ArrayList<Product> newData) {
        catalog.clear();
        catalog.addAll(newData);
        notifyDataSetChanged();
    }

    public class CatalogViewHolder extends RecyclerView.ViewHolder {
        ImageView imvProductThumb, imvAddToFav;
        TextView txtName, txtPrice, txtRatingValue,  btnAddToCart, txtDiscountPercent;
        public CatalogViewHolder(@NonNull View itemView) {
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

    private void showLoginDialog() {
        LayoutInflater inflater = LayoutInflater.from(context.getApplicationContext());
        View dialogView = inflater.inflate(R.layout.custom_dialog_confirm, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);

        Button btndongy = dialogView.findViewById(R.id.btndongy);
        Button btnhuy = dialogView.findViewById(R.id.btnhuy);
        TextView txtTitle = dialogView.findViewById(R.id.txtTitle);
        TextView txtContent = dialogView.findViewById(R.id.txtContent);

        btndongy.setVisibility(View.GONE);
        btnhuy.setText("Đăng nhập");
        txtTitle.setText("Vui lòng đăng nhập!");
        txtContent.setText("Vui lòng đăng nhập để tiếp tục mua sắm và trải nghiệm tại OriYoung!");
        final AlertDialog dialog = builder.create();
        dialog.show();


        // Dẫn đến trang Login
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), LoginActivity.class);
                context.getApplicationContext().startActivity(intent);
            }
        });
    }
}
