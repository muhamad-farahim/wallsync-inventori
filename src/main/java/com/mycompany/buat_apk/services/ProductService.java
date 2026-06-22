package com.mycompany.buat_apk.services;

import java.sql.SQLException;
import java.util.*;

import com.mycompany.buat_apk.domains.entities.products.CreateProduct;
import com.mycompany.buat_apk.domains.entities.products.ProductDetails;
import com.mycompany.buat_apk.domains.entities.products.ProductWithStocks;
import com.mycompany.buat_apk.domains.entities.products.UpdateProduct;
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
            stockData.setPrice(data.getBuyingPrice());
            this.stockRepo.createStocks(stockData);

        }catch(SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
            return null;
        }
        
        return data;
    }

    public List<ProductWithStocks> getAllProductsWithStocks() {
        try {
            return this.repo.getAllProductsWithStocks();
        }catch(SQLException e) {
            System.err.println("Error when fetching all products with stocks");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ProductDetails getProductDetailsWithId(Long id) {
        try {
            ProductDetails productDetails = this.repo.getProductDetailById(id);
            return productDetails;
        }catch(Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void updateProduct(Long id, UpdateProduct data) {
        try {
            this.repo.updateProduct(id, data);
        }catch(Exception e) {
            System.err.println("Update product has failed.");
            System.err.println(e.getMessage());
        }
    }
    public void deleteProduct(Long id) {
    try {
        this.repo.deleteProductById(id);
    } catch (Exception e) {
            System.err.println("Delete product has failed.");
            System.err.println(e.getMessage());
        }
    }
    
    public List<ProductWithStocks> searchProducts(
            String keyword
    ) {

        try {
            return repo.searchProducts(keyword);
        } catch(SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<ProductWithStocks> getProductsByCategory(Long categoryId) {
        try {
            return repo.getProductsByCategory(categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    /**
     *
     * @param productId
     * @param customerId
     * @param qty
     * @param price
     * @param description
     * @throws SQLException
     */
    public void createSales (
        Long productId,
        Long customerId,
        int qty,
        Long price,
        String description,
        Long userId
    ) 
    throws SQLException {

        CreateStock stock = new CreateStock();

        stock.setUserId(userId);
        stock.setProductId(productId);
        stock.setCustomerId(customerId);

        stock.setQuantity(-qty);

        stock.setPrice(price);
        stock.setDescription(description);

        this.stockRepo.createStocks(stock);
    }
    
    public void createPurchase(
        Long productId,
        int qty,
        Long price,
        String description,
        Long userId
    ) throws SQLException {

        CreateStock stock = new CreateStock();

        stock.setUserId(userId);
        stock.setProductId(productId);
        stock.setQuantity(qty);
        stock.setPrice(price);
        stock.setDescription(description);

        stockRepo.createStocks(stock);
    }
}
