package com.group6.models;

public class CheckoutCart {
    private String productName;
    private double productPrice;
    private int productThumb;
    private int productQuantity;
    private double productAmount;
    public CheckoutCart(String productName, double productPrice, int productThumb, int productQuantity){
        this.productName = productName;
        this.productPrice = productPrice;
        this.productThumb = productThumb;
        this.productQuantity = productQuantity;
        this.productAmount = productPrice * productQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductThumb() {
        return productThumb;
    }

    public void setProductThumb(int productThumb) {
        this.productThumb = productThumb;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
        this.productAmount = productPrice * productQuantity;
    }

    public double getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(double productAmount) {
        this.productAmount = productAmount;
    }
}
