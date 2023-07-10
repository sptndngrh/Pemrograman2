package septiandiN92.praktikumpbo.pertemuan4.Tugas;

 public class RumahMakanSunda {
        String nama;
        int harga;
        int stok;

        public RumahMakanSunda(String nama, int harga, int stok) {
            this.nama = nama;
            this.harga = harga;
            this.stok = stok;
        }

        public void showInfo() {
            System.out.println("Menu : " + this.nama);
            System.out.println("Harga: " + this.harga);
            System.out.println("Stok : " + this.stok);
            System.out.println();
        }
    }