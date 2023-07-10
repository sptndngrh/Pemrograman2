package septiandiN92.praktikumpbo.pertemuan7;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class Registration extends JFrame{
    private JPanel mainPanel;
    private JTextField namaLengkap_field;
    private JTextField password_Field;
    private JRadioButton lakiLakiRadioButton;
    private JRadioButton perempuanRadioButton;
    private JComboBox jabatanComboBox;
    private JCheckBox bahasaIndonesiaCheckBox;
    private JCheckBox bahasaInggrisCheckBox;
    private JCheckBox bahasaMandarinCheckBox;
    private JCheckBox lainnyaCheckBox;
    private JTextArea textArea1;
    private JComboBox Bulan_lahir;
    private JSpinner Tanggal_lahir;
    private JSpinner Tahun_lahir;
    private JTextField Tempat_lahir;
    private JSlider Slider;
    private JTextField textField4;
    private JButton submitButton;
    private JLabel bahasa;
    private JLabel nama_Lengkap;
    private JLabel alamat;
    private JLabel jabatan;
    private JLabel ttl;
    private JLabel namaLengkap;
    private JLabel skill;
    private ButtonGroup jenisKelaminGroup;

    public Registration() {
        super("Formulir Registration");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(800, 600);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Mengambil nilai
                String namaLengkap = namaLengkap_field.getText();

                // Mengambil nilai dari radio button
                jenisKelaminGroup.add(lakiLakiRadioButton);
                jenisKelaminGroup.add(perempuanRadioButton);
                String jenisKelamin = "";
                Enumeration<AbstractButton> buttons = jenisKelaminGroup.getElements();

                for (int i = 0; i < jenisKelaminGroup.getButtonCount(); i++) {
                    AbstractButton button = buttons.nextElement();
                    if(button.isSelected())
                        jenisKelamin = button.getText();
                }

                // Mengambil nilai dari ComboBox / Dropdown
                String jabatan = (String) jabatanComboBox.getSelectedItem();

                // Mengambil nilai dari Spinner
                int tahunLahir = (int) Tahun_lahir.getValue();

                // Mengatur nilai min dan max sebuah slider (default: min 0, max 100)
                Slider.setMinimum(50);
                Slider.setMaximum(200);

                // Mengambil nilai dari Slider
                int skill = Slider.getValue();

                //Menampilkan pesan
                JOptionPane.showMessageDialog(null, "Data " + jenisKelamin + " tersimpan!");

            }
        });
    }

    public static void main(String[] args) {
        JFrame mainFrame = new Registration();
        mainFrame.setVisible(true);
    }
}

