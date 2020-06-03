package DAO;

import Entities.Products;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IProductDAO {
    void addProduct(Products products);
    void deleteProduct(int id);
    List<Products> getAllProducts();
    //void printAllProducts() throws JsonProcessingException;
    List<Products> searchProductsById(int id) throws JsonProcessingException;
    List<Products>  searchProductsByName(String name) throws JsonProcessingException;
}
