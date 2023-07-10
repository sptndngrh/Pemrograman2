package septiandiN92.praktikumpbo.pertemuan4.Tugas;

public class Minuman extends RumahMakanSunda{
    String type = "Minuman";

    public Minuman(String nama, int harga, int stok) {
        super(nama, harga, stok);
    }
    public void showInfo(){
        System.out.println("Minuman      : " + super.nama);
        System.out.println("Harga        : " + super.harga);
        System.out.println("Stok         : " + super.stok);
        System.out.println();
    }
}
