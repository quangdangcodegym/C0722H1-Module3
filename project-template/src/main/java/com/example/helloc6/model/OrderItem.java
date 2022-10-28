package com.example.helloc6.model;

import java.math.BigDecimal;

public class OrderItem {
    private int id;
    private int idOrder;
    private int idProduct;
    private int quantity;
    private BigDecimal price;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderItem(int id, int idOrder, int idProduct, int quantity, BigDecimal price) {
        this.quantity = quantity;
        this.id = id;
        this.idOrder = idOrder;
        this.idProduct = idProduct;
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

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
