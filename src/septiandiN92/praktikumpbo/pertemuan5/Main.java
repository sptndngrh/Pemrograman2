package septiandiN92.praktikumpbo.pertemuan5;

public class Main {
    public static void main(String[] args) {

        Barang[] keranjang = new Barang[10];

        Barang[] etalase = {
          new Barang("Tahu China", 5600),
          new Barang("Tempe Indonesia", 6500),
          new Barang("Indomie SP", 2900),
          new Barang("Mountoya 1 Dus", 25500 ),
          new Barang("Tepung Bumbu Serbaguna 1Kg", 13500)
        };

        System.out.println("Jumlah barang di etalase: " + etalase.length);

        System.out.println("Daftar barang di etalase: ");
        int i = 1;

        for(Barang b : etalase){
            System.out.println("Barang ke-" + i);
            b.showInfo();
            i++;
        }

        for(int x=0; x<etalase.length; x++) {
            System.out.println("Barang ke-" + (x+1) );
            etalase[x].showInfo();
        }
    }
}
