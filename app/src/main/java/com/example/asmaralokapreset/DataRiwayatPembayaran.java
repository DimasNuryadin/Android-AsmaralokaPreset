package com.example.asmaralokapreset;

public class DataRiwayatPembayaran {

    // Deklarasi Variabel
    private String key;
    private String namaPreset;
    private String hargaPreset;
    private String emailKirim;
    private String metodePembayaran;


    public String getEmailKirim() {
        return emailKirim;
    }

    public void setEmailKirim(String emailKirim) {
        this.emailKirim = emailKirim;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public String getNamaPreset() {
        return namaPreset;
    }

    public void setNamaPreset(String namaPreset) {
        this.namaPreset = namaPreset;
    }

    public String getHargaPreset() {
        return hargaPreset;
    }

    public void setHargaPreset(String hargaPreset) {
        this.hargaPreset = hargaPreset;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public DataRiwayatPembayaran(){

    }


    public DataRiwayatPembayaran(String namaPreset, String hargaPreset, String emailKirim, String metodePembayaran) {
        this.namaPreset = namaPreset;
        this.hargaPreset = hargaPreset;
        this.emailKirim = emailKirim;
        this.metodePembayaran = metodePembayaran;
    }
}
