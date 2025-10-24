package com.bity.bitykart.dto;

public class    OrderItemDto {

    private String productName;
    private double productprice;
    private int quantity;

    public OrderItemDto(String productName, double productprice, int quantity) {
        this.productName = productName;
        this.productprice = productprice;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductprice() {
        return productprice;
    }

    public void setProductprice(double productprice) {
        this.productprice = productprice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
