package septiandiN92.praktikumpbo.pertemuan4.Challenge;

public class Item {
    String name;
    int price;

    Item() {}
    Item(String name, int price) {
        this.name = name;
        this.price = price;
    }
    public void showInfo() {
        System.out.println("Nama : " + this.name);
    }
    public void extraInfo(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
