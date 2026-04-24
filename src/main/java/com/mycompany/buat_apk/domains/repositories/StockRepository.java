package com.mycompany.buat_apk.domains.repositories;

import java.sql.SQLException;

import com.mycompany.buat_apk.domains.entities.stocks.CreateStock;

public interface StockRepository {
    public CreateStock createStocks(CreateStock s) throws SQLException;
    public int getProductStocks(int product_id) throws SQLException;
}
