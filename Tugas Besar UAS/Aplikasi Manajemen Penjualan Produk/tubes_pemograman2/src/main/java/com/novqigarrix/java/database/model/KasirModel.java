package com.novqigarrix.java.database.model;

public class KasirModel extends UserModel {

    Long gaji;

    public KasirModel() {

    }

    public Long getGaji() {
        return gaji;
    }

    public void setGaji(Long gaji) {
        this.gaji = gaji;
    }

    @Override
    public String toString() {
        System.out.println("Saya adalah Kasir!");
        return "\nId: " + getUserId() +
                "\n Nama: " + getNama() +
                "\n Role: " + getRole() +
                "\n Gaji: " + getGaji();
    }

}
