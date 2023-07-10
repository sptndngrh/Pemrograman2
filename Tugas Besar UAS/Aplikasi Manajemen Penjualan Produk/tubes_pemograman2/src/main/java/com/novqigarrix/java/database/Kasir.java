package com.novqigarrix.java.database;

import com.novqigarrix.java.database.model.ProductModel;
import com.novqigarrix.java.database.model.ProductTransactionModel;
import com.novqigarrix.java.database.model.TransactionModel;
import com.novqigarrix.java.database.repository.ProductRepositoryImpl;
import com.novqigarrix.java.database.repository.TransactionRepositoryImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Kasir extends JFrame {
    private JPanel panelMain;
    private JTabbedPane tabbedPane1;
    private JTextField idField;
    private JTextField quantityField;
    private JTextField totalHargaField;
    private JTextField uangMasukField;
    private JTextField totalkembalianField;
    private JButton simpanButton;
    private JTable tabelProduk;
    private JButton inputUangMasukButton;
    private JButton checkTotalHargaButton;
    private JButton tambahProdukButton;
    private JButton logoutButton;

    private Login tampilanLogin;

    private DefaultTableModel tabelModel;
    private DefaultTableModel kasirTabelModel;
    private final ProductRepositoryImpl productRepository;
    private final TransactionRepositoryImpl transactionRepository;

    private final Vector<ProductTransactionModel> productsVector;

    public Kasir(DefaultTableModel tabelModel) {
        super("Tampilan Kasir");
        this.tabelModel = tabelModel;

        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600,600);

        // Inisialisasi produk repository
        productRepository = new ProductRepositoryImpl();

        // Inisialisasi transaksi repository
        transactionRepository = new TransactionRepositoryImpl();

        productsVector = new Vector<ProductTransactionModel>();

        quantityField.setText("1");

        checkTotalHargaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String idProduk = idField.getText();
                String quantityInString = quantityField.getText();

                if(idProduk.isEmpty() || quantityInString.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Silahkan isi seluruh field!",
                            "Validation Error",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                try {

                    ProductModel produk = productRepository.findOne(idProduk);

                    if(produk == null) {
                        JOptionPane.showMessageDialog(null,
                                "Produk tidak ditemukan. Pastikan ID yang kamu masukkan sudah benar!",
                                "Error Message",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        int quantity = Integer.parseInt(quantityInString);

                        // harga jual * quantiy = total harga sesuai quantity
                        int totalHarga = produk.getHargaJual() * quantity;

                        // Set total harga nya di GUI
                        totalHargaField.setText("Rp" + totalHarga);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Jumlah quantity tidak valid",
                                "Error Message",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException sqlException) {
                    JOptionPane.showMessageDialog(null,
                            sqlException.getMessage(),
                            "Database Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        tambahProdukButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String idProduk = idField.getText();
                String quantityInString = quantityField.getText();

                if(idProduk.isEmpty() || quantityInString.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Silahkan isi seluruh field!",
                            "Validation Error",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                try {

                    ProductModel produk = productRepository.findOne(idProduk);

                    if(produk == null) {
                        JOptionPane.showMessageDialog(null,
                                "Produk tidak ditemukan. Pastikan ID yang kamu masukkan sudah benar!",
                                "Database Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {

                        int quantity = Integer.parseInt(quantityInString);

                        // harga jual * quantiy = total harga sesuai quantity
                        int totalHarga = produk.getHargaJual() * quantity;

                        ProductTransactionModel productTransactionModel = new ProductTransactionModel();
                        productTransactionModel.setIdProduk(produk.getIdProduk());
                        productTransactionModel.setHarga(produk.getHargaJual());
                        productTransactionModel.setQuantity(quantity);
                        productTransactionModel.setNamaProduk(produk.getNamaProduk());
                        productTransactionModel.setHarga(totalHarga);

                        productsVector.add(productTransactionModel);

                        Object[] row = {
                                productTransactionModel.getIdProduk(),
                                productTransactionModel.getNamaProduk(),
                                "Rp" + productTransactionModel.getHarga(),
                                productTransactionModel.getQuantity()
                        };

                        kasirTabelModel.addRow(row);
                        clearProdukForm();

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Jumlah quantity tidak valid",
                                "Error Message",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException sqlException) {
                    JOptionPane.showMessageDialog(null,
                            sqlException.getMessage(),
                            "Database Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        inputUangMasukButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String uangMasukInString = uangMasukField.getText();
                if(uangMasukInString.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Silahkan masukkan berapa uang anda terima dari kustomer!",
                            "Database Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int uangMasuk = Integer.parseInt(uangMasukInString);

                int totalHargaSemuaProduk = 0;

                for (ProductTransactionModel produk : productsVector) {
                    totalHargaSemuaProduk += produk.getHarga();
                }

                int totalKembalian = uangMasuk - totalHargaSemuaProduk;
                if(totalKembalian < 0) {
                    JOptionPane.showMessageDialog(null,
                            "Uang anda tidak cukup!",
                            "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                totalkembalianField.setText("Rp" + totalKembalian);

            }
        });

        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check apakah uang masuk sudah di set
                String uangMasukInString = uangMasukField.getText();
                if(uangMasukInString.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Uang masuk harus di set terlebih dahulu!",
                            "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int uangMasuk = Integer.parseInt(uangMasukInString);

                int totalHargaSemuaProduk = 0;

                for (ProductTransactionModel produk : productsVector) {
                    totalHargaSemuaProduk += produk.getHarga();
                }

                int totalKembalian = uangMasuk - totalHargaSemuaProduk;
                if(totalKembalian < 0) {
                    JOptionPane.showMessageDialog(null,
                            "Uang anda tidak cukup!",
                            "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Semaphore semaphore = new Semaphore(productsVector.size());

                for (ProductTransactionModel produk : productsVector) {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {

                                semaphore.acquire();

                                // Get data produk berdasarkan id produk
                                ProductModel productModel = productRepository.findOne(produk.getIdProduk());
                                if(productModel == null) return;

                                TransactionModel transactionModel = new TransactionModel();
                                transactionModel.setIdProduk(productModel.getIdProduk());
                                transactionModel.setQuantity(produk.getQuantity());
                                transactionModel.setHarga(productModel.getHargaJual());

                                transactionRepository.create(transactionModel);
                                System.out.println("Berhasil menyimpan transaksi dengan Id Produk: " + produk.getIdProduk());

                                Object[] row = {
                                        produk.getIdProduk(),
                                        produk.getNamaProduk(),
                                        productModel.getStok() - transactionModel.getQuantity(),
                                        "Rp" + productModel.getHargaBeli(),
                                        "Rp" + transactionModel.getHarga()
                                };

                                tabelModel.addRow(row);

                            } catch (SQLException ex) {
                                System.out.println("Original Err Message: " + ex.getMessage());
                                System.out.println("Gagal menyimpan data produk: " + produk.getNamaProduk());
                            } catch (InterruptedException ex) {
                                System.out.println("Semaphore Gagal!!");
                                System.out.println(ex.getMessage());
                            } finally {
                                semaphore.release();
                            }

                        }
                    });

                    thread.start();
                }

                JOptionPane.showMessageDialog(null,
                        "Data transaksi telah disimpan!",
                        "Success Message",
                        JOptionPane.INFORMATION_MESSAGE);

                clearProdukForm();

                for (int i = kasirTabelModel.getRowCount() - 1; i >= 0; i--) {
                    kasirTabelModel.removeRow(i);
                }

                uangMasukField.setText("");
                totalkembalianField.setText("");

            }
        });

        JFrame tampilanKasir = this;

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilanKasir.setVisible(false);
                tampilanLogin.setVisible(true);
            }
        });
    }

    private void createUIComponents() {
        // Load produk ke tabel

        kasirTabelModel = new DefaultTableModel();

        kasirTabelModel.addColumn("ID PRODUK");
        kasirTabelModel.addColumn("NAMA PRODUK");
        kasirTabelModel.addColumn("HARGA");
        kasirTabelModel.addColumn("QUANTITY");

        tabelProduk = new JTable(kasirTabelModel);

    }

    private void clearProdukForm() {
        idField.setText("");
        quantityField.setText("1");
        totalHargaField.setText("");
    }

    public void setTampilanLogin(Login tampilanLogin) {
        this.tampilanLogin = tampilanLogin;
    }

    public void setTabelModel(DefaultTableModel model) {
        this.tabelModel = model;
    }

}
