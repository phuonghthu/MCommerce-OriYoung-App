package com.group6.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.group6.models.Review;
import com.group6.oriyoung.ProductDetail;
import com.group6.oriyoung.R;
import com.group6.oriyoung.ReviewDetail;

import java.util.ArrayList;

public class ReviewAdapter extends BaseAdapter {
    Context context;
    ArrayList<Review>reviews;
    ;
    int item_review_layout;


    public ReviewAdapter(Context context, int item_review_layout, ArrayList<Review> reviews) {
        this.context = context;
        this.reviews = reviews;
        this.item_review_layout = item_review_layout;

    }

    @Override
    public int getCount() {
        return reviews.size();
    }


    @Override
    public Object getItem(int position) {
        return reviews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){

            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_review_layout, null);

            holder.txtIndividualRatingValue = convertView.findViewById(R.id.txtIndividualRatingValue);
            holder.txtUsername = convertView.findViewById(R.id.txtUsername);
            holder.txtReviewDetail = convertView.findViewById(R.id.txtReviewDetail);
            holder.imvProductThumb = convertView.findViewById(R.id.imvProductThumb);
            holder.txtName = convertView.findViewById(R.id.txtName);
            holder.txtPrice = convertView.findViewById(R.id.txtPrice);
            holder.txtRatingValue = convertView.findViewById(R.id.txtRatingValue);


            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        //Liên kết data
        Review v = reviews.get(position);
        holder.txtIndividualRatingValue.setText(String.valueOf(v.getIndividualRatingValue()));
        holder.txtUsername.setText(v.getReviewerName());
        holder.txtReviewDetail.setText(v.getReviewDescription());
        holder.imvProductThumb.setImageResource(v.getProductImage());
        holder.txtName.setText(v.getProductName());
        holder.txtPrice.setText(String.valueOf(v.getProductPrice()));
        holder.txtRatingValue.setText(String.valueOf(v.getRatingValue()));
        return convertView;
    }

    public static class ViewHolder{
        ImageView imvProductThumb;
        TextView txtUsername, txtReviewDetail, txtIndividualRatingValue, txtName, txtPrice, txtRatingValue;
    }
}
