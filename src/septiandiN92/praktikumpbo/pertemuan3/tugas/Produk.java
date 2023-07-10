package septiandiN92.praktikumpbo.pertemuan3.tugas;

public class Produk {
    String nama_produk;
    int harga_produk;
    int pajak_produk_persen;
    String jenis_produk;

    String spasi;

    public Produk() {}

    public void showProfile() {
        System.out.println("nama_produk     : " + nama_produk);
        System.out.println("Harga Produk    : " + harga_produk);
        System.out.println("Pajak Produk    : " + pajak_produk_persen);
        System.out.println("Jenis Produk    : " + jenis_produk);
        System.out.println("=========================================");
    }
}
