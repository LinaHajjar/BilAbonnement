package com.example.bilabonnement.Repository;


import com.example.bilabonnement.Model.Kunde;
import com.example.bilabonnement.Model.LejeKontrakt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BilabonnementRepo {

    @Autowired
    JdbcTemplate template;


    public void addKunde(Kunde kunde){
        String sql = "INSERT INTO dummyTable(telefonnummer, email, fornavn, efternavn, adresse, postnummer, byen, koerekortnummer, udstedelsdato)\n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";  // ? ? ? : prepare statement
        template.update(sql, kunde.getTelefonnummer(), kunde.getEmail(), kunde.getFornavn(), kunde.getEfternavn(), kunde.getAdresse(), kunde.getPostnummer(), kunde.getByen(), kunde.getKoerekortnummer(), kunde.getUdstedelsdato()); // mangler getters og setters:DONE
    }


    public void addLejekontrakt(LejeKontrakt lejeKontrakt){
        String sql = "INSERT INTO lejekontrakt(telefonnummer, nummerplade, startDato, slutDato, maxKm, pris)\n" +
                "VALUES(?, ?, ?, ?, ?, ?)";
        template.update(sql, lejeKontrakt.getTelefonnummer(), lejeKontrakt.getNummerplade(), lejeKontrakt.getStartdato(), lejeKontrakt.getSlutdato(), lejeKontrakt.getMaxKm(), lejeKontrakt.getPris()); // denne kode adder til databasen ved hjælp af getters
    }

}
