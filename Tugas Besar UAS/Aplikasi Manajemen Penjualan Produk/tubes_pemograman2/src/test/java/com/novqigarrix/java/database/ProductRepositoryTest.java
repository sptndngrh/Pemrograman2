package com.novqigarrix.java.database;

import com.novqigarrix.java.database.model.ProductModel;
import com.novqigarrix.java.database.repository.ProductRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ProductRepositoryTest {

    static {
        System.setProperty("JDBC_URL", "jdbc:mysql://localhost:3306/tubes_pemograman2_test");
        System.setProperty("MYSQL_USERNAME", "root");
        System.setProperty("MYSQL_PASSWORD", "mysql");
    }

    @Test
    void testFindAll() throws SQLException {

        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        productRepository.truncate();

        for (int i = 0; i < 3; i++) {
            ProductModel productModel = new ProductModel();

            productModel.setIdProduk("P00" + i);
            productModel.setNamaProduk("Intellij IDEA Ultimate");
            productModel.setStok(99999);
            productModel.setHargaJual(30 + i);
            productModel.setHargaBeli(50 + i);

            productRepository.create(productModel);
        }

        ProductModel[] productModels = productRepository.findAll();

        Assertions.assertEquals(productModels.length, 3);

    }

    @Test
    void testCreateProduct() throws SQLException {

        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        productRepository.truncate();

        ProductModel productModel = new ProductModel();

        productModel.setIdProduk("P001");
        productModel.setNamaProduk("Intellij IDEA Ultimate");
        productModel.setStok(99999);
        productModel.setHargaJual(30);
        productModel.setHargaBeli(50);

        ProductModel createdProduct = productRepository.create(productModel);

        Assertions.assertEquals(createdProduct.getIdProduk(), productModel.getIdProduk());
        Assertions.assertEquals(createdProduct.getNamaProduk(), productModel.getNamaProduk());
        Assertions.assertEquals(createdProduct.getStok(), productModel.getStok());
        Assertions.assertEquals(createdProduct.getHargaBeli(), productModel.getHargaBeli());
        Assertions.assertEquals(createdProduct.getHargaJual(), productModel.getHargaJual());

    }

    @Test
    void testCreateManyProducts() throws SQLException, InterruptedException {

        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        productRepository.truncate();

        ProductModel[] productModels = new ProductModel[3];

        for (int i = 0; i < 3; i++) {
            ProductModel productModel = new ProductModel();

            productModel.setIdProduk("P00" + i);
            productModel.setNamaProduk("Intellij IDEA Ultimate" + i + 1);
            productModel.setStok(99999);
            productModel.setHargaJual(30 + i);
            productModel.setHargaBeli(50 + i);

            productModels[i] = productModel;
        }

        productRepository.createMany(productModels);

        Thread.sleep(5000);

        ProductModel[] createdProducts = productRepository.findAll();
        Assertions.assertEquals(productModels.length, createdProducts.length);

    }
    
    @Test
    void testFindOneSuccess() throws SQLException {

        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        productRepository.truncate();

        ProductModel productModel = new ProductModel();

        productModel.setIdProduk("P001");
        productModel.setNamaProduk("Intellij IDEA Ultimate");
        productModel.setStok(99999);
        productModel.setHargaJual(30);
        productModel.setHargaBeli(50);

        ProductModel createdProduct = productRepository.create(productModel);

        ProductModel foundProduct = productRepository.findOne(createdProduct.getIdProduk());

        Assertions.assertEquals(createdProduct.getIdProduk(), foundProduct.getIdProduk());
        Assertions.assertEquals(createdProduct.getNamaProduk(), foundProduct.getNamaProduk());
        Assertions.assertEquals(createdProduct.getStok(), foundProduct.getStok());
        Assertions.assertEquals(createdProduct.getHargaBeli(), foundProduct.getHargaBeli());
        Assertions.assertEquals(createdProduct.getHargaJual(), foundProduct.getHargaJual());

    }

    @Test
    void testFindOneFailed() throws SQLException {

        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        productRepository.truncate();

        ProductModel foundProduct = productRepository.findOne("NO ID");
        Assertions.assertNull(foundProduct);

    }

    @Test
    void testDeleteSuccess() throws SQLException {

        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        productRepository.truncate();

        ProductModel productModel = new ProductModel();

        productModel.setIdProduk("P001");
        productModel.setNamaProduk("Intellij IDEA Ultimate");
        productModel.setStok(99999);
        productModel.setHargaJual(30);
        productModel.setHargaBeli(50);

        ProductModel createdProduct = productRepository.create(productModel);

        boolean isSuccess = productRepository.delete(createdProduct.getIdProduk());
        Assertions.assertTrue(isSuccess);

    }

    @Test
    void testDeleteFailed() throws SQLException {

        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        productRepository.truncate();

        boolean isSuccess = productRepository.delete("NO ID");
        Assertions.assertFalse(isSuccess);

    }

}
