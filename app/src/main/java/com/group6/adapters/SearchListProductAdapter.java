package com.group6.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
import java.util.Iterator;
import java.util.List;

public class SearchListProductAdapter extends RecyclerView.Adapter<SearchListProductAdapter.ProductViewHolder> {
    Context context;
    ArrayList<Product> products;


    public SearchListProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_search_list, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Glide.with(context).load(products.get(position).getImagePath()).transform(new CenterCrop(),
                new RoundedCorners(30)).into(holder.imvList);

        holder.txtProductNameList.setText(products.get(position).getProductName());


        holder.txtProductPriceList.setText(String.valueOf(Math.round(products.get(position).getProductPrice())) + " VNĐ");



        // Nhấn vào từng item gửi tt mở detail
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetail.class);
                intent.putExtra("object", products.get(position));
                // Thêm cờ vào Intent
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() { return
            products.size();
    }

    public void searchProduct(ArrayList<Product> searchList){
        products = searchList;
        notifyDataSetChanged();
    }



    public class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView imvList;
        TextView txtProductNameList, txtProductPriceList;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imvList= itemView.findViewById(R.id.imvList);
            txtProductNameList= itemView.findViewById(R.id.txtProductNameList);
            txtProductPriceList= itemView.findViewById(R.id.txtProductPriceList);


        }
    }

}






