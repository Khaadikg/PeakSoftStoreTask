package dao;

import enums.Companies;
import model.Products;

import java.util.ArrayList;

public class ProductDao {

    private static ArrayList<Products> products = new ArrayList<>();

    public static void addProductToDB(Products products){
        boolean exist = true;
        try {
            Companies.valueOf(products.getCompany());
        } catch (IllegalArgumentException e) {
            exist = false;
        }
        if (exist) ProductDao.products.add(products);
        else System.out.println("Продукции компании " + products.getCompany() + " не допускаюся!");
//        ProductDao.products.add(products);
    }

    public static ArrayList<Products> getDBProducts(){
        return products;
    }

}
