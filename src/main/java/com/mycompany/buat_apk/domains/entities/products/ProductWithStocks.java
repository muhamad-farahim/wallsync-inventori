package com.mycompany.buat_apk.domains.entities.products;

import java.io.File;
import java.util.Date;

public class ProductWithStocks {
    private Long id;
    private String name;
    private String image;
    private String description;
    private Long category_id;
    private Date created_at;
    private Long price;
    private int stocks;
    private String category_name;

    public ProductWithStocks(Long id, String name, String image, String description, Long category_id, Date created_at, Long price, int stocks, String category_name) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.category_id = category_id;
        this.created_at = created_at;
        this.price = price;
        this.stocks = stocks;
        this.category_name = category_name;
    }

    public ProductWithStocks() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return category_id;
    }

    public void setCategoryId(Long category_id) {
        this.category_id = category_id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getStocks() {
        return this.stocks;
    }
    
    public void setStocks(int stocks) {
        this.stocks = stocks;
    }

    public String getCategoryName() {
        return this.category_name;
    }

    public void setCategoryName(String category_name) {
        this.category_name = category_name;
    }

    public File getImageFile() {
        String rootDir = System.getProperty("user.dir");

        return new File(rootDir + 
                File.separator + "storage" +
                File.separator + "images" +
                File.separator + this.image
            );
    }
}
