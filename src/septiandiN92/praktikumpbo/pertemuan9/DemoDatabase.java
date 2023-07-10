package septiandiN92.praktikumpbo.pertemuan9;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DemoDatabase extends JFrame{
    private JTabbedPane tabbedPane;
    private JPanel mainPanel;
    private JPanel addDatatab;
    private JPanel seeDatatab;
    private JTextField namaLengkaptextField;
    private JTextField NIMtextField;
    private JSpinner nilaiSpinner;
    private JButton clearButton1;
    private JTable dataTable;
    private JButton submitButton1;
    private JButton clearButton;
    private JButton deleteButton;
    private JLabel namaLengkapLabel;
    private JLabel NIMLabel;
    private JLabel nilaiLabel;
    private JButton submitButton;
    private DefaultTableModel model;
    private static Connection c;
    private static Statement s;
    private static ResultSet rs;

    public DemoDatabase() {
        super("Demo Database");

        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400, 400);

        // Apa yang terjadi jika tombol SUBMIT ditekan
        submitButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Variable untuk penyimpanan nilai inputan user
                String nim = NIMtextField.getText();
                String nama = namaLengkaptextField.getText();
                int nilai = (int) nilaiSpinner.getValue();

                if (!nim.equals("") && !nama.equals("") && nilai != 0)
                try {
                    // Buka akses Database
                    openDb();

                    // Query INSERT untuk menambahkan data ke tabel di Database
                    s.executeUpdate("INSERT INTO mahasiswa VALUES ('" + nim + "', '" + nama + "', '" + nilai + "')");

                    // Untuk nambahin data ke tabel di aplikasi
                    Object[] row = { nama, nim, nilai };
                    model.addRow(row);

                    // Tampilkan peringatan bahwa penambahan data berhasil
                    JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
                }

                catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                finally {
                    closedDb();
                }

                // Jika ada form yang kosong, tampilkan pesan popup
                else {
                    JOptionPane.showMessageDialog(null, "Mohon isi semua form terlebih dahulu sebelum menyimpan data");
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                namaLengkaptextField.setText("");
                NIMtextField.setText("");
                nilaiSpinner.setValue(0);
                JOptionPane.showMessageDialog(null, "Form cleared!");
            }
        });

        // Menambahkan ActionListener ke JButton
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus semua data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        openDb();
                        s.executeUpdate("DELETE FROM mahasiswa");
                        model.setRowCount(0);

                        JOptionPane.showMessageDialog(null, "Semua data berhasil dihapus!");
                    }

                    catch (SQLException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }

                    finally {
                        closedDb();
                    }
                }
            }
        });

    }

    public static void main(String[] args) {
        JFrame mainFrame = new DemoDatabase();
        mainFrame.setVisible(true);
    }

    /** Method untuk membuka koneksi ke database **/
    private static void openDb() throws ClassNotFoundException, SQLException {
        String URL = "jdbc:mysql://localhost:8080/dbdemo";
        String USERNAME = "root";
        String PASSWORD = "";

        Class.forName("com.mysql.cj.jdbc.Driver");
        c = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        s = c.createStatement();
    }

    /** Method untuk penutup akses ke Database **/
    private static void closedDb() {
        try {
            rs.close();
            s.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createUIComponents() throws SQLException {
        // Mengatur model tabel
        model = new DefaultTableModel();
        dataTable = new JTable(model);

        // Mengatur kolom
        model.addColumn("Nama");
        model.addColumn("NIM");
        model.addColumn("Nilai");

        // Ambil nilai untuk dimasukkan dalam tabel
        try {

            // Buka akses Database
            openDb();

            // Query untuk mengambil nilai
            rs = s.executeQuery("SELECT * FROM mahasiswa");

            // Jabarkan data yang kita peroleh, lalu masukkan ke dalam tabel di dalam aplikasi
            while (rs.next()) {
                Object[] row = {
                        rs.getString("nama"),
                        rs.getString("nim"),
                        rs.getInt("nilai")
                };
                model.addRow(row);
            }
        }

        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        finally {
            closedDb();
        }
    }
}
