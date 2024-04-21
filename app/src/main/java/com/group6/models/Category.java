package com.group6.models;

public class Category {
    int categoryID;
    String imagePath;
    String categoryName;

    public Category() {

    };

    public Category(int categoryID, String imagePath, String categoryName) {
        this.categoryID = categoryID;
        this.imagePath = imagePath;
        this.categoryName = categoryName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}