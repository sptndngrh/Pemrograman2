package septiandiN92.praktikumpbo.pertemuan6;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MultipleCatch {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try{
            System.out.println("Masukkan bilangan: ");
            int bil1 = input.nextInt();

            System.out.println("Masukkan pembagi: ");
            int bil2 = input.nextInt();

            int hasil = bil1/bil2;
            System.out.println(bil1 + " / " + bil2 + " = " + hasil + " (dibulatkan)");
        }

        // dua jenis exception
        catch (ArithmeticException e) {
            System.out.println("Error: Program tidak dapat diproses :(");
        }
        catch (InputMismatchException e) {
            System.out.println("Error, masukkan angka saja :)");
        }

        System.out.println("Proses sudah selesai bund! ^_^");
    }
}
