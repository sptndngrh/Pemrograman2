package com.novqigarrix.java.database;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.novqigarrix.java.database.model.UserModel;
import com.novqigarrix.java.database.repository.UserRepositoryImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Register extends JFrame {
    private JTextField namalengkapTextField;
    private JTextField userTextField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JComboBox rolecomboBox;
    private JButton registerButton;
    private JPanel mainPannel;
    private JButton toLoginButton;

    private Login tampilanLogin;

    private void clearFields() {
        // Clear fields nya
        namalengkapTextField.setText("");
        userTextField.setText("");
        passwordField1.setText("");
        passwordField2.setText("");
        rolecomboBox.setSelectedIndex(0);
    }

    public Register() {
        super("Create new Account");
        this.setContentPane(mainPannel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 400);

        JFrame tampilanRegister = this;

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        toLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close tampilan Register
                tampilanRegister.setVisible(false);

                // Clear fields nya
                clearFields();

                // Munculkan tampilan login
                tampilanLogin.setVisible(true);
            }
        });
    }

    private void registerUser() {
        String nama = namalengkapTextField.getText();
        String username = userTextField.getText();
        String password = String.valueOf(passwordField1.getPassword());
        String confirmationPassword = String.valueOf(passwordField2.getPassword());
        Object role = rolecomboBox.getSelectedItem();

        if (nama.isEmpty() || username.isEmpty() || password.isEmpty() || confirmationPassword.isEmpty() || role == null) {
            JOptionPane.showMessageDialog(this,
                    "Mohon isi data diri dengan benar!",
                    "Validasi Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(role.equals("Chose your Role")) {
            JOptionPane.showMessageDialog(this,
                    "Role tidak valid!",
                    "Validasi Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmationPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Pastikan Password sesuai",
                    "Validasi Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {

            UserRepositoryImpl repository = new UserRepositoryImpl();

            // Lakukan pengecheckan kalau user dengan username tersebut
            // sudah ada atau belum
            UserModel existedUser = repository.findByUsername(username);
            if(existedUser != null){
                JOptionPane.showMessageDialog(this,
                        "Akun dengan username tersebut sudah terdaftar. Gunakan username lain!",
                        "Error Message",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());

            UserModel userModel = new UserModel();
            userModel.setNama(nama);
            userModel.setUsername(username);
            userModel.setPassword(hashedPassword);
            userModel.setRole(role.toString());

            repository.create(userModel);

            JOptionPane.showMessageDialog(this,
                    "Akun anda berhasil didaftar. Silahkan Login untuk Melanjutkan!",
                    "Success Message",
                    JOptionPane.INFORMATION_MESSAGE);

            this.setVisible(false);
            clearFields();

            tampilanLogin.setVisible(true);

        } catch (SQLException e){
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Database Error",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void setTampilanLogin(Login tampilanLogin) {
        this.tampilanLogin = tampilanLogin;
    }

}
