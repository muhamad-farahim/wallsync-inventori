package com.mycompany.buat_apk.domains.entities.stocks;

public class ProductTransactionSummary {
    private final long productId;
    private final String productName;
    private final int currentStock;
    private final long moneyIn;
    private final long moneyOut;

    public ProductTransactionSummary(long productId, String productName, int currentStock, long moneyIn, long moneyOut) {
        this.productId = productId;
        this.productName = productName;
        this.currentStock = currentStock;
        this.moneyIn = moneyIn;
        this.moneyOut = moneyOut;
    }

    public long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public long getMoneyIn() {
        return moneyIn;
    }

    public long getMoneyOut() {
        return moneyOut;
    }

    public long getNet() {
        return moneyIn - moneyOut;
    }
}
