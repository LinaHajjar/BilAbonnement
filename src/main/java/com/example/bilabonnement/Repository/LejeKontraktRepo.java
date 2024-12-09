package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.LejeKontrakt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDate;
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

    public void sletLejeKontract(int lejekontrakt_id) throws SQLException {
        String sql = "DELETE FROM lejekontrakt WHERE lejekontrakt_id = ?";
        template.update(sql, lejekontrakt_id);
    }

    public boolean opdaterLejeKontrakt(LejeKontrakt lejeKontrakt) {
        String sql = "UPDATE lejekontrakt " +
                "SET slutdato = ?, maxKm = ?, pris = ? " +
                "WHERE telefonnummer = ? AND nummerplade = ?";
        int rowsUpdated = template.update(sql,
                lejeKontrakt.getSlutdato(),
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
        template.update(sql, lejeKontrakt.getTelefonnummer(), lejeKontrakt.getNummerplade(), lejeKontrakt.getStartdato(), lejeKontrakt.getSlutdato(), lejeKontrakt.getMaxKm(), lejeKontrakt.getPris()); // denne kode adder til databasen ved hjælp af getters
    }

        //  query der tager en kundes telefonnummer og finde alle kundens lejekontrakter: søge efter kunden.
    public List<LejeKontrakt> findKontraktByTelefon(String telefonnummer) throws SQLException {
        String sql = "SELECT * FROM lejekontrakt WHERE telefonnummer = ?";
        RowMapper<LejeKontrakt> rowMapper = new BeanPropertyRowMapper<LejeKontrakt>(LejeKontrakt.class);
        return template.query(sql, rowMapper, telefonnummer);
    }

//
//    public int getAntalBiler (LocalDate startdato, LocalDate slutdato) throws SQLException {
//        String sql = "SELECT lk.nummerplade, bi.maerke, bi.model" +
//                "FROM lejekontrak.lk JOIN bil bi ON bi.nummerplade = lk.nummerplade\n" +
//                "WHERE lk.startdato >= ? AND lk.slutdato <= ?" +
//                "ORDER BY lk.startdato DESC";
//
//        java.sql.Date sqlStartdato = java.sql.Date.valueOf(startdato);
//        java.sql.Date sqlSlutdato = java.sql.Date.valueOf(slutdato);
//
//
//
//
////        RowMapper<LejeKontrakt> rowMapper = new BeanPropertyRowMapper<>(LejeKontrakt.class);
//        return template.queryForObject(sql,Integer.class, sqlStartdato, sqlSlutdato);
//    }

public int getAntalBiler(LocalDate startdato, LocalDate slutdato) throws SQLException {
    String sql = "SELECT COUNT(*) FROM lejekontrakt WHERE startdato >= ? AND slutdato <= ?";

    // Convert LocalDate to java.sql.Date
    java.sql.Date sqlStartdato = java.sql.Date.valueOf(startdato);
    java.sql.Date sqlSlutdato = java.sql.Date.valueOf(slutdato);

    // Execute the query and return the count
    return template.queryForObject(sql, Integer.class, sqlStartdato, sqlSlutdato);
}





}
