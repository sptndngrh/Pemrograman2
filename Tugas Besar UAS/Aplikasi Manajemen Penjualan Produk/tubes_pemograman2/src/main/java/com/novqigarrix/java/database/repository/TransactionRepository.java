package com.novqigarrix.java.database.repository;

import com.novqigarrix.java.database.model.ProductTransactionModel;
import com.novqigarrix.java.database.model.TransactionModel;

import java.sql.SQLException;

public interface TransactionRepository {

    TransactionModel create(TransactionModel transaksi) throws SQLException;

    void createMany(TransactionModel[] transactionModels) throws SQLException;

    TransactionModel[] findAll() throws SQLException;

    ProductTransactionModel[] findAllProductAndTransactions() throws SQLException;

    void truncate() throws SQLException;

}
