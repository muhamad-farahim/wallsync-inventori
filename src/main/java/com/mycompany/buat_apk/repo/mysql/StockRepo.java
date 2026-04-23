package com.mycompany.buat_apk.repo.mysql;

import java.sql.Connection;

import com.mycompany.buat_apk.domains.entities.stocks.Stocks;
import com.mycompany.buat_apk.domains.repositories.StockRepository;

public class StockRepo implements StockRepository {
    private final Connection conn;

	public StockRepo(final Connection conn) {
		this.conn = conn;
	}

	@Override
	public Stocks createStocks(Stocks s) {


		return null;
	}

	@Override
	public int getProductStocks(int product_id) {
		return 0;
	}

    
}
