package com.mycompany.buat_apk.domains.enums;

public enum StockStatus {
    
    PEMBELIAN("Pembelian"),
    PENJUALAN("Penjualan");
    
    public final String detailsStatus;

    StockStatus(String status) {
        this.detailsStatus = status;
    }
}
