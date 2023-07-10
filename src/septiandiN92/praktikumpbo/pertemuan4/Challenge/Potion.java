package septiandiN92.praktikumpbo.pertemuan4.Challenge;

public class Potion extends Item{
    float healing;

    public Potion(String name, int price, float healing){
        super(name, price);
        this.healing = healing;
    }

    public void extraInfo() {
        System.out.println("Nama Potion\t: " + this.getName());
        System.out.println("Harga\t\t: " + this.getPrice());
        System.out.println("Heal\t\t: " + this.healing);
        System.out.println("\n");
    }

    public float getHealing(){
        return healing;
    }

    public void setHealing(float healing) {
        this.healing = healing;
    }
}
