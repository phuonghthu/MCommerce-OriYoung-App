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
    String imagePath;
    boolean isHot;
//    boolean isFavorite;
    double ratingValue;
    int ratingCount;
    List<Review> review;
    int numberInCart;

    public Product() {
        // Empty constructor required for Firebase database deserialization
    }

    public Product(int productID, int categoryID, String productName, double productPrice,
                   double productDiscountPercent, String productDescription, String imagePath,
                   boolean isHot, double ratingValue, int ratingCount, List<Review> review, int numberInCart) {
        this.productID = productID;
        this.categoryID = categoryID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDiscountPercent = productDiscountPercent;
        this.productDescription = productDescription;
        this.imagePath = imagePath;
        this.isHot = isHot;
        this.ratingValue = ratingValue;
        this.ratingCount = ratingCount;
        this.review = review;
        this.numberInCart = numberInCart;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
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

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}