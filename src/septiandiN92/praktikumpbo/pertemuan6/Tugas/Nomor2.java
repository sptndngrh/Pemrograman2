package septiandiN92.praktikumpbo.pertemuan6.Tugas;

public class Nomor2 {
    public static void main(String[] args) {
        try {
            int score[] = new int[-2];
            score[15]=35;
            System.out.println(score[1]);
        }

        catch (NegativeArraySizeException e) {
            System.out.println("Error: Program tidak dapat dijalankan!");
        }

        System.out.println("Program telah selesai dijalankan :) !!!!");
    }
}
