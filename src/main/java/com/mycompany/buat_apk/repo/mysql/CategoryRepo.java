package com.mycompany.buat_apk.repo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.buat_apk.domains.entities.categories.Category;
import com.mycompany.buat_apk.domains.entities.categories.CategoryWithProductCount;
import com.mycompany.buat_apk.domains.repositories.CategoryRepository;

public class CategoryRepo implements CategoryRepository {

    private final Connection conn;

    public CategoryRepo(final Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<CategoryWithProductCount> getAllCategories() throws SQLException {
        List<CategoryWithProductCount> categories = new ArrayList<>();

        // SQL query with a LEFT JOIN to count products grouped by each category id
        String query = "SELECT c.id, c.name, c.code, c.description, COUNT(p.id) AS product_count " +
            "FROM categories c " +
            "LEFT JOIN products p ON c.id = p.category_id " +
            "GROUP BY c.id, c.name, c.code, c.description;";

        try (PreparedStatement stmt = this.conn.prepareStatement(query);
                ResultSet res = stmt.executeQuery()) {

            while (res.next()) {
                CategoryWithProductCount category = new CategoryWithProductCount(
                        res.getLong("id"), 
                        res.getString("name"), 
                        res.getString("code"),
                        res.getString("description"),
                        res.getInt("product_count") // Maps to your new Integer productCount field
                        );  
                categories.add(category);
            }
                }
        return categories;
    }

    @Override
    public Long createProductReturnid(Category category) throws SQLException {
        String query = "INSERT INTO categories (name, code, description) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getCode());
            stmt.setString(3, category.getDescription());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Insert failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public void updateCategory(Category category) throws SQLException {
        String query = "UPDATE categories SET name = ?, code = ?, description = ? WHERE id = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(query)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getCode());
            stmt.setString(3, category.getDescription());
            stmt.setLong(4, category.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Update failed, no category found with id: " + category.getId());
            }
        }
    }

    @Override
    public void deleteProductById(Long id) throws SQLException {
        String query = "DELETE FROM categories WHERE id = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(query)) {
            stmt.setLong(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Delete failed, no category found with id: " + id);
            }
        }
    }
}
