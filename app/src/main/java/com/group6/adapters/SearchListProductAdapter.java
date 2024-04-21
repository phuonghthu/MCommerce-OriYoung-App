package com.group6.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.group6.models.Product;
import com.group6.oriyoung.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SearchListProductAdapter extends ArrayAdapter<Product> {

    private ArrayList<Product> productList;
    private ArrayList<Product> filteredList;
    private LayoutInflater inflater;

    public SearchListProductAdapter(Context context, ArrayList<Product> productList) {
        super(context, 0, productList);
        this.productList = productList;
        this.filteredList = new ArrayList<>(productList);
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
        Glide.with(convertView).load(product.getImagePath()).transform(new CenterCrop(),
                new RoundedCorners(30)).into(viewHolder.productImage);
        return convertView;
    }

    static class ViewHolder {
        TextView productName;
        TextView productPrice;
        ImageView productImage;
    }

    public void filter(String query) {
        query = query.toLowerCase().trim();

        ArrayList<Product> matchedProducts = new ArrayList<>(); // Danh sách lưu trữ các sản phẩm trùng khớp
        ArrayList<Product> unmatchedProducts = new ArrayList<>(); // Danh sách lưu trữ các sản phẩm không trùng khớp

        if (!query.isEmpty()) {
            // Duyệt qua danh sách sản phẩm để tìm kiếm trùng khớp
            for (Product product : productList) {
                if (product.getProductName().toLowerCase().startsWith(query)) {
                    matchedProducts.add(product); // Thêm sản phẩm trùng khớp vào danh sách mới
                } else {
                    unmatchedProducts.add(product); // Thêm sản phẩm không trùng khớp vào danh sách mới
                }
            }
        } else {
            matchedProducts.addAll(productList); // Nếu query rỗng, thì danh sách không trùng khớp sẽ giống với danh sách gốc
        }

        // Đưa các sản phẩm trùng khớp lên đầu danh sách
        productList.clear();
        productList.addAll(matchedProducts);
        productList.addAll(unmatchedProducts);

        // Cập nhật lại RecyclerView
        notifyDataSetChanged();
    }





}
