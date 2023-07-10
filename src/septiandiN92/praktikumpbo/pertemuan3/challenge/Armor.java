package septiandiN92.praktikumpbo.pertemuan3.challenge;

public class Armor {
    String name;
    float protection;

    public Armor() {}

    public Armor(String name, float protection) {
        this.name = name;
        this.protection = protection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getProtection() {
        return protection;
    }

    public void setProtection(float protection) {
        this.protection = protection;
    }
}
