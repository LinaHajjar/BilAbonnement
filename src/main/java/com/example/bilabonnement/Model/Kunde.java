package com.example.bilabonnement.Model;

import java.time.LocalDate;

public class Kunde {
    String telefonnummer;
    String email;
    String fornavn;
    String efternavn;
    String adresse;
    int postnummer;
    String byen;
    String koerekortnummer;
    LocalDate udstedelsdato;

    public Kunde(){}
    public Kunde(String email, String telefonnummer, String efternavn, String fornavn, String adresse, int postnummer, String byen, String koerekortnummer, LocalDate udstedelsdato) {
        this.email = email;
        this.telefonnummer = telefonnummer;
        this.efternavn = efternavn;
        this.fornavn = fornavn;
        this.adresse = adresse;
        this.postnummer = postnummer;
        this.byen = byen;
        this.koerekortnummer = koerekortnummer;
        this.udstedelsdato = udstedelsdato;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public LocalDate getUdstedelsdato() {
        return udstedelsdato;
    }

    public void setUdstedelsdato(LocalDate udstedelsdato) {
        this.udstedelsdato = udstedelsdato;
    }

    public String getKoerekortnummer() {
        return koerekortnummer;
    }

    public void setKoerekortnummer(String koerekortnummer) {
        this.koerekortnummer = koerekortnummer;
    }

    public String getByen() {
        return byen;
    }

    public void setByen(String byen) {
        this.byen = byen;
    }

    public int getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(int postnummer) {
        this.postnummer = postnummer;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEfternavn() {
        return efternavn;
    }

    public void setEfternavn(String efternavn) {
        this.efternavn = efternavn;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
