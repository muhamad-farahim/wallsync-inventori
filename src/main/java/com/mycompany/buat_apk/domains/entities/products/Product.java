package com.mycompany.buat_apk.domains.entities.products;

import java.util.Date;

public class Product {
    private Long id;
    private String name;
    private String image;
    private String description;
    private Long category_id;
    private Date created_at;
    private Long price;

    public Product(Long id, String name, String image, String description, Long category_id, Date created_at, Long price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.category_id = category_id;
        this.created_at = created_at;
        this.price = price;
    }

    public Product() {
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
}
