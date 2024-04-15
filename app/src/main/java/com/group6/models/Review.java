package com.group6.models;

public class Review {
    private int reviewID;
    private int reviewCount;
    private String reviewerName;
    private double RatingValue;
    private String reviewDescription;
    private Product relatedProduct;

    public Review(int reviewID, String reviewerName, double ratingValue, String reviewDescription, Product relatedProduct) {
        this.reviewID = reviewID;
        this.reviewerName = reviewerName;
        RatingValue = ratingValue;
        this.reviewDescription = reviewDescription;
        this.relatedProduct = relatedProduct;
    }
    public Review(int reviewID) {
        this.reviewID = reviewID;
    }

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

    public double getRatingValue() {
        return RatingValue;
    }

    public void setRatingValue(double ratingValue) {
        RatingValue = ratingValue;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public Product getRelatedProduct() {
        return relatedProduct;
    }

    public void setRelatedProduct(Product relatedProduct) {
        this.relatedProduct = relatedProduct;
    }
    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }
}