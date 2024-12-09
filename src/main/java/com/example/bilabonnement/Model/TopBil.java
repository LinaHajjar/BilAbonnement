package com.example.bilabonnement.Model;

public class TopBil {

    String maerke;
    String model;
    int antal;

    public TopBil(){

    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMaerke() {
        return maerke;
    }

    public void setMaerke(String brand) {
        this.maerke = brand;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antalLejetUd) {
        this.antal= antalLejetUd;
    }

    @Override
    public String toString() {
        return ("TopBil: " +
                "brand=" + maerke +
                ", model=" + model +
                ", antalLejetUd=" + antal);
    }
}
