package com.mycompany.buat_apk.repo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
