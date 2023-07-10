package septiandiN92.praktikumpbo.pertemuan6.Tugas;

public class Nomor1 {

    public static void main(String[] args) {
        String kampus = "Institut Teknologi Telkom Purwokerto";
        try {
            int number = Integer.parseInt(kampus);
            System.out.println(number);
        }

        catch (NullPointerException exception) {
            System.out.println("Error " + exception.getMessage());
        }
        catch (NumberFormatException exception) {
            System.out.println("Error " + exception.getMessage());
        }

        finally {
            System.out.println("Program akan dieksekusi :|");
        }

        System.out.println("Selesai :)");

    }
}
