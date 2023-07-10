package septiandiN92.praktikumpbo.pertemuan4;

public class Mobil extends Kendaraan{

    int jmlPintu;
    String jenisBensin;

    public Mobil() {
    }

    public Mobil(int jmlPintu, String jenisBensin) {
        this.jmlPintu = jmlPintu;
        this.jenisBensin = jenisBensin;
    }

    public Mobil(String nama, int jmlRoda, int jmlPintu, String jenisBensin) {
        super(nama, jmlRoda);
        this.jmlPintu = jmlPintu;
        this.jenisBensin = jenisBensin;
    }

    public void belok(String arah){
        System.out.println("Mobil " + nama + " belok ke " + arah + "!");
    }

    public void belok() {
        System.out.println("Harap masukkan arah!");
    }

    public void extraInfo() {
        System.out.println("Jumlah pintu: " + jmlPintu);
        System.out.println("Jenis bensin: " + jenisBensin);
    }
}
