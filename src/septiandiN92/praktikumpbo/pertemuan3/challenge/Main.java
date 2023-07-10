package septiandiN92.praktikumpbo.pertemuan3.challenge;

public class Main {
        public static void main(String[] args) {

            var hero = new Hero("Layla", 1000);
            var weapon = new Weapon("Buffer", 69);
            var armor = new Armor("Baju Besi", 500);

            hero.attack(weapon);
            hero.wear(armor);
        }
}
