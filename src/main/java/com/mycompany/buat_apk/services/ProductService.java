package com.mycompany.buat_apk.services;

import java.sql.SQLException;

import com.mycompany.buat_apk.domains.entities.products.CreateProduct;
import com.mycompany.buat_apk.domains.entities.products.Product;
import com.mycompany.buat_apk.domains.entities.stocks.CreateStock;
import com.mycompany.buat_apk.domains.repositories.ProductRepository;
import com.mycompany.buat_apk.domains.repositories.StockRepository;

public class ProductService {
    ProductRepository repo;
    StockRepository stockRepo;


    public ProductService(ProductRepository repo, StockRepository stockRepo) {
		this.repo = repo;
		this.stockRepo = stockRepo;
	}

	public CreateProduct createProduct(Long userId, CreateProduct data) {
        //create the product
        Long productId = null;
        try {
             productId = this.repo.createProductReturnId(data);

        }catch(SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
            return null;
        }

        try {
            CreateStock stockData = new CreateStock(userId, productId, data.getQuantity());
            this.stockRepo.createStocks(stockData);

        }catch(SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
            return null;
        }

        //create the stock
        
        return data;
    }
}
