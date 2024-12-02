package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.LejeKontrakt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class LejeKontraktRepo {

    @Autowired
    JdbcTemplate template;

    public List<LejeKontrakt> getAllLejeKontrakt() throws SQLException {
        String sql = "select * from lejekontrakt";
        RowMapper<LejeKontrakt> rowMapper=new BeanPropertyRowMapper<>(LejeKontrakt.class);
        return template.query(sql, rowMapper);
    }

    public LejeKontrakt getKontraktById(int lejekontrakt_id) throws SQLException {
        String sql = "select * from lejekontrakt where lejekontrakt_id = ?";
        RowMapper<LejeKontrakt> rowMapper = new BeanPropertyRowMapper<>(LejeKontrakt.class);
        LejeKontrakt lejeKontrakt = template.queryForObject(sql, rowMapper, lejekontrakt_id);
        return lejeKontrakt;
    }

    public LejeKontrakt getKontraktByTelefon(String telefonnumer) throws SQLException {
        String sql = "select * from lejekontrakt where telefonnumer = ?";
        RowMapper<LejeKontrakt> rowMapper = new BeanPropertyRowMapper<>(LejeKontrakt.class);
        LejeKontrakt lejeKontrakt = template.queryForObject(sql, rowMapper, telefonnumer);
        return lejeKontrakt;
    }

    public Boolean sletLejeKontract(int lejekontrakt_id) throws SQLException {
        String sql = "DELETE FROM lejekontrakt WHERE contract_number = ?";
        return template.update(sql, lejekontrakt_id)>0;
    }

    public boolean opdaterLejeKontrakt(LejeKontrakt lejeKontrakt) {
        String sql = "UPDATE lejekontrakt " +
                "SET slutdato = ?, maxKm = ?, pris = ? " +
                "WHERE telefonnummer = ? AND nummerplade = ?";
        int rowsUpdated = template.update(sql,
                lejeKontrakt.getSlutDato(),
                lejeKontrakt.getMaxKm(),
                lejeKontrakt.getPris(),
                lejeKontrakt.getTelefonnummer(),
                lejeKontrakt.getNummerplade());

        // Return true if at least one row was updated, otherwise return false
        return rowsUpdated > 0;
    }



    public void addLejekontrakt(LejeKontrakt lejeKontrakt){
        String sql = "INSERT INTO lejekontrakt(telefonnummer, nummerplade, startDato, slutDato, maxKm, pris)\n" +
                "VALUES(?, ?, ?, ?, ?, ?)";
        template.update(sql, lejeKontrakt.getTelefonnummer(), lejeKontrakt.getNummerplade(), lejeKontrakt.getStartdato(), lejeKontrakt.getSlutDato(), lejeKontrakt.getMaxKm(), lejeKontrakt.getPris()); // denne kode adder til databasen ved hjælp af getters
    }

}
