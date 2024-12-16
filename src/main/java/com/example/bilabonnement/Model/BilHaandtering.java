package com.example.bilabonnement.Model;

import java.time.LocalDate;

public class BilHaandtering {

    int haandtering_id;
    int lejekontrakt_id;
    String nummerplade;
    LocalDate  handlingsdato;
    String handlingslokation;
    public enum type {UDLEVERING, RETURNERING}
    type type;
    public enum status {UNDER_LEVERING, LEVERET, AFVENTER_INSPEKTION, INSPICERET, KLAR_TIL_SALG}
    status status;



    public BilHaandtering() {

    }


    public int getHaandtering_id() {
        return haandtering_id;
    }

    public void setHaandtering_id(int haandtering_id) {
        this.haandtering_id = haandtering_id;
    }

    public int getLejekontrakt_id() {
        return lejekontrakt_id;
    }

    public void setLejekontrakt_id(int lejekontrakt_id) {
        this.lejekontrakt_id = lejekontrakt_id;
    }

    public String getNummerplade() {
        return nummerplade;
    }

    public void setNummerplade(String nummerplade) {
        this.nummerplade = nummerplade;
    }

    public LocalDate getHandlingsdato() {
        return handlingsdato;
    }

    public void setHandlingsdato(LocalDate dato) {
        this.handlingsdato = dato;
    }



    public String getHandlingsLokation() {
        return handlingslokation;
    }

    public void setHandlingsLokation(String handlingsLokation) {
        this.handlingslokation = handlingsLokation;
    }


    public BilHaandtering.type getType() {
        return type;
    }

    public void setType(BilHaandtering.type type) {
        this.type = type;
    }

    // Getters and Setters for status
    public BilHaandtering.status getStatus() {
        return status;
    }

    public void setStatus(BilHaandtering.status status) {
        this.status = status;
    }











}
