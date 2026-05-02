
package com.mycompany.buat_apk.domains.entities.stocks;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import com.mycompany.buat_apk.domains.enums.StockStatus;

public class StockDetailItem {
    private Long id;
    private Long productId;
    private Long customerId = null;
    private Long userId;
    private int quantity;
    private Long price = null;
    private Date createdAt;
    private String description;
    private List<StockDetailItem> transactions;

    public StockDetailItem(
            Long id, Long productId, Long customerId, int quantity, Long price, Date createdAt, Long userId
            ) {
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

    public List<StockDetailItem> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(List<StockDetailItem> list) {
        this.transactions = list;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public StockStatus getStatus() {
        return this.quantity < 0 ? StockStatus.PENJUALAN : StockStatus.PEMBELIAN;
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

    public String getRpPrice() {
        String priceString = String.valueOf(this.price);
        return "Rp. " + String.format(Locale.of("id", "ID"), "%,d", priceString);
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


    public String getFormattedCreatedDate() {

        if (this.createdAt == null) return "-";

        SimpleDateFormat formatter = new SimpleDateFormat("d MMMM yyyy", Locale.of("id", "ID"));

        return formatter.format(this.createdAt);

    }
}
