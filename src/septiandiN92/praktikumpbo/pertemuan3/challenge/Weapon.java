package septiandiN92.praktikumpbo.pertemuan3.challenge;

public class Weapon {
    String name;
    float damage;

    public Weapon() {
    }

    public Weapon(String name, float damage) {
        this.name = name;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }
}
