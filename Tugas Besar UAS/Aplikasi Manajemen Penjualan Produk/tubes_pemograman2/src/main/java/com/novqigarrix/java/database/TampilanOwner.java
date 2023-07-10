package com.novqigarrix.java.database;

import com.novqigarrix.java.database.model.ProductTransactionModel;
import com.novqigarrix.java.database.repository.TransactionRepositoryImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TampilanOwner extends JFrame {

    private JPanel mainPanel;
    private JTable mainTable;
    private JButton logoutButton;
    private Login tampilanLogin;

    private DefaultTableModel tabelModel;

    TampilanOwner(DefaultTableModel tabelModel) {
        super("Tampilan Owner");
        this.tabelModel = tabelModel;

        this.setContentPane(mainPanel);
        this.setSize(600, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JFrame tampilanOwner = this;

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tampilanOwner.setVisible(false);
                tampilanLogin.setVisible(true);

            }
        });

    }

    private void createUIComponents() {
        tabelModel.addColumn("ID PRODUK");
        tabelModel.addColumn("NAMA PRODUK");
        tabelModel.addColumn("STOCK");
        tabelModel.addColumn("HARGA BELI");
        tabelModel.addColumn("HARGA JUAL");

        TransactionRepositoryImpl repo = new TransactionRepositoryImpl();

        try {

            ProductTransactionModel[] dataTransaksi = repo.findAllProductAndTransactions();
            for (ProductTransactionModel data : dataTransaksi) {
                Object[] row = {
                        data.getIdProduk(),
                        data.getNamaProduk(),
                        data.getStok(),
                        "Rp" + data.getHargaBeli(),
                        "Rp" + data.getHarga()
                };
                tabelModel.addRow(row);
            }

            mainTable = new JTable(tabelModel);

        } catch (SQLException e) {
            System.out.println("Terjadi error saat mengambil data transaksi di database!");
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void setTabelModel(DefaultTableModel model) {
        this.tabelModel = model;
    }

    public void setTampilanLogin(Login tampilanLogin) {
        this.tampilanLogin = tampilanLogin;
    }
}
