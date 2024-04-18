package com.group6.models;

import com.group6.oriyoung.Checkout;

import java.util.List;

public class OrderHistory {
    private String orderID;
    private String orderStatus;
    private List<CheckoutCart> orderItems;
    private int itemQuantity;
    private double totalPayment;

    public OrderHistory(String orderID, String orderStatus, List<CheckoutCart> orderItems, int itemQuantity, double totalPayment) {
        this.orderID = orderID;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
        this.itemQuantity = itemQuantity;
        this.totalPayment = totalPayment;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<CheckoutCart> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<CheckoutCart> orderItems) {
        this.orderItems = orderItems;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }
}
