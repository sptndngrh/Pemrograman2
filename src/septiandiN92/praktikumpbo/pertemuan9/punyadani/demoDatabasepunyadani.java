package septiandiN92.praktikumpbo.pertemuan9.punyadani;

import com.mysql.cj.xdevapi.Table;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class demoDatabasepunyadani  extends JFrame{
    private JTabbedPane tabbedPane;
    private JPanel mainPanel;
    private JPanel addTab;
    private JPanel seeDataTab;
    private JTextField namaLengkapTextField;
    private JTextField nimTextField;
    private JSpinner nilaiSpinner;
    private JButton submitButton;
    private JTable dataTable;
    private JButton editButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JLabel namaLengkapLabel;
    private JLabel NIMLabel;
    private JLabel nilaiLabel;
    private JButton clearButton1;
    private DefaultTableModel model;

    private static Connection c;
    private static Statement s;
    private static ResultSet rs;

    public demoDatabasepunyadani(){
        super("Demo Database");

        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400, 400);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim = nimTextField.getText();
                String nama = namaLengkapTextField.getText();
                int nilai = (int) nilaiSpinner.getValue();

                try {
                    if (nim.isEmpty() || nama.isEmpty() || nilai == 0) {
                        JOptionPane.showMessageDialog(null, "Field Harus Diisi Semua!");
                    }
                    else {
                        openDb();
                        s.executeUpdate(
                                "INSERT INTO mahasiswa VALUES ('" + nama + "','" + nim + "'," + nilai + " )"
                        );

                        Object[] row = {nama, nim, nilai};
                        model.addRow(row);
                        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
                    }
                } catch (SQLException | ClassNotFoundException ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,ex.getLocalizedMessage());
                } finally {
                    closeDb();
                }
            }
        });
        clearButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                namaLengkapTextField.setText("");
                nimTextField.setText("");
                nilaiSpinner.setValue(0);
                JOptionPane.showMessageDialog(null, "Form Cleared!");
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openDb();
                    s.executeUpdate(
                            "DELETE FROM mahasiswa" );
                    DefaultTableModel tableModel = (DefaultTableModel) dataTable.getModel();
                    tableModel.setRowCount(0);

                    JOptionPane.showMessageDialog(null, "Semua Data Telah Dihapus");
                } catch (SQLException | ClassNotFoundException ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,ex.getLocalizedMessage());
                } finally {
                    closeDb();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // User disuruh untuk memasukkan NIM yang datanya nanti akan dihapus
                String pk = JOptionPane.showInputDialog(null, "Masukkan NIM yang akan dihapus:", "Hapus Data", JOptionPane.QUESTION_MESSAGE);
                if (pk != null) {
                    try {
                        openDb();
                        // Eksekusi Query SQL untuk menghapus data sesuai dengan nim yang telah dimasukkan
                        PreparedStatement pst = c.prepareStatement("DELETE FROM mahasiswa WHERE nim = ?");
                        pst.setString(1, pk);
                        pst.executeUpdate();

                        // Mengupdate Tabel Mahasiswa
                        s.executeQuery("SELECT * FROM mahasiswa");
                        ResultSet rs = s.getResultSet();

                        // Menampilkan Tabel Baru yang Datanya Sudah Berubah
                        model.setRowCount(0);  // clear the table model
                        while (rs.next()) {
                            String nim = rs.getString("nim");
                            String nama = rs.getString("nama");
                            int nilai = rs.getInt("nilai");
                            Object[] row = {nama, nim, nilai};
                            model.addRow(row);
                        }
                        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                    } catch (SQLException | ClassNotFoundException ex){
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null,ex.getLocalizedMessage());
                    } finally {
                        closeDb();
                    }
                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mendapatkan indeks tabel yang sedang dipilih oleh User
                int rowIndex = dataTable.getSelectedRow();
                int colIndex = dataTable.getSelectedColumn();
                if (rowIndex == -1 || colIndex == -1) {
                    // Jika tidak ada cells yang dipilih maka program akan menampilkan error
                    JOptionPane.showMessageDialog(null, "Pilih salah satu baris untuk mengubah data!");
                } else {
                    // Mendapatkan Value dari Cell yang dipilih
                    Object value = dataTable.getValueAt(rowIndex, colIndex);
                    // Message untuk memasukkan data baru
                    String newValue = JOptionPane.showInputDialog(null, "Masukkan nilai baru:", "Edit Data", JOptionPane.QUESTION_MESSAGE, null, null, value).toString();
                    if (newValue != null) {
                        String nim = dataTable.getValueAt(rowIndex, 1).toString();
                        try {
                            openDb();
                            // Query SQL untuk mengubah data dalam Tabel
                            PreparedStatement pst = c.prepareStatement("UPDATE mahasiswa SET nama = ?, nilai = ? WHERE nim = ?");
                            pst.setString(1, newValue);  // set string nama
                            pst.setInt(2, Integer.parseInt(newValue));  // set int nilai
                            pst.setString(3, nim);  // set string nim sebagai kondisi
                            pst.executeUpdate();

                            // Update data baru dalam tabel
                            dataTable.setValueAt(newValue, rowIndex, colIndex);
                            JOptionPane.showMessageDialog(null, "Data Telah Diubah");
                        } catch (SQLException | ClassNotFoundException ex){
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
                        } finally {
                            closeDb();
                        }
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame mainFrame = new demoDatabasepunyadani();
        mainFrame.setVisible(true);
    }

    private static void openDb() throws SQLException, ClassNotFoundException {
        String URL = "jdbc:mysql://localhost:3306/db_demo";
        String USERNAME = "root";
        String PASSWORD = "";

        Class.forName("com.mysql.cj.jdbc.Driver");
        c = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        s = c.createStatement();
    }
    private static void closeDb() {
        try {
            rs.close();
            s.close();
            c.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void createUIComponents() {
        model = new DefaultTableModel();
        dataTable = new JTable(model);
        model.addColumn("Nama");
        model.addColumn("NIM");
        model.addColumn("Nilai");

        try {
            openDb();
            rs = s.executeQuery("SELECT * FROM mahasiswa");

            while (rs.next()){
                Object[] row = {
                        rs.getString("nama"),
                        rs.getString("nim"),
                        rs.getInt("nilai")
                };
                model.addRow(row);
            }
        } catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        } finally {
            closeDb();
        }
    }

}

