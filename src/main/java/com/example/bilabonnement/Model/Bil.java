package com.example.bilabonnement.Model;

import java.time.LocalDate;

public class Bil {
    String nummerplade;
    String maerke;
    String model;
    enum braendstoftype {ELEKTIK, BENZIN, DIESEL, HYBRID};
    int odometer;
    LocalDate foersteregistrering;
    Boolean tilgaengelighed;
    int co2udledning;

    public Bil(){} //tømme konstruktør

    public Bil(String maerke, String nummerplade, String model, int odometer, LocalDate foersteregistrering, Boolean tilgaengelighed, int co2udledning) {
        this.maerke = maerke;
        this.nummerplade = nummerplade;
        this.model = model;
        this.odometer = odometer;
        this.foersteregistrering = foersteregistrering;
        this.tilgaengelighed = tilgaengelighed;
        this.co2udledning = co2udledning;
    }

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

    public Boolean getTilgaengelighed() {
        return tilgaengelighed;
    }

    public void setTilgaengelighed(Boolean tilgaengelighed) {
        this.tilgaengelighed = tilgaengelighed;
    }

    public int getCo2udledning() {
        return co2udledning;
    }

    public void setCo2udledning(int co2udledning) {
        this.co2udledning = co2udledning;
    }
}
