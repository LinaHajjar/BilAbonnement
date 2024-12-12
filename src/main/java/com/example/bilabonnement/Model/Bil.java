package com.example.bilabonnement.Model;

import java.time.LocalDate;

public class Bil {
    String nummerplade;
    String maerke;
    String model;
    enum Braendstoftype {ELEKTIK, BENZIN, DIESEL, HYBRID};
    Braendstoftype braendstoftype;
    int odometer;
    LocalDate foersteregistrering;
    int co2udledning;

    public Bil(){} //tømme konstruktør


    public String getNummerplade() {
        return nummerplade;
    }

    public void setNummerplade(String nummerplade) {
        this.nummerplade = nummerplade;
    }

    public String getMaerke() {
        return maerke;
    }

    public void setMaerke(String maerke) {
        this.maerke = maerke;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public LocalDate getFoersteregistrering() {
        return foersteregistrering;
    }

    public void setFoersteregistrering(LocalDate foersteregistrering) {
        this.foersteregistrering = foersteregistrering;
    }

    public int getCo2udledning() {
        return co2udledning;
    }

    public void setCo2udledning(int co2udledning) {
        this.co2udledning = co2udledning;
    }

    public Braendstoftype getBraendstoftype() {
        return braendstoftype;
    }

    public void setBraendstoftype(Braendstoftype braendstoftype) {
        this.braendstoftype = braendstoftype;
    }

    //toString metode
    @Override
    public String toString() {
        return "Bil{" +
                "nummerplade='" + nummerplade + '\'' +
                ", maerke='" + maerke + '\'' +
                ", model='" + model + '\'' +
                ", braendstoftype=" + braendstoftype +
                ", odometer=" + odometer +
                ", foersteregistrering=" + foersteregistrering +
                ", co2udledning=" + co2udledning +
                '}';
    }
}
