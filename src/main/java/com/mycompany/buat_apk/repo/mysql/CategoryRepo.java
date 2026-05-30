package com.mycompany.buat_apk.repo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.buat_apk.domains.entities.categories.Category;
import com.mycompany.buat_apk.domains.repositories.CategoryRepository;

public class CategoryRepo implements CategoryRepository {

    private final Connection conn;

	public CategoryRepo(final Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT id, name, code FROM categories;";

        try(PreparedStatement stmt = this.conn.prepareStatement(query);
                ResultSet res = stmt.executeQuery()) {

            while (res.next()) {
               Category category = new Category(
                       res.getLong("id"), 
                       res.getString("name"), 
                       res.getString("code")
                       );  
               categories.add(category);
            }

        }
		return categories;
	}

    @Override
public Long createProductReturnid(Category category) throws SQLException {
    String query = "INSERT INTO categories (name, code) VALUES (?, ?)";
    try (PreparedStatement stmt = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, category.getName());
        stmt.setString(2, category.getCode());
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
    String query = "UPDATE categories SET name = ?, code = ? WHERE id = ?";
    try (PreparedStatement stmt = this.conn.prepareStatement(query)) {
        stmt.setString(1, category.getName());
        stmt.setString(2, category.getCode());
        stmt.setLong(3, category.getId());
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
