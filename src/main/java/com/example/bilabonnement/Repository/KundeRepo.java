package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Kunde;
import com.example.bilabonnement.Model.LejeKontrakt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class KundeRepo {
    @Autowired
    JdbcTemplate template;

    public List<Kunde> getAllKunde() throws SQLException {
        String sql = "select * from kunde";
        RowMapper<Kunde> rowMapper=new BeanPropertyRowMapper<>(Kunde.class);
        return template.query(sql, rowMapper);
    }

    public Kunde getKundeByTelefon(String telefonnummer) throws SQLException {
        String sql = "select * from kunde where telefonnummer = ?";
        RowMapper<Kunde> rowMapper=new BeanPropertyRowMapper<>(Kunde.class);
        Kunde kunde=template.queryForObject(sql, rowMapper, telefonnummer);
        return kunde;
    }

    public boolean opdaterKundeInfo(Kunde kunde){
        String sql = "UPDATE kunde SET email=?, adresse=?, postnummer=?, byen=?, where telefonnummer = ?";
        template.update(sql, kunde.getEmail(), kunde.getAdresse(), kunde.getPostnummer(), kunde.getByen(), kunde.getTelefonnummer());
        return true;
    }

    public boolean phoneNumberExists(String telefonnummer) {
        String sql = "SELECT COUNT(*) FROM kunde WHERE telefonnummer = ?"; // vælger det valgte telefonnummer, og tæller om kunden er der
        int count = template.queryForObject(sql, int.class, telefonnummer); // tester om kunden med det valgte telefonnummer allerede er ti tabellen
        return count > 0; // returnerer en boolean false hvis kunde ikke eksisterer
    }

    public void addKunde(Kunde kunde){
        String sql = "INSERT INTO kunde(telefonnummer, email, fornavn, efternavn, adresse, postnummer, byen, koerekortnummer, udstedelsdato)\n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";  // ? ? ? : prepare statement
        template.update(sql, kunde.getTelefonnummer(), kunde.getEmail(), kunde.getFornavn(), kunde.getEfternavn(), kunde.getAdresse(), kunde.getPostnummer(), kunde.getByen(), kunde.getKoerekortnummer(), kunde.getUdstedelsdato()); // mangler getters og setters:DONE
    }

    public Boolean sletKunde(String telefonnummer) throws SQLException {
        String sql = "DELETE FROM kunde WHERE telefonnummer = ?";
        return template.update(sql, telefonnummer)>0;
    }

    public void redigerKunde(Kunde kunde){
        String sql = "UPDATE kunde SET email=?, fornavn=?, efternavn=?, adresse=?, postnummer=?, byen=? WHERE telefonnummer= ?";
        template.update(sql, kunde.getEmail(),kunde.getFornavn(), kunde.getEfternavn(), kunde.getAdresse(), kunde.getPostnummer(), kunde.getByen(), String.valueOf(kunde.getTelefonnummer()));
    }


}
