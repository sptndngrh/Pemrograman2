package septiandiN92.praktikumpbo.pertemuan3;

public class Animal {
//    // Attribute
//    String name;
//    int age;
//    String color;

//    // Attribute private
    private String name;
    private int age;
    private String color;


    // Constructor 1
    public Animal() {}

    // Constructor 2
    public Animal(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    // Method (Function)
    public void showProfile() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age + " years old");
        System.out.println("Color: " + color);
        System.out.println();
    }

//    public Animal(String name, int age, String color) {
//        this.name = name;
//        this.age = age;
//        this.color = color;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void getColor() {
        this.color = color;
    }
}
