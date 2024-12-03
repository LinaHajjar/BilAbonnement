package com.example.bilabonnement.Model;

public class Bruger {

    int afdeling_id;
    String username;
    String password;


    public Bruger() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAfdeling_id() {
        return afdeling_id;
    }

    public void setAfdeling_id(int afdeling_id) {
        this.afdeling_id = afdeling_id;
    }
}
