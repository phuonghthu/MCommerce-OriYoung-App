package com.group6.models;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
    int productID;
    int categoryID;
    String productName;
    double productPrice;
    double productDiscountPercent;
    String productDescription;
    int productImage;
    boolean isHot;
//    boolean isFavorite;
    double ratingValue;
    int ratingCount;
    List<Review> review;

    public Product(int productID, int categoryID, String productName, double productPrice,
                   double productDiscountPercent, String productDescription, int productImage,
                   boolean isHot, boolean isFavorite, double ratingValue, int ratingCount, List<Review> review) {
        this.productID = productID;
        this.categoryID = categoryID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDiscountPercent = productDiscountPercent;
        this.productDescription = productDescription;
        this.productImage = productImage;
        this.isHot = isHot;
//        this.isFavorite = isFavorite;
        this.ratingValue = ratingValue;
        this.ratingCount = ratingCount;
        this.review = review;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
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

    public double getProductDiscountPercent() {
        return productDiscountPercent;
    }

    public void setProductDiscountPercent(double productDiscountPercent) {
        this.productDiscountPercent = productDiscountPercent;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

//    public boolean isFavorite() {
//        return isFavorite;
//    }
//
//    public void setFavorite(boolean favorite) {
//        isFavorite = favorite;
//    }

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

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }
}