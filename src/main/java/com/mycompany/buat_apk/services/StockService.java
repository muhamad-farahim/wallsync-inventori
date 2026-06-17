/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.buat_apk.services;

/**
 *
 * @author DANDY
 */
import java.util.*;
import java.sql.SQLException;
import java.time.YearMonth;

import com.mycompany.buat_apk.domains.entities.stocks.DailyTransactionSummary;
import com.mycompany.buat_apk.domains.entities.stocks.ProductTransactionSummary;
import com.mycompany.buat_apk.domains.entities.stocks.TransactionItem;
import com.mycompany.buat_apk.repo.mysql.StockRepo;
import com.mycompany.buat_apk.services.StockService;
import com.mycompany.buat_apk.domains.repositories.StockRepository;

public class StockService {
    private StockRepository stockRepo;

    public StockService(StockRepository stockRepo) {
        this.stockRepo = stockRepo;
    }

    public List<TransactionItem> getAllTransactions() {

        try {
            return stockRepo.getAllTransactions();
        }
        catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DailyTransactionSummary> getDailySummaryByMonth(YearMonth month) {

        try {
            return stockRepo.getDailySummaryByMonth(month);
        }
        catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ProductTransactionSummary> getProductSummaryByMonth(YearMonth month) {

        try {
            return stockRepo.getProductSummaryByMonth(month);
        }
        catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}


