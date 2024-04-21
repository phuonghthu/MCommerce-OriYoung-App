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

import com.group6.models.Cart;
import com.group6.oriyoung.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    ArrayList<Cart> carts;
    CartAdapter adapter;

    public CartAdapter(Context context, ArrayList<Cart> carts) {
        this.context = context;
        this.carts = carts;
        this.adapter = adapter;
        this.totalTemp = totalTemp;
    }

    //    public CartAdapter(Context context, ArrayList<cartModel> carts) {
//        this.context = context;
//        this.carts = carts;
//    }
    private double totalTemp = 0.0;



    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imvProductCart.setImageResource(carts.get(position).getImvProductCart());
        holder.txtProductNameCart.setText(carts.get(position).getTxtProductNameCart());
        double price = carts.get(position).getTxtPriceCart();
        long roundedPrice = Math.round(price);
        holder.txtProductPriceCart.setText(String.valueOf(roundedPrice));
        holder.txtTemp.setText(String.valueOf(roundedPrice));

        //Dấu cộng
        holder.imvPlus.setTag(position);

        holder.imvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag(); // Lấy vị trí của item từ tag
                int currentCartNumb = Integer.parseInt(holder.txtCartNumb.getText().toString());
                currentCartNumb++; // Tăng giá trị của txtCartNumb
                holder.txtCartNumb.setText(String.valueOf(currentCartNumb));
                double productPrice = carts.get(position).getTxtPriceCart();
                int cartNumb = Integer.parseInt(holder.txtCartNumb.getText().toString());
                double tempPrice = productPrice * cartNumb;
                holder.txtTemp.setText(String.valueOf(tempPrice));

            }
        });

        //Dấu trừ
        holder.imvMinus.setTag(position);
        holder.imvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                int currentCartNumb = Integer.parseInt(holder.txtCartNumb.getText().toString());
                if(currentCartNumb >0){
                    currentCartNumb--;
                    holder.txtCartNumb.setText(String.valueOf(currentCartNumb));

                }
                if (currentCartNumb <= 0) {
                    // Xóa item tại vị trí position
                    carts.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, carts.size());
                }
                double productPrice = carts.get(position).getTxtPriceCart();
                int cartNumb = Integer.parseInt(holder.txtCartNumb.getText().toString());
                double tempPrice = productPrice * cartNumb;
                holder.txtTemp.setText(String.valueOf(tempPrice));

                //Test

            }
        });
//        double productPrice = carts.get(position).getTxtPriceCart();
//        int cartNumb = Integer.parseInt(holder.txtCartNumb.getText().toString());
//        double tempPrice = productPrice * cartNumb;
//        holder.txtTemp.setText(String.valueOf(tempPrice));
//
//        totalTemp += tempPrice;
//        holder.txtTempTotal.setText(String.valueOf(totalTemp));

    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvProductCart, imvPlus, imvMinus;
        TextView txtProductNameCart;
        TextView txtProductPriceCart;
        TextView txtTempTotal;
        TextView txtTemp;
        TextView txtCartNumb;



        @SuppressLint("WrongViewCast")
        public ViewHolder(View view) {
            super(view);
            imvProductCart = view.findViewById(R.id.imvCart);
            txtProductNameCart = view.findViewById(R.id.txtProductNameCart);
            txtProductPriceCart = view.findViewById(R.id.txtProductPriceCart);
            txtTemp = view.findViewById(R.id.txtTemp);
            imvPlus = view.findViewById(R.id.imvPlus);
            txtTempTotal = view.findViewById(R.id.txtTempTotal);
            imvMinus = view.findViewById(R.id.imvMinus);
            txtCartNumb = view.findViewById(R.id.txtCartNumb);


        }
    }
}
