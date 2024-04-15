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
        viewHolder.productImage.setImageResource(product.getProductImage());

        return convertView;
    }

    static class ViewHolder {
        TextView productName;
        TextView productPrice;
        ImageView productImage;
    }

    public void filter(String query) {
        query = query.toLowerCase().trim(); // Chuyển đổi văn bản tìm kiếm thành chữ thường và loại bỏ khoảng trắng đầu cuối
        filteredList.clear(); // Xóa danh sách sản phẩm lọc

        if (query.isEmpty()) {
            // Nếu văn bản tìm kiếm rỗng, hiển thị toàn bộ danh sách sản phẩm
            filteredList.addAll(productList);
        } else {
            // Nếu không, lọc danh sách sản phẩm dựa trên văn bản tìm kiếm
            for (Product product : productList) {
                if (product.getProductName().toLowerCase().contains(query)) {
                    filteredList.add(product);
                }
            }
        }

        // Xóa danh sách hiện tại
        productList.clear();
        // Thêm danh sách sản phẩm lọc vào danh sách hiện tại
        productList.addAll(filteredList);
        // Cập nhật lại ListView
        notifyDataSetChanged();
    }
}
