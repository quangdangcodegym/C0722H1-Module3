package com.example.helloc6.model.dto;

import com.example.helloc6.model.Product;

import java.math.BigDecimal;

public class OrderItemDTO {
    private int id;
    private int idOrder;
    private Product product;
    private int quantity;
    private BigDecimal price;
    public OrderItemDTO(){

    }

    public OrderItemDTO(int id, int idOrder, Product product, int quantity, BigDecimal price) {
        this.id = id;
        this.idOrder = idOrder;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
