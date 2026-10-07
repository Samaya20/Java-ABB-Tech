package com.example.orderingtask.dto;

import java.math.BigDecimal;

public class OrderItemRequest {

    private String productName;
    private BigDecimal unitPrice;
    private Integer quantity;

    public OrderItemRequest() {
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
