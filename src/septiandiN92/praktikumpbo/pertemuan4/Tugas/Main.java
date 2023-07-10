package septiandiN92.praktikumpbo.pertemuan4.Tugas;

public class Main {
    public static void main(String[] args) {
        Menu NasiLiwet = new Menu("Nasi Liwet", 26500, 140);
        Minuman EsCampur = new Minuman("Es Campur", 12000, 50);

        NasiLiwet.showInfo();
        EsCampur.showInfo();
    }
}
