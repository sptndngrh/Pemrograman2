package septiandiN92.praktikumpbo.uts;

import java.util.Scanner;

public class Biodata {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Nama: ");
        String nama = input.nextLine();

        System.out.print("NIM: ");
        String nim = input.nextLine();

        System.out.print("Program Studi: ");
        String programstudi = input.nextLine();

        System.out.print("Mata Kuliah Favorit: ");
        String mk = input.nextLine();

        System.out.print("Makanan Favorit: ");
        String foodfav = input.nextLine();

        System.out.print("Minuman Favorit: ");
        String drinkfav = input.nextLine();

        System.out.println("****************************************************");
        System.out.println("Nama: " + nama);
        System.out.println("NIM: " + nim);
        System.out.println("Program Studi: " + programstudi);
        System.out.println("Mata Kuliah Favorit: " + mk);
        System.out.println("Makanan Favorit: " + foodfav);
        System.out.println("Minuman Favorit: " + drinkfav);
        System.out.println("****************************************************");

    }
}
