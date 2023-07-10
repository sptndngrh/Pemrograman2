package septiandiN92.praktikumpbo.pertemuan4.Tugas;

public class Menu extends RumahMakanSunda{
    String type = "Menu";

    public Menu(String nama, int harga, int stok){
        super(nama, harga, stok);
    }
    public void showInfo() {
        System.out.println("Menu Masakan : " + super.nama);
        System.out.println("Harga        : " + super.harga);
        System.out.println("Stok         : " + this.type);
        System.out.println();
    }
}
