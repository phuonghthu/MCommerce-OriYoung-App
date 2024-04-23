package com.group6.models;

import java.util.List;

public class Order {
    private String orderID;
    private int userID;
    private String orderStatus;
    private String orderTime;
    private List<Cart> orderItems;
    private int totalQuantity;
    private double totalPayment;

    public Order(String orderID, int userID, String orderStatus, String orderTime, List<Cart> orderItems, int totalQuantity, double totalPayment) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderStatus = orderStatus;
        this.orderTime = orderTime;
        this.orderItems = orderItems;
        this.totalQuantity = totalQuantity;
        this.totalPayment = totalPayment;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public List<Cart> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Cart> orderItems) {
        this.orderItems = orderItems;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }
}
