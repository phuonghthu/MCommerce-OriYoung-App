package com.group6.models;

public class cartModel {
    int imvProductCart;
    String txtProductNameCart;
    double txtPriceCart;
    double txtTemp;

    public cartModel(int imvProductCart, String txtProductNameCart, double txtPriceCart, double txtTemp) {
        this.imvProductCart = imvProductCart;
        this.txtProductNameCart = txtProductNameCart;
        this.txtPriceCart = txtPriceCart;
        this.txtTemp = txtTemp;
    }

    public int getImvProductCart() {
        return imvProductCart;
    }

    public void setImvProductCart(int imvProductCart) {
        this.imvProductCart = imvProductCart;
    }

    public String getTxtProductNameCart() {
        return txtProductNameCart;
    }

    public void setTxtProductNameCart(String txtProductNameCart) {
        this.txtProductNameCart = txtProductNameCart;
    }

    public double getTxtPriceCart() {
        return txtPriceCart;
    }

    public void setTxtPriceCart(double txtPriceCart) {
        this.txtPriceCart = txtPriceCart;
    }

    public double getTxtTemp() {
        return txtTemp;
    }

    public void setTxtTemp(double txtTemp) {
        this.txtTemp = txtTemp;
    }
}
