package septiandiN92.praktikumpbo.pertemuan4.Challenge;

public class Player {
    String name;
    float hp;

    Player(String name, float hp){
        this.name = name;
        this.hp = hp;
    }

    public void showHud(){
        System.out.println("Nama Player : " + this.name);
        System.out.println("Jumlah HP   : " + this.hp);

        if (this.hp == 0){
            System.out.println("Karakter mati");
        }
    }

    public void attack(Weapon weapon) {
        System.out.println("Nama Weapon\t: " + weapon.getName());
    }
    public void heal(Potion potion){
        System.out.println("Nama Potion\t: " + potion.getName());
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
