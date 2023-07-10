package septiandiN92.praktikumpbo.pertemuan3.buattemen;

import septiandiN92.praktikumpbo.pertemuan3.tugas.Produk;

public class Main {
    public static void main(String[] args) {
        Pakethajiumrah umrah = new Pakethajiumrah();
        Pakethajiumrah umrahplus = new Pakethajiumrah();
        Pakethajiumrah umrahplusplus = new Pakethajiumrah();
        Pakethajiumrah haji = new Pakethajiumrah();
        Pakethajiumrah hajiplus = new Pakethajiumrah();
        Pakethajiumrah hajiplusplus = new Pakethajiumrah();

        umrah.layanan = "Umroh";
        umrah.kelas = "Ekonomi";
        umrah.biaya = 24000000;

        umrahplus.layanan = "Umroh";
        umrahplus.kelas = "+";
        umrahplus.biaya = 32000000;

        umrahplusplus.layanan = "Umroh";
        umrahplusplus.kelas = "++";
        umrahplusplus.biaya = 85000000;

        haji.layanan = "Haji";
        haji.kelas = "Ekonomi";
        haji.biaya = 40000000;

        hajiplus.layanan = "Haji";
        hajiplus.kelas = "+";
        hajiplus.biaya = 85000000;

        hajiplusplus.layanan = "Haji";
        hajiplusplus.kelas = "++";
        hajiplusplus.biaya = 134000000;

        umrah.showProfile();;
        umrahplus.showProfile();;
        umrahplusplus.showProfile();
        haji.showProfile();
        hajiplus.showProfile();
        hajiplusplus.showProfile();
    }
}
