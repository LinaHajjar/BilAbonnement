package com.example.bilabonnement.Model;

import java.time.LocalDate;

public class LejeKontrakt {
    int lejekontrakt_id;
    String telefonnummer;
    String nummerplade;
    LocalDate startdato;
    LocalDate slutdato;
    int maxKm;
    double pris;

    public LejeKontrakt(){}

    public int getLejekontrakt_id() {
        return lejekontrakt_id;
    }

    public void setLejekontrakt_id(int lejekontrakt_id) {
        this.lejekontrakt_id = lejekontrakt_id;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getNummerplade() {
        return nummerplade;
    }

    public void setNummerplade(String nummerplade) {
        this.nummerplade = nummerplade;
    }

    public LocalDate getStartdato() {
        return startdato;
    }

    public void setStartdato(LocalDate startdato) {
        this.startdato = startdato;
    }

    public LocalDate getSlutdato() {
        return slutdato;
    }

    public void setSlutdato(LocalDate slutdato) {
        this.slutdato = slutdato;
    }

    public int getMaxKm() {
        return maxKm;
    }

    public void setMaxKm(int maxKm) {
        this.maxKm = maxKm;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }
}
