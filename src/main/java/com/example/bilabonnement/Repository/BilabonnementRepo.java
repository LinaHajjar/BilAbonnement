package com.example.bilabonnement.Repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BilabonnementRepo {

    @Autowired
    JdbcTemplate template;


    public void addKunde(){

        String sql = "INSERT INTO dummyTable(telefonnummer, email, fornavn, efternavn, adresse, postnummer, by, koerekortnummer, udstedelsdato)\n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
        template.update(sql) // mangler getters og setters
    }


    public void addLejekontrakt(){
        String sql = "INSERT INTO lejekontrakt(lejekontrakt_id, telefonnummer, nummerplade, startDato, slutDato, maxKm, pris)\n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
        template.update(sql) // mangler getters og setters
    }

}
