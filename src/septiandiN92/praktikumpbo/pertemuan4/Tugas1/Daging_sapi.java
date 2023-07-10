package septiandiN92.praktikumpbo.pertemuan4.Tugas1;

public class Daging_sapi extends TokoDaging{
    String type = "Daging_sapi";

    public Daging_sapi(String nama, int harga, int stok){
        super(nama, harga, stok);
    }
    public void showInfo() {
        System.out.println("Daging Sapi  : " + super.nama);
        System.out.println("Harga        : " + super.harga);
        System.out.println("Stok         : " + this.type);
        System.out.println();
    }
}
