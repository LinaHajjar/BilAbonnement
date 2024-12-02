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
        String sql = "SELECT COUNT(*) FROM kunde WHERE telefonnummer = ?";
        int count = template.queryForObject(sql, int.class, telefonnummer);
        return count > 0;
    }

    public void addKunde(Kunde kunde){
        String sql = "INSERT INTO kunde(telefonnummer, email, fornavn, efternavn, adresse, postnummer, byen, koerekortnummer, udstedelsdato)\n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";  // ? ? ? : prepare statement
        template.update(sql, kunde.getTelefonnummer(), kunde.getEmail(), kunde.getFornavn(), kunde.getEfternavn(), kunde.getAdresse(), kunde.getPostnummer(), kunde.getByen(), kunde.getKoerekortnummer(), kunde.getUdstedelsdato()); // mangler getters og setters:DONE
    }

    public void sletKunde(String telefonnummer) throws SQLException {
        String deleteLejekontraktSql = "DELETE FROM lejekontrakt WHERE telefonnummer = ?";
        template.update(deleteLejekontraktSql, telefonnummer);

        String sql = "DELETE FROM kunde WHERE telefonnummer = ?";
        template.update(sql, telefonnummer);
    }

}
