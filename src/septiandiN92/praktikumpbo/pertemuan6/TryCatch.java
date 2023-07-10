package septiandiN92.praktikumpbo.pertemuan6;

public class TryCatch {
    public static void main(String[] args) {
        int[] numbers = { 1, 2, 3, 4, 5 };

        try {
            System.out.println(numbers[7]);
        }

        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Array yang dimasukkan melebihi batas!");
        }
        System.out.println("Proses selesai!");
    }
}

