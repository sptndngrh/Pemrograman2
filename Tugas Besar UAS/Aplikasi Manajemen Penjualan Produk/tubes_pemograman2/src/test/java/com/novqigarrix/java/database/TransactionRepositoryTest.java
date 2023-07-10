package com.novqigarrix.java.database;

import com.novqigarrix.java.database.model.ProductModel;
import com.novqigarrix.java.database.model.ProductTransactionModel;
import com.novqigarrix.java.database.model.TransactionModel;
import com.novqigarrix.java.database.repository.ProductRepositoryImpl;
import com.novqigarrix.java.database.repository.TransactionRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class TransactionRepositoryTest {

    static {
        System.setProperty("JDBC_URL", "jdbc:mysql://localhost:3306/tubes_pemograman2_test");
        System.setProperty("MYSQL_USERNAME", "root");
        System.setProperty("MYSQL_PASSWORD", "mysql");
    }

    @Test
    void testCreateTransaction() throws SQLException {

        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        productRepository.truncate();
        
        TransactionRepositoryImpl transactionRepository = new TransactionRepositoryImpl();
        transactionRepository.truncate();

        // Create produk nya dulu
        ProductModel productModel = new ProductModel();

        productModel.setIdProduk("P001");
        productModel.setNamaProduk("Intellij IDEA Ultimate");
        productModel.setStok(9999);
        productModel.setHargaJual(30);
        productModel.setHargaBeli(50);

        ProductModel newProduct = productRepository.create(productModel);

        TransactionModel transaksi = new TransactionModel();
        transaksi.setIdProduk(newProduct.getIdProduk());
        transaksi.setHarga(30);
        transaksi.setQuantity(5);

        TransactionModel createdTransaksi = transactionRepository.create(transaksi);

        Assertions.assertEquals(createdTransaksi.getIdProduk(), transaksi.getIdProduk());
        Assertions.assertEquals(createdTransaksi.getHarga(), transaksi.getHarga());
        Assertions.assertEquals(createdTransaksi.getQuantity(), transaksi.getQuantity());

        // Check apakah stok produk nya berkurang
        ProductModel product = productRepository.findOne(transaksi.getIdProduk());
        Assertions.assertEquals(product.getStok(), 9999 - transaksi.getQuantity());

    }

    @Test
    void testFindAllTransactions() throws SQLException {

        TransactionRepositoryImpl transactionRepository = new TransactionRepositoryImpl();
        transactionRepository.truncate();

        TransactionModel[] transactionModels = new TransactionModel[3];

        for (int i = 0; i < 3; i++) {
            TransactionModel transaksi = new TransactionModel();
            transaksi.setIdProduk("P00" + i);
            transaksi.setHarga(30 + i);
            transaksi.setQuantity(5 + i);

            transactionModels[i] = transactionRepository.create(transaksi);
        }

        TransactionModel[] results = transactionRepository.findAll();
        Assertions.assertEquals(results.length, transactionModels.length);

    }

    @Test
    void testCreateMany() throws SQLException, InterruptedException {

        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        productRepository.truncate();

        TransactionRepositoryImpl transactionRepository = new TransactionRepositoryImpl();
        transactionRepository.truncate();

        ProductModel[] productModels = new ProductModel[10];

        for (int i = 0; i < 10; i++) {
            ProductModel productModel = new ProductModel();

            productModel.setIdProduk("P00" + i);
            productModel.setNamaProduk("Intellij IDEA Ultimate");
            productModel.setStok(99999);
            productModel.setHargaJual(30 + i);
            productModel.setHargaBeli(50 + i);

            productModels[i] = productRepository.create(productModel);
        }

        TransactionModel[] transactionModels = new TransactionModel[10];

        for (int i = 0; i < 10; i++) {
            TransactionModel transaksi = new TransactionModel();
            transaksi.setIdProduk(productModels[i].getIdProduk());
            transaksi.setHarga(30 + i);
            transaksi.setQuantity(5 + i);

            transactionModels[i] = transactionRepository.create(transaksi);
        }

        transactionRepository.createMany(transactionModels);
        Thread.sleep(2000);

        TransactionModel[] transactions = transactionRepository.findAll();
        Assertions.assertEquals(transactions.length, transactionModels.length);

        // Periksa apakah stok nya berkurang sesuai quantity di transaksi
        for (TransactionModel transaksi : transactionModels) {
            ProductModel produk = productRepository.findOne(transaksi.getIdProduk());
            if(produk == null) continue;

            Stream<ProductModel> productModelStream = Arrays.stream(productModels).filter(p -> p.getIdProduk().equals(produk.getIdProduk()));
            Optional<ProductModel> matchedProdukOptional = productModelStream.findFirst();
            if(!matchedProdukOptional.isPresent()) continue;

            ProductModel matchedProduk = matchedProdukOptional.get();

            Assertions.assertEquals(produk.getStok(), matchedProduk.getStok() - transaksi.getQuantity());
        }

    }

    @Test
    void testFindAllProductAndTransactions() throws SQLException {

        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        TransactionRepositoryImpl transactionRepository = new TransactionRepositoryImpl();

        productRepository.truncate();
        transactionRepository.truncate();

        ProductModel[] productModels = new ProductModel[3];

        for (int i = 0; i < 3; i++) {
            ProductModel productModel = new ProductModel();

            productModel.setIdProduk("P00" + i);
            productModel.setNamaProduk("Intellij IDEA Ultimate");
            productModel.setStok(99999);
            productModel.setHargaJual(30 + i);
            productModel.setHargaBeli(50 + i);

            productModels[i] = productRepository.create(productModel);
        }

        TransactionModel[] transaksis = new TransactionModel[productModels.length];

        for (int i = 0; i < productModels.length; i++) {
            TransactionModel transaction = new TransactionModel();

            ProductModel product = productModels[i];

            transaction.setIdProduk(product.getIdProduk());
            transaction.setHarga(product.getHargaJual());
            transaction.setQuantity((i + 1) * 3);

            transaksis[i] = transactionRepository.create(transaction);
        }

        ProductTransactionModel[] results = transactionRepository.findAllProductAndTransactions();
        Assertions.assertEquals(results.length, productModels.length);

        for (int i = 0; i < results.length; i++) {
            ProductModel product = productModels[i];
            TransactionModel transaksi = transaksis[i];

            ProductTransactionModel pt = results[i];

            Assertions.assertEquals(pt.getIdProduk(), product.getIdProduk());
            Assertions.assertEquals(pt.getNamaProduk(), product.getNamaProduk());
            Assertions.assertEquals(pt.getHarga(), transaksi.getHarga());
            Assertions.assertEquals(pt.getQuantity(), transaksi.getQuantity());
        }

    }

}
