package com.example.helloc6.dao;

import com.example.helloc6.model.dto.ProductDTO;

import java.sql.SQLException;
import java.util.List;

public interface IProductDTODAO {
    public void insertProductDTO(ProductDTO ProductDTO) throws SQLException;

    public ProductDTO selectProductDTO(int id);

    public List<ProductDTO> selectAllProductDTOs();

    public List<ProductDTO> selectAllProductDTO(int page, int recordsPerPage, String q, int idCategory);

    public int getNoOfRecords();

    public boolean deleteProductDTO(int id) throws SQLException;

    public boolean updateProductDTO(ProductDTO ProductDTO) throws SQLException;

    public boolean updateProductDTOWithSP(ProductDTO ProductDTO) throws SQLException;
}
