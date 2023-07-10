package com.novqigarrix.java.database.repository;

import com.novqigarrix.java.database.model.ProductModel;

import java.sql.SQLException;

public interface ProductRepository {

    ProductModel[] findAll() throws SQLException;

    ProductModel findOne(String idProduk) throws SQLException;

    ProductModel create(ProductModel product) throws SQLException;

    void createMany(ProductModel[] products) throws SQLException;

    boolean delete(String idProduk) throws SQLException;

    void truncate() throws SQLException;

}
