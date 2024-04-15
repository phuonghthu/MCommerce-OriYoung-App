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

        ArrayList<Product> matchedProducts = new ArrayList<>(); // Danh sách lưu trữ các sản phẩm trùng khớp

        if (!query.isEmpty()) {
            // Duyệt qua danh sách sản phẩm để tìm kiếm trùng khớp
            for (Iterator<Product> iterator = filteredList.iterator(); iterator.hasNext(); ) {
                Product product = iterator.next();
                if (product.getProductName().toLowerCase().startsWith(query)) {
                    matchedProducts.add(product); // Thêm sản phẩm trùng khớp vào danh sách mới
                    iterator.remove(); // Loại bỏ sản phẩm trùng khớp khỏi danh sách gốc
                }
            }
        }

        // Thêm các sản phẩm trùng khớp vào đầu danh sách gốc
        productList.addAll(0, matchedProducts);

        // Cập nhật lại ListView
        notifyDataSetChanged();
    }


}
