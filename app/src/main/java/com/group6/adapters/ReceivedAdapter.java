package com.group6.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group6.models.CheckoutCart;
import com.group6.models.OrderHistory;
import com.group6.oriyoung.R;
import com.theophrast.ui.widget.SquareImageView;

import java.util.ArrayList;
import java.util.List;

public class ReceivedAdapter extends RecyclerView.Adapter<ReceivedAdapter.ViewHolder>{

    private Context context;
    private ArrayList<OrderHistory> orderHistory;
    private ArrayList<CheckoutCart> productitem;

    public ReceivedAdapter(Context context, ArrayList<OrderHistory> orderHistory, ArrayList<CheckoutCart> productitem) {
        this.context = context;
        this.orderHistory = orderHistory;
        this.productitem = productitem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_orderhistory, parent, false);
        return new ReceivedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CheckoutCart orderItems = productitem.get(position);
        holder.imvCart.setImageResource(orderItems.getProductThumb());
        holder.txtproductNameCart.setText(orderItems.getProductName());
        holder.txtproductPriceCart.setText(String.valueOf(orderItems.getProductPrice()));
        holder.txtproductQuantityCart.setText(String.valueOf(orderItems.getProductQuantity()));
        holder.txtproductAmountCart.setText(String.valueOf(orderItems.getProductAmount()));


        OrderHistory order = orderHistory.get(position);

        holder.txtOrderStatus.setText(order.getOrderStatus());
        holder.txtOrderID.setText("#"+ order.getOrderID());
        holder.txtItemQty.setText((String.valueOf(order.getItemQuantity()) + " sản phẩm"));
        holder.txtTotalAmount.setText(String.valueOf(order.getTotalPayment()));
        holder.btnLeft.setText("Đặt lại");
        holder.btnRight.setText("Đánh giá");

    }

    @Override
    public int getItemCount() { return orderHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SquareImageView imvCart;
        TextView txtproductNameCart, txtproductPriceCart, txtproductQuantityCart, txtproductAmountCart,
                txtOrderStatus, txtOrderID, txtItemQty, txtTotalAmount;
        Button btnLeft, btnRight;
        LinearLayout layoutCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutCart = itemView.findViewById(R.id.layoutCart);
            imvCart = itemView.findViewById(R.id.imvCart);
            txtproductNameCart = itemView.findViewById(R.id.txtProductNameCart);
            txtproductPriceCart = itemView.findViewById(R.id.txtProductPriceCart);
            txtproductQuantityCart = itemView.findViewById(R.id.txtProductQuantityCart);
            txtproductAmountCart = itemView.findViewById(R.id.txtProductAmountCart);

            txtOrderStatus = itemView.findViewById(R.id.txtOrderStatus);
            txtOrderID = itemView.findViewById(R.id.txtOrderID);
            txtItemQty = itemView.findViewById(R.id.txtItemQty);
            txtTotalAmount = itemView.findViewById(R.id.txtTotalAmount);
            btnLeft = itemView.findViewById(R.id.btnLeft);
            btnRight = itemView.findViewById(R.id.btnRight);

        }
    }
}
