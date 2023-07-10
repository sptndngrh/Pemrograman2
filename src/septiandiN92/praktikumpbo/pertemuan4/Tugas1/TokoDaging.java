package septiandiN92.praktikumpbo.pertemuan4.Tugas1;

public class TokoDaging {
    String nama;
    int harga;
    int stok;

    public TokoDaging(String nama, int harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public void showInfo() {
        System.out.println("Daging : " + this.nama);
        System.out.println("Harga: " + this.harga);
        System.out.println("Stok : " + this.stok);
        System.out.println();
    }
}
