package com.group6.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.group6.models.Review;
import com.group6.oriyoung.R;

import java.util.ArrayList;

public class ReviewAdapter extends BaseAdapter {
    Context context;
    ArrayList<Review>reviews;
    int item_layout;

    public ReviewAdapter(Context context, int item_review_layout, ArrayList<Review> reviews) {
        this.context = context;
        this.reviews = reviews;
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
            convertView = inflater.inflate(item_layout, null);

            holder.imvRating = convertView.findViewById(R.id.imvRating);
            holder.txtUsername = convertView.findViewById(R.id.txtUsername);
            holder.txtReviewDetail = convertView.findViewById(R.id.txtReviewDetail);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        //Liên kết data
        Review v = reviews.get(position);
        holder.imvRating.setImageResource(v.getReviewCount());
        holder.txtUsername.setText(v.getReviewerName());
        holder.txtReviewDetail.setText(v.getReviewDescription());
        return convertView;
    }

    public static class ViewHolder{
        ImageView imvRating;
        TextView txtUsername, txtReviewDetail;
    }
}
