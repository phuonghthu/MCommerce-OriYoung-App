package com.group6.models;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
    int productID;
    String productName;
    double productPrice;
    double productDiscountPrice;
    String productDescription;
    String productImage;
    boolean isHot;
    boolean isFavorite;
    double ratingValue;
    int ratingCount;
    List<String> comments;

    public Product(int productID, String productName, double productPrice, double productDiscountPrice,
                   String productDescription, String productImage, boolean isHot, boolean isFavorite,
                   double ratingValue, int ratingCount, List<String> comments) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDiscountPrice = productDiscountPrice;
        this.productDescription = productDescription;
        this.productImage = productImage;
        this.isHot = isHot;
        this.isFavorite = isFavorite;
        this.ratingValue = ratingValue;
        this.ratingCount = ratingCount;
        this.comments = comments;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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

    public double getProductDiscountPrice() {
        return productDiscountPrice;
    }

    public void setProductDiscountPrice(double productDiscountPrice) {
        this.productDiscountPrice = productDiscountPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public double getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(double ratingValue) {
        this.ratingValue = ratingValue;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
