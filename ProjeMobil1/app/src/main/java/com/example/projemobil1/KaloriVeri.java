package com.example.projemobil1;

public class KaloriVeri {

    int id;
    String kaloriSon;
    String tarih;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKaloriSon() {
        return kaloriSon;
    }

    public void setKaloriSon(String kaloriSon) {
        this.kaloriSon = kaloriSon;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public KaloriVeri(int id, String kaloriSon, String tarih){
        this.id = id;
        this.kaloriSon = kaloriSon;
        this.tarih = tarih;
    }

    public String toString(){
        return ""+id+"-"+kaloriSon+"-"+tarih;
    }
}
