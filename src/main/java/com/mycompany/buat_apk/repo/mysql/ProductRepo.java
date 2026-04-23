package com.mycompany.buat_apk.repo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.buat_apk.domains.entities.products.CreateProduct;
import com.mycompany.buat_apk.domains.entities.products.Product;
import com.mycompany.buat_apk.domains.repositories.ProductRepository;

public class ProductRepo implements ProductRepository {
    private final Connection conn;

	public ProductRepo(final Connection conn) {
		this.conn = conn;
	}

	@Override
	public CreateProduct createProduct(CreateProduct c) throws SQLException {
        String sql = "INSERT INTO products (name, image, description, category_id) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, c.getName());
        stmt.setString(2, c.getImage());
        stmt.setString(3, c.getDescription());
        stmt.setLong(4, c.getCategoryId());

        stmt.executeUpdate();
        System.out.println("Product " + c.getName() + " created successfully");

		return c;
	}

    @Override
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, image, description, category_id, created_at FROM products";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             java.sql.ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("image"),
                    rs.getString("description"),
                    rs.getLong("category_id"),
                    rs.getTimestamp("created_at")
                );
                products.add(product);
            }
        }
        return products;
    }

}
