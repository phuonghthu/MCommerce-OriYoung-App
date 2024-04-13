package com.group6.models;

public class Brand {
    int brandImage;
    String brandName;

    public Brand(int brandImage, String brandName) {
        this.brandImage = brandImage;
        this.brandName = brandName;
    }

    public int getBrandImage() {
        return brandImage;
    }

    public void setBrandImage(int brandImage) {
        this.brandImage = brandImage;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
