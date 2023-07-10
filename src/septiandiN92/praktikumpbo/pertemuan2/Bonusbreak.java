package septiandiN92.praktikumpbo.pertemuan2;

public class  Bonusbreak {
    public static void main(String[] args) {
        int j = 1;
        while (j <= 100) {
            System.out.println("Loading... (" + j + "%)");
            j += 10;

            if (j == 50) {
                System.out.println("Udahan dulu euy, letih!");
                break;
            }
        }
        System.out.println("Looping While selesai! \n");
    }
}
