package septiandiN92.praktikumpbo.pertemuan4;

public class Main {
    public static void main(String[] args) {
//
//        // Cara Lebih Singkat
//        Mobil mb = new Mobil("Honda Jazz", 4, 4, "Pertamax Turbo");
//
//        // Cara Formal
////        mb.nama = "Suzuki Karimun WagonR";
////        mb.jmlRoda = 4;
////        mb.jmlPintu = 2;
////        mb.jenisBensin = "Pertalite";
//
//        mb.showInfo();
//        mb.NyelakanMesin();
//        mb.belok("kanan");
//        mb.belok();
        Mobil mb = new Mobil();
        Kendaraan nk = new Kendaraan();

        mb.nama = "Honda Jazz";
        mb.jmlRoda = 4;
        nk.nama = "Kendaraan LCGC";
        nk.jmlRoda = 3;

        mb.showInfo();
        mb.NyelakanMesin();
        mb.belok("kanan");
        mb.belok();

        nk.showInfo();
    }
}
