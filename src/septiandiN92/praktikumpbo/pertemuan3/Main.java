package septiandiN92.praktikumpbo.pertemuan3;

public class Main {
    public static void main(String[] args) {
//        // Cara formal
//        // -- Pembuatan Class --
//        Animal cat = new Animal();
//        Animal dog = new Animal();
//        Animal rabbit = new Animal();
//
//        // Pengisian nilai attribute di object 'cat'
//        cat.name = "Solaria";
//        cat.age = 2;
//        cat.color = "White";
//
//        // Pengisian nilai attribute di object 'dog'
//        dog.name = "Haku";
//        dog.age = 4;
//        dog.color = "Black white";
//
//        // Pengisian nilai attribute di object 'rabbit'
//        rabbit.name = "Okama";
//        rabbit.age = 1;
//        rabbit.color = "Brown white";

        // Cara Cepat
        // -- Pembuatan Class --
        Animal cat = new Animal("Solaria", 2, "White");
        Animal dog = new Animal("Haku", 4, "Black white");
        Animal rabbit = new Animal("Okama", 1, "Brown white");

        // Menjalankan method 'showProfile()' masing-masing object
        cat.showProfile();
        dog.showProfile();
        rabbit.showProfile();

        // Uji coba Getter & Setter
        System.out.println("Dog's name (before): " + dog.getName());
        dog.setName("Kimocho");
        System.out.println("Dog's name (after): " + dog.getName());
    }
}
