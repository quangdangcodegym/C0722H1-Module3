package com.codegym.usermanager.dao;

public interface ICartDAO {
    public void addToCart(int cartId, int productId, int quantity, double price);
}
