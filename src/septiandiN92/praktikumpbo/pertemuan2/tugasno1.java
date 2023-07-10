package septiandiN92.praktikumpbo.pertemuan2;

import java.util.Scanner;

public class tugasno1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int faktorial = 1;
        System.out.println("Masukkan bilangan bulat: ");
        int n = input.nextInt();
        if (n <= 0) {
            System.out.println("Ini adalah nol! \nNIM: 21104060");
        }
        else {
            for (int i = 1; i <= n; i++) {
                faktorial *= i;
            }
            System.out.println("Hasil faktorial dari " + String.valueOf(n) + " adalah " + String.valueOf(faktorial) + "\nNIM: 21104060");
        }

        input.close();
    }
}
