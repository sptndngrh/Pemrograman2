package septiandiN92.praktikumpbo.pertemuan4.Tugas1;

public class Main {
    public static void main(String[] args) {
        Daging_ayam ayam = new Daging_ayam("Daging Ayam 1KG", 30500, 160);
        Daging_sapi sapi = new Daging_sapi("Daging Sapi 1KG", 126000, 80);

        ayam.showInfo();
        sapi.showInfo();
    }
}
