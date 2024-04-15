package com.group6.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.group6.models.Product;
import com.group6.oriyoung.R;
import java.util.ArrayList;

public class SearchListProductAdapter extends ArrayAdapter<Product> {

    private ArrayList<Product> productList;
    private LayoutInflater inflater;

    public SearchListProductAdapter(Context context, ArrayList<Product> productList) {
        super(context, 0, productList);
        this.productList = productList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_search_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.productName = convertView.findViewById(R.id.txtProductNameList);
            viewHolder.productPrice = convertView.findViewById(R.id.txtProductPriceList);
            viewHolder.productImage = convertView.findViewById(R.id.imvList);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Product product = productList.get(position);
        viewHolder.productName.setText(product.getProductName());
        viewHolder.productPrice.setText(String.valueOf(product.getProductPrice()));
        viewHolder.productImage.setImageResource(product.getProductImage());

        return convertView;
    }

    static class ViewHolder {
        TextView productName;
        TextView productPrice;
        ImageView productImage;
    }
}
