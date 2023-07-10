package com.novqigarrix.java.database.repository;

import com.novqigarrix.java.database.model.UserModel;
import com.novqigarrix.java.database.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public UserModel findOne(int userId) throws SQLException {
        String querySQL = "SELECT id_user, username, nama, password, role FROM user WHERE id_user = ?";

        Connection connection = DatabaseUtil.getDataSource().getConnection();
        PreparedStatement pr = connection.prepareStatement(querySQL);

        try {
            pr.setInt(userId, 1);

            ResultSet resultSet = pr.executeQuery();
            connection.commit();

            if(!resultSet.next()) {
                return null;
            }

            UserModel userModel = new UserModel();

            userModel.setUserId(resultSet.getInt(1));
            userModel.setUsername(resultSet.getString(2));
            userModel.setNama(resultSet.getString(3));
            userModel.setPassword(resultSet.getString(4));
            userModel.setRole(resultSet.getString(5));

            return userModel;

        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException(e);
        } finally {
            pr.close();
            connection.close();
        }

    }

    @Override
    public UserModel findByUsername(String username) throws SQLException {
        String querySQL = "SELECT id_user, username, nama, password, role FROM user WHERE username = ?";

        Connection connection = DatabaseUtil.getDataSource().getConnection();
        PreparedStatement pr = connection.prepareStatement(querySQL);

        try {
            pr.setString(1, username);

            ResultSet resultSet = pr.executeQuery();
            connection.commit();

            if(!resultSet.next()) {
                return null;
            }

            UserModel userModel = new UserModel();

            userModel.setUserId(resultSet.getInt(1));
            userModel.setUsername(resultSet.getString(2));
            userModel.setNama(resultSet.getString(3));
            userModel.setPassword(resultSet.getString(4));
            userModel.setRole(resultSet.getString(5));

            return userModel;

        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException(e);
        } finally {
            pr.close();
            connection.close();
        }

    }

    public UserModel create(UserModel user) throws SQLException {

        String querySQL = "INSERT INTO user (username, nama, password, role) VALUES (?, ?, ?, ?)";

        Connection connection = DatabaseUtil.getDataSource().getConnection();
        PreparedStatement pr = connection.prepareStatement(querySQL, PreparedStatement.RETURN_GENERATED_KEYS);

        try {

            pr.setString(1, user.getUsername());
            pr.setString(2, user.getNama());
            pr.setString(3, user.getPassword());
            pr.setString(4, user.getRole());

            pr.execute();

            ResultSet resultSet = pr.getGeneratedKeys();
            connection.commit();

            if(!resultSet.next()) {
                return null;
            }

            user.setUserId(resultSet.getInt(1));
            return user;

        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException(e);
        } finally {
            pr.close();
            connection.close();
        }

    }

    @Override
    public boolean delete(int userId) throws SQLException {

        String querySQL = "DELETE FROM user WHERE id_user = ?";

        Connection connection = DatabaseUtil.getDataSource().getConnection();
        PreparedStatement pr = connection.prepareStatement(querySQL);

        try {

            pr.setInt(1, userId);

            boolean isSucces = pr.execute();
            connection.commit();

            return isSucces;

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

        String querySQL = "TRUNCATE user";

        Connection connection = DatabaseUtil.getDataSource().getConnection();
        PreparedStatement pr = connection.prepareStatement(querySQL);

        try {

            pr.execute();
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            pr.close();
            connection.close();
        }

    }

}