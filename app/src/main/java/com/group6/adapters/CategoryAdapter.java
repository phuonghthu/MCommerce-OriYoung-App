package com.group6.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group6.models.Category;
import com.group6.oriyoung.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    Context context;
    ArrayList<Category> categories;

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.imvCateThumb.setImageResource(categories.get(position).getCategoryThumb());
        holder.txtCateName.setText(categories.get(position).getCategoryName());


    }

    @Override
    public int getItemCount() {return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        // Khai báo

        CircleImageView imvCateThumb;
        TextView txtCateName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            // ánh xạ
            imvCateThumb = itemView.findViewById(R.id.imvCateThumb);
            txtCateName = itemView.findViewById(R.id.txtCateName);

        }
    }
}
