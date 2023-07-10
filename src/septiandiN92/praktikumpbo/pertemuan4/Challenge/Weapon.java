package septiandiN92.praktikumpbo.pertemuan4.Challenge;

public class Weapon extends Item{
    float damage;

    public Weapon(String name, int price, float damage){
        super(name, price);
        this.damage = damage;
    }

    public void extraInfo() {
        System.out.println("Nama Weapon\t: " + this.getName());
        System.out.println("Harga\t\t: " + this.getPrice());
        System.out.println("Damage\t\t: " + this.damage);
        System.out.println("\n");
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }
}
