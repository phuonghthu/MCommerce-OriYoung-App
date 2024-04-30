package com.group6.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderID;
    private String userID;
    private String orderStatus;
    private String paymentMethod;
    private String orderDate;
    private String shippingFee;
    private String address;
    private List<Cart> orderItems;
    private int totalQuantity;
    private double totalAmount;



    public Order(String orderID, String userID, String orderStatus, String paymentMethod,
                 String orderDate, String shippingFee, String address, List<Cart> orderItems, int totalQuantity, double totalAmount) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
        this.orderDate = orderDate;
        this.shippingFee = shippingFee;
        this.address = address;
        this.orderItems = orderItems;
        this.totalQuantity = totalQuantity;
        this.totalAmount = totalAmount;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(String shippingFee) {
        this.shippingFee = shippingFee;
    }

    public String getAddress() {return address;}

    public void setAddress(String address) {
        this.address = address;}

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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

}
