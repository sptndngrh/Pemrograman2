package septiandiN92.praktikumpbo.pertemuan4.Tugas1;

public class Daging_ayam extends TokoDaging{
    String type = "Daging_ayam";

    public Daging_ayam(String nama, int harga, int stok) {
        super(nama, harga, stok);
    }
    public void showInfo(){
        System.out.println("Daging Ayam  : " + super.nama);
        System.out.println("Harga        : " + super.harga);
        System.out.println("Stok         : " + super.stok);
        System.out.println();
    }
}
