package com.novqigarrix.java.database;

import com.novqigarrix.java.database.util.DatabaseUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceTest {

    static {
        System.setProperty("JDBC_URL", "jdbc:mysql://localhost:3306/tubes_pemograman2_test");
        System.setProperty("MYSQL_USERNAME", "root");
        System.setProperty("MYSQL_PASSWORD", "mysql");
    }

    @Test
    void testGetDataSource() {
        DatabaseUtil.getDataSource();
    }

    @Test
    void testGetConnection() {
        try {
            HikariDataSource hikariDataSource = DatabaseUtil.getDataSource();
            Connection conn = hikariDataSource.getConnection();

            System.out.println("Sukses konek ke database");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
