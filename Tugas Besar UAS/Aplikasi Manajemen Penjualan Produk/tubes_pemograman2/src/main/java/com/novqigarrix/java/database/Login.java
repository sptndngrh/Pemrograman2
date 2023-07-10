package com.novqigarrix.java.database;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.novqigarrix.java.database.model.UserModel;
import com.novqigarrix.java.database.repository.UserRepositoryImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Login extends JFrame {
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JPanel mainPanel;
    private JButton toRegisterButton;
    private JButton loginButton;

    private JFrame tampilanRegister;
    private TampilanOwner tampilanOwner;
    private Kasir tampilanKasir;

    private void clearFields() {
        userTextField.setText("");
        passwordField.setText("");
    }

    public Login() {
        super("Create new Account");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 400);

        JFrame tampilanLogin = this;

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        toRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilanLogin.setVisible(false);

                // Clear fields nya
                clearFields();

                tampilanRegister.setVisible(true);
            }
        });

    }

    public void setTampilanRegister(JFrame tampilanRegister) {
        this.tampilanRegister = tampilanRegister;
    }

    private void loginUser() {
        String username = userTextField.getText();
        String password = String.valueOf(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Mohon Isi Form Login dengan benar!",
                    "Validasi Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {

            UserRepositoryImpl repository = new UserRepositoryImpl();
            UserModel user = repository.findByUsername(username);

            // Cari usernya melalui username
            // kalau user nya tidak ada berarti
            // user nya blm daftar
            if(user == null) {
                JOptionPane.showMessageDialog(this,
                        "Username atau Password anda salah!",
                        "Error Message",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            BCrypt.Result passwordMatches = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

            // Bandingkan password yang dari input, dan yang ada di database
            // Jika tidak sama, maka password nya salah
            if(!passwordMatches.verified) {
                JOptionPane.showMessageDialog(this,
                        "Username atau Password anda salah!",
                        "Error Message",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this,
                    "Selamat datang " + user.getNama() + ". Anda Login sebagai " + user.getRole(),
                    "Success Message",
                    JOptionPane.INFORMATION_MESSAGE);

            // Jika user role nya kasir
            // maka tampilkan tampilan kasir
            // jika user role nya owner
            // maka tampilkan tampilan owner

            // Close dulu tampilan login nya
            this.setVisible(false);

            if(user.getRole().equals("KASIR")) {
                tampilanKasir.setVisible(true);
            } else if (user.getRole().equals("OWNER")) {
                tampilanOwner.setVisible(true);
            }

            // Clear fields nya
            clearFields();

        } catch (SQLException e){
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Database Error",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void setTampilanOwner(TampilanOwner tampilanOwner) {
        this.tampilanOwner = tampilanOwner;
    }

    public void setTampilanKasir(Kasir tampilanKasir) {
        this.tampilanKasir = tampilanKasir;
    }

}
