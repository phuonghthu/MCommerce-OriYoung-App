package com.group6.models;

public class Category {
    int categoryID;
    int categoryThumb;
    String categoryName;

    public Category(int categoryThumb, String categoryName) {
        this.categoryThumb = categoryThumb;
        this.categoryName = categoryName;
        this.categoryID = categoryID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getCategoryThumb() {
        return categoryThumb;
    }

    public void setCategoryThumb(int categoryThumb) {
        this.categoryThumb = categoryThumb;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
