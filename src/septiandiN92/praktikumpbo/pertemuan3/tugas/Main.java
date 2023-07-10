package septiandiN92.praktikumpbo.pertemuan3.tugas;

public class Main {
    public static void main(String[] args) {
        Produk beras = new Produk();
        Produk susu = new Produk();
        Produk minyak = new Produk();
        Produk mie = new Produk();
        Produk daging_ayam = new Produk();
        Produk daging_sapi = new Produk();
        Produk telur_ayam = new Produk();
        Produk telur_bebek = new Produk();

        beras.nama_produk = "Beras Pandan Wangi 1Kg";
        beras.harga_produk = 11500;
        beras.pajak_produk_persen = 11;
        beras.jenis_produk = "Sembako";

        susu.nama_produk = "Susu DanCow 500gr";
        susu.harga_produk = 45500;
        susu.pajak_produk_persen = 11;
        susu.jenis_produk = "Sembako";

        minyak.nama_produk = "MinyakKita 1L";
        minyak.harga_produk = 14000;
        minyak.pajak_produk_persen = 11;
        minyak.jenis_produk = "Sembako";

        mie.nama_produk = "Indomie Special";
        mie.harga_produk = 2800;
        mie.pajak_produk_persen = 11;
        mie.jenis_produk = "Sembako";

        daging_ayam.nama_produk = "Daging Ayam";
        daging_ayam.harga_produk = 32000;
        daging_ayam.pajak_produk_persen = 11;
        daging_ayam.jenis_produk = "Sembako";

        daging_sapi.nama_produk = "Daging Sapi";
        daging_sapi.harga_produk = 132000;
        daging_sapi.pajak_produk_persen = 11;
        daging_sapi.jenis_produk = "Sembako";

        telur_ayam.nama_produk = "Telur Ayam";
        telur_ayam.harga_produk = 26000;
        telur_ayam.pajak_produk_persen = 11;
        telur_ayam.jenis_produk = "Sembako";

        telur_bebek.nama_produk = "Telur Bebek";
        telur_bebek.harga_produk = 34000;
        telur_bebek.pajak_produk_persen = 11;
        telur_bebek.jenis_produk = "Sembako";

        beras.showProfile();
        susu.showProfile();
        minyak.showProfile();
        mie.showProfile();
        daging_ayam.showProfile();
        daging_sapi.showProfile();
        telur_ayam.showProfile();
        telur_bebek.showProfile();
    }
}
