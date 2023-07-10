package com.novqigarrix.java.database;

import com.novqigarrix.java.database.model.UserModel;
import com.novqigarrix.java.database.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class UserRepositoryImplTest {

    static {
        System.setProperty("JDBC_URL", "jdbc:mysql://localhost:3306/tubes_pemograman2_test");
        System.setProperty("MYSQL_USERNAME", "root");
        System.setProperty("MYSQL_PASSWORD", "mysql");
    }

    @Test
    void testCreateUser() throws SQLException {

        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        userRepository.truncate();

        UserModel user = new UserModel();

        user.setUsername("novqigarrix");
        user.setNama("NovqiGarrix");
        user.setPassword("novqigarrix");
        user.setRole("KASIR");

        UserModel createdUser = userRepository.create(user);

        Assertions.assertEquals(1, createdUser.getUserId());
        Assertions.assertEquals(user.getUsername(), createdUser.getUsername());
        Assertions.assertEquals(user.getNama(), createdUser.getNama());
        Assertions.assertEquals(user.getPassword(), createdUser.getPassword());
        Assertions.assertEquals(user.getRole(), createdUser.getRole());

    }

    @Test
    void testFindUser() throws SQLException {

        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        userRepository.truncate();

        UserModel user = new UserModel();

        user.setUsername("novqigarrix");
        user.setNama("NovqiGarrix");
        user.setPassword("novqigarrix");
        user.setRole("KASIR");

        UserModel createdUser = userRepository.create(user);

        UserModel foundedUser = userRepository.findOne(createdUser.getUserId());

        Assertions.assertEquals(createdUser.getUserId(), foundedUser.getUserId());
        Assertions.assertEquals(createdUser.getUsername(), foundedUser.getUsername());
        Assertions.assertEquals(createdUser.getNama(), foundedUser.getNama());
        Assertions.assertEquals(createdUser.getPassword(), foundedUser.getPassword());
        Assertions.assertEquals(createdUser.getRole(), foundedUser.getRole());

    }

    @Test
    void testDeleteUser() throws SQLException {

        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        userRepository.truncate();

        UserModel user = new UserModel();

        user.setUsername("novqigarrix");
        user.setNama("NovqiGarrix");
        user.setPassword("novqigarrix");
        user.setRole("KASIR");

        UserModel createdUser = userRepository.create(user);

        userRepository.delete(user.getUserId());

        UserModel nulledUser = userRepository.findOne(user.getUserId());

        Assertions.assertNull(nulledUser);

    }

}
