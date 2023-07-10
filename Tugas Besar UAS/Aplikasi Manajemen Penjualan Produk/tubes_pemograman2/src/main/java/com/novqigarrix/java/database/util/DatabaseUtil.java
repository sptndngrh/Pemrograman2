package com.novqigarrix.java.database.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseUtil {

    private static final HikariDataSource dataSource;

    static {
        String jdbcURL = System.getProperty("JDBC_URL");
        String username = System.getProperty("MYSQL_USERNAME");
        String password = System.getProperty("MYSQL_PASSWORD");

        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl(jdbcURL);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);

        // Konfigurasi connection pool
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(5);
        hikariConfig.setIdleTimeout(60000 * 5);
        hikariConfig.setMaxLifetime(60000 * 30);

        hikariConfig.setAutoCommit(false);

        dataSource = new HikariDataSource(hikariConfig);
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }

}
