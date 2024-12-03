package com.example.bilabonnement.Model;

public class Skader {

int lejekontrakt_id;
skade_type skade_type;
String beskrivelse;
double pris;


public Skader(){
}


public enum skade_type{SKADE,MANGEL,FEJL;}

public int getLejekontrakt_id() {return lejekontrakt_id;}

public void setLejekontrakt_id(int lejekontrakt_id) {this.lejekontrakt_id = lejekontrakt_id;}

public skade_type getSkade_type() {
    return skade_type;

}

public void setSkade_type(skade_type skade_type) {
    this.skade_type = skade_type;
}
public String getBeskrivelse() {return beskrivelse;}

public void setBeskrivelse(String beskrivelse){this.beskrivelse = beskrivelse;}

public double getPris(){return pris;}

public void setPris(double pris){this.pris = pris;}

}
