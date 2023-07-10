package com.novqigarrix.java.database.repository;

import com.novqigarrix.java.database.model.ProductModel;
import com.novqigarrix.java.database.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public ProductModel[] findAll() throws SQLException {

        String querySQL = "SELECT id_produk, nama_produk, stok, harga_beli, harga_jual FROM produk";

        Connection connection = DatabaseUtil.getDataSource().getConnection();
        PreparedStatement pr = connection.prepareStatement(querySQL);

        try {

            ResultSet rs = pr.executeQuery();
            connection.commit();

            Vector<ProductModel> products = new Vector<ProductModel>();

            while(rs.next()) {
                ProductModel product = new ProductModel();
                product.setIdProduk(rs.getString(1));
                product.setNamaProduk(rs.getString(2));
                product.setStok(rs.getInt(3));
                product.setHargaBeli(rs.getInt(4));
                product.setHargaJual(rs.getInt(5));

                products.add(product);
            }

            ProductModel[] productModels = new ProductModel[products.size()];

            for (int i = 0; i < products.size(); i++) {
                productModels[i] = products.get(i);
            }

            return productModels;

        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            pr.close();
            connection.close();
        }

    }

    @Override
    public ProductModel findOne(String idProduk) throws SQLException {

        String querySQL = "SELECT id_produk, nama_produk, stok, harga_beli, harga_jual FROM produk WHERE id_produk = ?";

        Connection connection = DatabaseUtil.getDataSource().getConnection();
        PreparedStatement pr = connection.prepareStatement(querySQL);

        try {

            pr.setString(1, idProduk);

            ResultSet resultSet = pr.executeQuery();
            connection.commit();

            if(!resultSet.next()) {
                return null;
            }

            ProductModel productModel = new ProductModel();

            productModel.setIdProduk(resultSet.getString(1));
            productModel.setNamaProduk(resultSet.getString(2));
            productModel.setStok(resultSet.getInt(3));
            productModel.setHargaBeli(resultSet.getInt(4));
            productModel.setHargaJual(resultSet.getInt(5));

            return productModel;

        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException(e);
        } finally {
            pr.close();
            connection.close();
        }

    }

    @Override
    public ProductModel create(ProductModel product) throws SQLException {

        String querySQL = "INSERT INTO produk (id_produk, nama_produk, stok, harga_beli, harga_jual) VALUES (?, ?, ?, ?, ?)";

        Connection connection = DatabaseUtil.getDataSource().getConnection();
        PreparedStatement pr = connection.prepareStatement(querySQL);

        try {

            pr.setString(1, product.getIdProduk());
            pr.setString(2, product.getNamaProduk());
            pr.setInt(3, product.getStok());
            pr.setLong(4, product.getHargaBeli());
            pr.setLong(5, product.getHargaJual());

            pr.execute();
            connection.commit();

            return product;

        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException(e);
        } finally {
            pr.close();
            connection.close();
        }

    }

    @Override
    public void createMany(ProductModel[] products) throws SQLException {

        for (ProductModel product : products) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        create(product);
                    } catch (SQLException e) {
                        System.out.println("Failed to save: " + product.getNamaProduk());
                    }
                }
            });

            System.out.println("Sedang membuat produk: " + product.getNamaProduk() + "...");
            thread.start();
        }

    }

    @Override
    public boolean delete(String idProduk) throws SQLException {

        String querySQL = "SELECT id_produk FROM produk WHERE id_produk = ?";

        Connection connection = DatabaseUtil.getDataSource().getConnection();
        PreparedStatement pr = connection.prepareStatement(querySQL);

        try {

            pr.setString(1, idProduk);

            ResultSet resultSet = pr.executeQuery();

            if(!resultSet.next()) {
                return false;
            }

            String deleteSQL = "DELETE FROM produk WHERE id_produk = ?";

            PreparedStatement deletePr = connection.prepareStatement(deleteSQL);
            deletePr.setString(1, idProduk);

            deletePr.execute();
            connection.commit();

            return true;

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

        String querySQL = "TRUNCATE produk";

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
