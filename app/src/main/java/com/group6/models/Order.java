package com.group6.models;

import java.util.List;

public class Order {
    private String orderID;
    private int customerID;
    private String orderStatus;
    private List<CheckoutCart> orderItems;
    private int itemQuantity;
    private double totalPayment;
}
