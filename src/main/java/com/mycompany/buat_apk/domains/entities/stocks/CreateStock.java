 package com.mycompany.buat_apk.domains.entities.stocks;

 public class CreateStock {
    private Long user_id;
    private Long productId;
    private Long customerId = null;
    private int quantity;
    private Long price = null;

    public CreateStock(Long user_id, Long productId, int quantity) {
        this.user_id = user_id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CreateStock() {
	}

	public Long getUserId() {
        return user_id;
    }

    public void setUserId(Long id) {
        this.user_id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
