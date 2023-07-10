package septiandiN92.praktikumpbo.pertemuan6;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MultiException {
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

//        catch (ArithmeticException | InputMismatchException e) {
//            System.out.println("Error: Program tidak dapat di proses :(");
//        }

        catch (ArithmeticException e) {
            System.out.println("Error: Tidak dapat memproses!");
        }

        catch (InputMismatchException e) {
            System.out.println("Error: Masukkan angka saja!");
        }

        System.out.println("Proses telah selesai ya! :)");
    }
}
