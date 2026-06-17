package com.mycompany.buat_apk.domains.repositories;

import java.sql.SQLException;
import java.time.YearMonth;
import java.util.*;
import com.mycompany.buat_apk.domains.entities.stocks.CreateStock;
import com.mycompany.buat_apk.domains.entities.stocks.DailyTransactionSummary;
import com.mycompany.buat_apk.domains.entities.stocks.ProductTransactionSummary;
import com.mycompany.buat_apk.domains.entities.stocks.TransactionItem;

public interface StockRepository {
    public CreateStock createStocks(CreateStock s) throws SQLException;
    public int getProductStocks(int product_id) throws SQLException;
    List<TransactionItem> getAllTransactions() throws SQLException;
    List<DailyTransactionSummary> getDailySummaryByMonth(YearMonth month) throws SQLException;
    List<ProductTransactionSummary> getProductSummaryByMonth(YearMonth month) throws SQLException;
}
