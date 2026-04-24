package com.mycompany.buat_apk.domains.repositories;

import java.sql.SQLException;
import java.util.List;

import com.mycompany.buat_apk.domains.entities.products.CreateProduct;
import com.mycompany.buat_apk.domains.entities.products.Product;

public interface ProductRepository {
    public Long createProductReturnId(CreateProduct c) throws SQLException;
    public List<Product> getAllProducts() throws SQLException;
}
