package com.mycompany.buat_apk.domains.repositories;

import java.sql.SQLException;
import java.util.List;

import com.mycompany.buat_apk.domains.entities.categories.Category;

public interface CategoryRepository {
    public List<Category> getAllCategories() throws SQLException;
    public Long createProductReturnid(Category category) throws SQLException;
    public void updateCategory(Category category) throws SQLException;
    public void deleteProductById(Long id) throws SQLException;
}
