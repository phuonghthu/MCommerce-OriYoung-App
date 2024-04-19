package com.group6.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.group6.models.Category;
import com.group6.oriyoung.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    Context context;
    ArrayList<Category> categories;

    public MenuAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        String imagePath = categories.get(position).getImagePath();
        if (imagePath != null) {
            int drawableResoucreID = context.getResources().getIdentifier(imagePath,
                    "drawable", holder.itemView.getContext().getPackageName());
            Glide.with(context).load(drawableResoucreID).into(holder.imvCateThumb);
        }
        holder.txtCateName.setText(categories.get(position).getCategoryName());

    }

    @Override
    public int getItemCount() {return categories.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        // Khai báo

        CircleImageView imvCateThumb;
        TextView txtCateName;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            // ánh xạ
            imvCateThumb = itemView.findViewById(R.id.imvCateThumb);
            txtCateName = itemView.findViewById(R.id.txtCateName);

        }
    }
}
