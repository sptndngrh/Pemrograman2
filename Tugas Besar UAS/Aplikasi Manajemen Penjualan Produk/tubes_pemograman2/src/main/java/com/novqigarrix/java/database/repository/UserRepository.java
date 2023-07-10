package com.novqigarrix.java.database.repository;

import com.novqigarrix.java.database.model.UserModel;

import java.sql.SQLException;

public interface UserRepository {

    UserModel findOne(int userId) throws SQLException;

    UserModel findByUsername(String username) throws SQLException;

    UserModel create(UserModel user) throws SQLException;

    boolean delete(int userId) throws SQLException;

    void truncate() throws SQLException;

}
