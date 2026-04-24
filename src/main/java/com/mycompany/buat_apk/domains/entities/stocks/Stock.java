package com.mycompany.buat_apk.domains.entities.stocks;

import java.sql.Date;

public class Stock {
    private Long id;
    private Long productId;
    private Long customerId = null;
    private Long userId;
    private int quantity;
    private Long price = null;
    private Date createdAt;

    public Stock(Long id, Long productId, Long customerId, int quantity, Long price, Date createdAt, Long userId) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.customerId = customerId;
        this.quantity = quantity;
        this.price = price;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
