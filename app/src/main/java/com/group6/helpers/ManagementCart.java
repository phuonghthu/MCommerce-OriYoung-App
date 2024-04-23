package com.group6.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.group6.models.Product;
import com.group6.oriyoung.R;

import java.util.ArrayList;


public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;
    private static final String KEY_ITEM_TOTAL = "itemTotal";
    private static final String KEY_ITEM_QUANTITY = "itemQuantity";


    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB=new TinyDB(context);

    }

    public void insertProduct(Product item) {
        ArrayList<Product> listpop = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listpop.size(); i++) {
            if (listpop.get(i).getProductName().equals(item.getProductName())) {
                existAlready = true;
                n = i;
                break;
            }
        }
        if(existAlready){
            listpop.get(n).setNumberInCart(item.getNumberInCart());
        }else{
            listpop.add(item);
        }
        tinyDB.putListObject("CartList",listpop);
        showCustomToast();


    }

    private void showCustomToast() {
        // Inflate custom layout for the Toast
        LayoutInflater inflater = LayoutInflater.from(context);
        View toastView = inflater.inflate(R.layout.custom_toast, null);

        // Customize the content of the Toast
        TextView txtTitle = toastView.findViewById(R.id.txtTitle);
        txtTitle.setText("Thêm vào giỏ hàng thành công");

        // Create and show the custom Toast
        Toast customToast = new Toast(context);
        customToast.setView(toastView);
        customToast.setDuration(Toast.LENGTH_SHORT);
        customToast.show();
    }

    public ArrayList<Product> getListCart() {
        return tinyDB.getListObject("CartList");
    }

    public Double getTotalFee(){
        ArrayList<Product> listItem=getListCart();
        double fee=0;
        for (int i = 0; i < listItem.size(); i++) {
            fee=fee+(listItem.get(i).getProductPrice()*listItem.get(i).getNumberInCart());
        }
        return fee;
    }
    public void minusNumberItem(ArrayList<Product> listItem,int position,ChangeNumberItemsListener changeNumberItemsListener){
        if(listItem.get(position).getNumberInCart()==1){
            listItem.remove(position);
        }else{
            listItem.get(position).setNumberInCart(listItem.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("CartList",listItem);
        changeNumberItemsListener.change();
    }
    public  void plusNumberItem(ArrayList<Product> listItem,int position,ChangeNumberItemsListener changeNumberItemsListener){
        listItem.get(position).setNumberInCart(listItem.get(position).getNumberInCart()+1);
        tinyDB.putListObject("CartList",listItem);
        changeNumberItemsListener.change();
    }
}