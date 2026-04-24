package com.mycompany.buat_apk.domains.entities.products;


public class CreateProduct {
    private String name;
    private String image;
    private String description;
    private Long price;
    private Long category_id;
    private int quantity;

    public CreateProduct(String name, String image, String description, Long category_id, int quantity, Long price) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.category_id = category_id;
        this.quantity = quantity;
        this.price = price;
    }

    public CreateProduct() {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

