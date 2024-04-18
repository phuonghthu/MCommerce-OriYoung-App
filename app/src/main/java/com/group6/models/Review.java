package com.group6.models;

public class Review {
    private int reviewID;
    private String reviewerName;
    private double IndividualRatingValue;
    private String reviewDescription;
    int productID;
    int categoryID;
    String productName;
    double productPrice;
    int productImage;
    boolean isHot;
    boolean isFavorite;
    double ratingValue;

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public double getIndividualRatingValue() {
        return IndividualRatingValue;
    }

    public void setIndividualRatingValue(double individualRatingValue) {
        IndividualRatingValue = individualRatingValue;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
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

    public Review(int reviewID, String reviewerName, double individualRatingValue, String reviewDescription, int productID, int categoryID, String productName, double productPrice, int productImage, boolean isHot, boolean isFavorite, double ratingValue) {
        this.reviewID = reviewID;
        this.reviewerName = reviewerName;
        IndividualRatingValue = individualRatingValue;
        this.reviewDescription = reviewDescription;
        this.productID = productID;
        this.categoryID = categoryID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.isHot = isHot;
        this.isFavorite = isFavorite;
        this.ratingValue = ratingValue;
    }





   }