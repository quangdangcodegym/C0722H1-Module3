package com.example.helloc6.dao.impl;

import com.example.helloc6.dao.*;
import com.example.helloc6.model.Category;
import com.example.helloc6.model.Image;
import com.example.helloc6.model.Product;
import com.example.helloc6.model.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDTODAO extends DatabaseQuery implements IProductDTODAO {
    private IProductDAO iProductDAO;
    private IImageDAO iImageDAO;
    private ICategoryDAO iCategoryDAO;

    public ProductDTODAO(){
        iProductDAO = new ProductDAO();
        iImageDAO = new ImageDAO();
        iCategoryDAO = new CategoryDAO();
    }

    @Override
    public void insertProductDTO(ProductDTO ProductDTO) throws SQLException {
        
    }

    @Override
    public ProductDTO selectProductDTO(int id) {
        return null;
    }

    @Override
    public List<ProductDTO> selectAllProductDTOs() {
        List<ProductDTO> listProductDTO = new ArrayList<>();
        List<Product> listProduct = iProductDAO.selectAllProducts();
        try{
            for(Product p : listProduct){
                ProductDTO productDTO = new ProductDTO();
                List<Image> imageList = iImageDAO.selectImagesByProductId(p.getId());
                Category category = iCategoryDAO.selectCategory(p.getIdCategory());
                productDTO.setProductToProductDTO(p);
                productDTO.setImagesToProductDTO(imageList);
                productDTO.setCategory(category);

                listProductDTO.add(productDTO);
            }
        }catch (SQLException ex){
            printSQLException(ex);
        }

        return listProductDTO;
    }

    @Override
    public List<ProductDTO> selectAllProductDTO(int page, int recordsPerPage, String q, int idCategory) {
        List<Product> productList = iProductDAO.selectAllProduct(page, recordsPerPage, q, idCategory);

        List<ProductDTO> productDTOList = new ArrayList<>();
        try{
            for(Product p : productList){
                ProductDTO productDTO = new ProductDTO();
                List<Image> imageList = iImageDAO.selectImagesByProductId(p.getId());
                Category category = iCategoryDAO.selectCategory(p.getIdCategory());
                productDTO.setProductToProductDTO(p);
                productDTO.setImagesToProductDTO(imageList);
                productDTO.setCategory(category);

                productDTOList.add(productDTO);
            }
            return productDTOList;
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return null;
    }

    @Override
    public int getNoOfRecords() {
        return iProductDAO.getNoOfRecords();
    }

    @Override
    public boolean deleteProductDTO(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateProductDTO(ProductDTO ProductDTO) throws SQLException {
        return false;
    }

    @Override
    public boolean updateProductDTOWithSP(ProductDTO ProductDTO) throws SQLException {
        return false;
    }
}
