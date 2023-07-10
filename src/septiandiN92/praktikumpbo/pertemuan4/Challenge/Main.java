package septiandiN92.praktikumpbo.pertemuan4.Challenge;

import septiandiN92.praktikumpbo.pertemuan3.challenge.Hero;

public class Main {
    public static void main(String[] args) {
        Weapon weapon1 = new Weapon("Tas", 1500, 500);
        Weapon weapon2 = new Weapon("Pedang", 6000, 3400);
        Potion potion1 = new Potion("Cairan Merah", 2500, 800);
        Potion potion2 = new Potion("Cairan Hijau", 4500, 2500);

        Player player1 = new Player("Haji", 2450);
        Player player2 = new Player("Okky", 6500);

        weapon1.extraInfo();

        potion1.extraInfo();

        player1.attack(weapon1);
        player2.heal(potion1);
        player1.showHud();
        player1.setHp(0);
        player1.showHud();
    }
}
