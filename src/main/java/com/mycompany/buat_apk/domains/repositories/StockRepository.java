package com.mycompany.buat_apk.domains.repositories;

import com.mycompany.buat_apk.domains.entities.stocks.Stocks;

public interface StockRepository {
    public Stocks createStocks(Stocks s);
    public int getProductStocks(int product_id);
}
