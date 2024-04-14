package com.group6.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.group6.models.Category;
import com.group6.oriyoung.R;

import java.util.ArrayList;

public class CategoryNameAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Category> categories;

    public CategoryNameAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.category_name_button, parent, false);
        }

        Button btnItem = convertView.findViewById(R.id.btnCategory);
        btnItem.setText(categories.get(position).getCategoryName());

        return convertView;
    }
}
