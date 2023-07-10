package septiandiN92.praktikumpbo.pertemuan4;

public class Kendaraan {
    String nama;
    int jmlRoda;

    // Constructor
    public Kendaraan() {
    }

    public Kendaraan(String nama, int jmlRoda) {
        this.nama = nama;
        this.jmlRoda = jmlRoda;
    }

    public void NyelakanMesin() {
        System.out.println("Mesin " + nama + " telah dinyalakan!");
    }

    public void belok() {
        System.out.println("Harap masukkan arah! ");
    }

  public void showInfo() {
        System.out.println("Nama Kendaraan: " + nama);
        System.out.println("Jumlah Roda: " + jmlRoda);

        extraInfo();
    }
    public void extraInfo() {

    }
}
