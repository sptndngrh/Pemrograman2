package com.novqigarrix.java.database;

import com.novqigarrix.java.database.util.EnvProperty;

import javax.swing.table.DefaultTableModel;
import java.util.Objects;

/**
 * Kelompok 1
 * - Novri Anto | 21104065
 * - Fauzaan Hafidz Amar D | 21104038
 * - Tegar Dwi Leksono | 21104051
 * - Septiandi Nugraha | 21104060
 */
public class App {

    static {
        System.setProperty("JDBC_URL", Objects.requireNonNull(EnvProperty.getProperty("JDBC_URL")));
        System.setProperty("MYSQL_USERNAME", Objects.requireNonNull(EnvProperty.getProperty("MYSQL_USERNAME")));
        System.setProperty("MYSQL_PASSWORD", Objects.requireNonNull(EnvProperty.getProperty("MYSQL_PASSWORD")));
    }

    public static void main(String[] args)
    {

        DefaultTableModel tabelModel = new DefaultTableModel();

        Login tampilanLogin = new Login();
        Register tampilanRegister = new Register();

        Kasir tampilanKasir = new Kasir(tabelModel);
        TampilanOwner tampilanOwner = new TampilanOwner(tabelModel);

        tampilanRegister.setTampilanLogin(tampilanLogin);

        tampilanLogin.setTampilanOwner(tampilanOwner);
        tampilanLogin.setTampilanKasir(tampilanKasir);
        tampilanLogin.setTampilanRegister(tampilanRegister);

        tampilanOwner.setTampilanLogin(tampilanLogin);
        tampilanKasir.setTampilanLogin(tampilanLogin);

        tampilanRegister.setVisible(true);

    }
}
