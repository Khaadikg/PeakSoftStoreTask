package service;

import excp.MyException;
import model.Products;

public interface ProductService {
    void addProduct(Products products);
    void getProductByName(String name) throws MyException;
    void checkForDueToProduct();
    void checkForDueToWeekProduct();
    void getAllProduct() throws MyException;

}
