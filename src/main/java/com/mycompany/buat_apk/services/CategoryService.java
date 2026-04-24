package com.mycompany.buat_apk.services;

import java.sql.SQLException;
import java.util.List;

import com.mycompany.buat_apk.domains.entities.categories.Category;
import com.mycompany.buat_apk.domains.repositories.CategoryRepository;

public class CategoryService {

   private CategoryRepository repo;

   public CategoryService(CategoryRepository repo) {
	this.repo = repo;
   } 

   public List<Category> getAllCategories() {
       try {
           return this.repo.getAllCategories();
       }catch(SQLException e) {
            System.out.println("Database error while fetching all categories: " + e.getMessage());
            System.exit(1);
            return null;
       }
   }
}
