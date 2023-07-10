package septiandiN92.praktikumpbo.pertemuan2;

import java.util.Scanner;

public class tugasno2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int jam, menit, detik, konversi;

        System.out.println("Berapa Detik: ");
        konversi = input.nextInt();

        jam = konversi/3600;
        menit = (konversi%3600)/60;
        detik = (konversi%3600)%60;

        System.out.println("Hasil konversi yaitu: ");
        System.out.println(jam + " jam, " + menit + " menit, " + detik + " detik, " + "\nNama: Septiandi Nugraha" +"\nNIM: 21104060" );
        input.close();
    }
}
