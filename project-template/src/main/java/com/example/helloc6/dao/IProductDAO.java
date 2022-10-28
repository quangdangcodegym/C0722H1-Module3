package com.example.helloc6.dao;

import com.example.helloc6.model.Product;
import com.example.helloc6.model.dto.ProductDTO;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
    public void insertProduct(Product Product) throws SQLException;

    public Product selectProduct(int id);

    public List<Product> selectAllProducts();

    public List<Product> selectAllProduct(int page, int recordsPerPage, String q, int idCategory);
    public int getNoOfRecords();


    public boolean deleteProduct(int id) throws SQLException;

    public boolean updateProduct(Product Product) throws SQLException;

    public boolean updateProductWithSP(Product Product) throws SQLException;

    public boolean checkEmailExists(String email) throws SQLException;
}
