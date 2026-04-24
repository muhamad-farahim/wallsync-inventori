package com.mycompany.buat_apk.domains.repositories;

import java.sql.SQLException;
import java.util.List;

import com.mycompany.buat_apk.domains.entities.categories.Category;

public interface CategoryRepository {
    public List<Category> getAllCategories() throws SQLException;
}
