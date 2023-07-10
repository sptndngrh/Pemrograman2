package com.novqigarrix.java.database.repository;

import com.novqigarrix.java.database.model.ProductTransactionModel;
import com.novqigarrix.java.database.model.TransactionModel;
import com.novqigarrix.java.database.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class TransactionRepositoryImpl implements TransactionRepository {
    @Override
    public TransactionModel create(TransactionModel transaksi) throws SQLException {

        String querySQL = "INSERT INTO transaksi(id_produk, harga, quantity) VALUES (?, ?, ?)";

        Connection connection = DatabaseUtil.getDataSource().getConnection();
        PreparedStatement pr = connection.prepareStatement(querySQL);

        try {

            pr.setString(1, transaksi.getIdProduk());
            pr.setInt(2, transaksi.getHarga());
            pr.setInt(3, transaksi.getQuantity());

            pr.execute();

            String updateProductSQL = "UPDATE produk SET stok = produk.stok - ? WHERE id_produk = ?";

            PreparedStatement updatePr = connection.prepareStatement(updateProductSQL);
            updatePr.setInt(1, transaksi.getQuantity());
            updatePr.setString(2, transaksi.getIdProduk());

            updatePr.execute();

            connection.commit();
            return transaksi;

        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Gagal insert ke database...");
            System.out.println("Pesan Error: " + e.getMessage());
            throw new SQLException(e);
        } finally {
            pr.close();
            connection.close();
        }

    }

    @Override
    public void createMany(TransactionModel[] transactionModels) throws SQLException {

        for (TransactionModel model : transactionModels) {
            System.out.println("Sedang membuat transaksi dari produk: " + model.getIdProduk());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        create(model);
                    } catch (SQLException e) {
                        System.out.println("Gagal membuat transaksi untuk produk: " + model.getIdProduk());
                    }
                }
            });
        }

    }

    @Override
    public TransactionModel[] findAll() throws SQLException {

        String querySQL = "SELECT id_produk, harga, quantity FROM transaksi";

        Connection connection = DatabaseUtil.getDataSource().getConnection();
        PreparedStatement pr = connection.prepareStatement(querySQL);

        Vector<TransactionModel> vector = new Vector<TransactionModel>();
        ResultSet resultSet = pr.executeQuery();

        while (resultSet.next()) {
            TransactionModel transactionModel = new TransactionModel();
            transactionModel.setIdProduk(resultSet.getString(1));
            transactionModel.setHarga(resultSet.getInt(2));
            transactionModel.setQuantity(resultSet.getInt(3));

            vector.add(transactionModel);
        }

        TransactionModel[] transactions = new TransactionModel[vector.size()];

        for (int i = 0; i < vector.size(); i++) {
            transactions[i] = vector.get(i);
        }

        return transactions;

    }

    @Override
    public ProductTransactionModel[] findAllProductAndTransactions() throws SQLException {

        String querySQL = "SELECT p.id_produk, nama_produk, stok, harga_beli, harga, quantity FROM produk p\n" +
                "JOIN transaksi t ON p.id_produk = t.id_produk;";

        Connection connection = DatabaseUtil.getDataSource().getConnection();
        PreparedStatement pr = connection.prepareStatement(querySQL);

        try {

            ResultSet resultSet = pr.executeQuery();
            connection.commit();

            Vector<ProductTransactionModel> vector = new Vector<ProductTransactionModel>();

            while (resultSet.next()) {

                ProductTransactionModel p = new ProductTransactionModel();

                String idProduk = resultSet.getString(1);

                p.setIdProduk(idProduk);
                p.setNamaProduk(resultSet.getString(2));
                p.setStok(resultSet.getInt(3));
                p.setHargaBeli(resultSet.getInt(4));
                p.setHarga(resultSet.getInt(5));
                p.setQuantity(resultSet.getInt(6));

                vector.add(p);

            }

            ProductTransactionModel[] productTransactionModels = new ProductTransactionModel[vector.size()];

            for (int i = 0; i < vector.size(); i++) {
                productTransactionModels[i] = vector.get(i);
            }

            return productTransactionModels;

        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException(e);
        } finally {
            pr.close();
            connection.close();
        }

    }

    @Override
    public void truncate() throws SQLException {

        String querySQL = "TRUNCATE transaksi";

        Connection connection = DatabaseUtil.getDataSource().getConnection();
        PreparedStatement pr = connection.prepareStatement(querySQL);

        try {
            pr.execute();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException(e);
        } finally {
            pr.close();
            connection.close();
        }

    }
}
