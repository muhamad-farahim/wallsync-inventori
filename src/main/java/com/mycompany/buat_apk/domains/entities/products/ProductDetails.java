package com.mycompany.buat_apk.domains.entities.products;


import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.mycompany.buat_apk.domains.entities.stocks.StockDetailItem;

public class ProductDetails {
    private Long id;
    private String name;
    private String image;
    private String description;
    private Long category_id;
    private String category_name;
    private Date created_at;
    private Long price;
    private int stocks;
    private List<StockDetailItem> transactions;

    public ProductDetails(
            Long id, String name, String image, String description, Long category_id, Date created_at, Long price,
            String category_name) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.category_id = category_id;
        this.created_at = created_at;
        this.price = price;
        this.category_name = category_name;
    }

    public ProductDetails() {
    }

    public List<StockDetailItem> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(List<StockDetailItem> transactions) {
        this.transactions = transactions;
    }

    public String getStatus() {
        return this.stocks < 1 ? "Stock habis" : this.stocks < 5 ? "Stock Kritis" : "In stock";
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

    public String getCategoryName() {
        return category_name;
    }

    public void setCategoryName(String category_name) {
        this.category_name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public File getImageFile() {
        String rootDir = System.getProperty("user.dir");

        return new File(rootDir + 
                File.separator + "storage" +
                File.separator + "images" +
                File.separator + this.image
            );
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

    public int getStocks() {
        return this.stocks;
    }

    public String getStocksString() {
        return String.valueOf(this.stocks);
    }

    public void setStocks(int stocks) {
        this.stocks = stocks;
    }

    public Long getPrice() {
        return price;
    }

    public String getRPPrice() {
        Locale localeID = Locale.of("id", "ID"); 
        NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(localeID);
        return rupiahFormat.format(price); 
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return created_at;
    }

    public String getFormattedCreatedDate() {

        if (this.created_at == null) return "-";

        SimpleDateFormat formatter = new SimpleDateFormat("d MMMM yyyy", Locale.of("id", "ID"));

        return formatter.format(this.created_at);

    }

    public void setCreatedAt(Date created_at) {
        this.created_at = created_at;
    }
}
