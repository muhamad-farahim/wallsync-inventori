package com.mycompany.buat_apk.domains.entities.categories;

public class CategoryWithProductCount {
    private Long id;  
    private String name;  
    private String code;
    private String description;
    private Integer productCount;

    public CategoryWithProductCount() {
    }

    public CategoryWithProductCount(Long id, String name, String code, String description, Integer productCount) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.productCount = productCount;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCode() { return code; }
    public String getDescription() { return description; }
    public Integer getProductCount() { return productCount; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCode(String code) { this.code = code; }
    public void setDescription(String description) { this.description = description; }
    public void setProductCount(Integer productCount) { this.productCount = productCount; }
}
