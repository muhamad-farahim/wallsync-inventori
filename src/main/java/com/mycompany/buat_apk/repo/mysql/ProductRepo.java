package com.mycompany.buat_apk.repo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.buat_apk.db.DbConnection;
import com.mycompany.buat_apk.domains.entities.products.CreateProduct;
import com.mycompany.buat_apk.domains.entities.products.Product;
import com.mycompany.buat_apk.domains.entities.products.ProductDetails;
import com.mycompany.buat_apk.domains.entities.products.ProductWithStocks;
import com.mycompany.buat_apk.domains.entities.stocks.StockDetailItem;
import com.mycompany.buat_apk.domains.repositories.ProductRepository;

public class ProductRepo implements ProductRepository {
    private final Connection conn;

	public ProductRepo(final Connection conn) {
		this.conn = conn;
	}

	@Override
	public Long createProductReturnId(CreateProduct c) throws SQLException {
        String sql = "INSERT INTO products (name, image, description, category_id, price) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, c.getName());
        stmt.setString(2, c.getImage());
        stmt.setString(3, c.getDescription());
        stmt.setLong(4, c.getCategoryId());
        stmt.setLong(5, c.getPrice());

        int affectedRows = stmt.executeUpdate();
        System.out.println("Product " + c.getName() + " created successfully");

        if (affectedRows == 0) {
            throw new SQLException("Creating product failed, no rows affected.");
        }

        try (java.sql.ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                Long newId = generatedKeys.getLong(1);
                System.out.println("Product created in MySQL with ID: " + newId);
                return newId;
            } else {
                throw new SQLException("Creating product failed, no ID obtained.");
            }
        }
	}

    @Override
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, image, description, category_id, created_at, price FROM products";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             java.sql.ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("image"),
                    rs.getString("description"),
                    rs.getLong("category_id"),
                    rs.getTimestamp("created_at"),
                    rs.getLong("price")
                );
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public List<ProductWithStocks> getAllProductsWithStocks() throws SQLException {
        List<ProductWithStocks> products = new ArrayList<>();

        String sql = "SELECT p.id, p.name, p.image, p.description, p.category_id, p.created_at, p.price, c.name AS cname, " +
            "COALESCE(SUM(s.quantity), 0) AS total_stock " +
            "FROM products p " +
            "LEFT JOIN stocks s ON p.id = s.product_id INNER JOIN categories c ON p.category_id = c.id " +
            "GROUP BY p.id, p.name, p.image, p.description, p.category_id, p.created_at, p.price, c.name";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
                java.sql.ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProductWithStocks product = new ProductWithStocks(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getString("description"),
                        rs.getLong("category_id"),
                        rs.getTimestamp("created_at"),
                        rs.getLong("price"),
                        rs.getInt("total_stock"),
                        rs.getString("cname")
                        );
                products.add(product);
            }
                }
        return products;
    }

	@Override
	public void deleteProductById(Long id) throws SQLException {
        String query = "DELETE FROM products WHERE id=?";

        PreparedStatement stmt = this.conn.prepareStatement(query);
        stmt.setLong(1, id);

        stmt.executeUpdate();

        System.out.print("Product with the id of: ");
        System.out.print(id);
        System.out.println("has been deleted");
	}

    @Override
public ProductDetails getProductDetailById(Long id) throws SQLException {
    String productSql = """
        SELECT 
            p.id, p.name, p.image, p.description, p.price, p.created_at,
            c.id AS category_id, c.name AS category_name,
            IFNULL(SUM(s.quantity), 0) AS total_stock
        FROM products p
        INNER JOIN categories c ON p.category_id = c.id
        LEFT JOIN stocks s ON p.id = s.product_id
        WHERE p.id = ?
        GROUP BY p.id, c.id;
        """;

    String transactionsSql = """
        SELECT id, product_id, customer_id, quantity, price, created_at, user_id, description
        FROM stocks 
        WHERE product_id = ? 
        ORDER BY created_at DESC;
        """;

    try (Connection conn = DbConnection.getConnection()) {
        ProductDetails details = null;

        try (PreparedStatement stmt = conn.prepareStatement(productSql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    details = new ProductDetails();
                    details.setId(rs.getLong("id"));
                    details.setName(rs.getString("name"));
                    details.setImage(rs.getString("image"));
                    details.setDescription(rs.getString("description"));
                    details.setPrice(rs.getLong("price"));
                    details.setCreated_at(rs.getTimestamp("created_at"));
                    details.setCategoryId(rs.getLong("category_id"));
                    details.setCategoryName(rs.getString("category_name"));
                    
                    details.setStocks(rs.getInt("total_stock")); 
                }
            }
        }

        if (details != null) {
            List<StockDetailItem> history = new ArrayList<>();
            try (PreparedStatement stmt = conn.prepareStatement(transactionsSql)) {
                stmt.setLong(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        StockDetailItem item = new StockDetailItem(
                            rs.getLong("id"),
                            rs.getLong("product_id"),
                            rs.getLong("customer_id"),
                            rs.getInt("quantity"),
                            rs.getLong("price"),
                            rs.getDate("created_at"),
                            rs.getLong("user_id")
                        );
                        item.setDescription(rs.getString("description"));
                        history.add(item);
                    }
                }
            }
        }

        return details;
    }
}

}
