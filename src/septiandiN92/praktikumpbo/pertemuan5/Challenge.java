package septiandiN92.praktikumpbo.pertemuan5;

import java.util.Scanner;

public class Challenge {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        System.out.println("Masukkan Nama Barang: ");
        String nama = userInput.next();
        System.out.println("Nama Barang = " + nama);

        System.out.println("Masukkan Harga Barang: ");
        int harga = userInput.nextInt();
        System.out.println("Harga Barang = " + harga);


    }
}
