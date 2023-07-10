package septiandiN92.praktikumpbo.pertemuan3.challenge;

public class Hero {
    String name;
    float hp;

    Hero() {}

    public Hero(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    void attack(Weapon weapon) {
        float damage = weapon.getDamage();
        System.out.println("Hero Attack " + damage);
    }

    void wear(Armor armor) {
        System.out.println("Armor Hero " + armor.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }
}
