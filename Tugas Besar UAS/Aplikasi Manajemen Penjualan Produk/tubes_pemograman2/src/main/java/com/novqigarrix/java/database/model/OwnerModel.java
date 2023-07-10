package com.novqigarrix.java.database.model;

import com.novqigarrix.java.database.repository.UserRepositoryImpl;

import java.sql.SQLException;

public class OwnerModel extends UserModel {

    boolean pecatKasir(int kasirId) throws SQLException {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();

        UserModel kasir = userRepository.findOne(kasirId);

        if(kasir == null) {
            return false;
        }

        return userRepository.delete(kasirId);
    }

    @Override
    public String toString() {
        System.out.println("Saya adalah Owner!");
        return "\nId: " + getUserId() +
                "\n Nama: " + getNama() +
                "\n Role: " + getRole();
    }

}
