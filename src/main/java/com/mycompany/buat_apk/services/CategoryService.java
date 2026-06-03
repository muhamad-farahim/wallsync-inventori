package com.mycompany.buat_apk.services;

import java.sql.SQLException;
import java.util.List;

import com.mycompany.buat_apk.domains.entities.categories.Category;
import com.mycompany.buat_apk.domains.entities.categories.CategoryWithProductCount;
import com.mycompany.buat_apk.domains.repositories.CategoryRepository;

public class CategoryService {

   private CategoryRepository repo;

   public CategoryService(CategoryRepository repo) {
	this.repo = repo;
   } 

   public List<CategoryWithProductCount> getAllCategories() {
       try {
           return this.repo.getAllCategories();
       }catch(SQLException e) {
            System.out.println("Database error while fetching all categories: " + e.getMessage());
            System.exit(1);
            return null;
       }
   }

   public Long CreateCategoryReturnId(Category category) {
    try {
        if (category.getName() != null && !category.getName().trim().isEmpty()) {
            
            String name = category.getName().trim().toLowerCase();
            
            name = name.replaceAll("[^a-z0-9\\s-]", "");
            
            String kebabCaseCode = name.replaceAll("\\s+", "-");
            
            category.setCode(kebabCaseCode);
        }

        return this.repo.createProductReturnid(category);
        
    } catch(SQLException e) {
        System.err.println("Error when creating category:");
        System.err.println(e);
        return 0L;
    }
}
}
