package com.dewen.entity;

import lombok.Data;

@Data
public class Product {

    private String productName;

    private String productCode;

    private float price;

    public Product(String productName, String productCode, float price) {
        this.productName = productName;
        this.productCode = productCode;
        this.price = price;
    }
}