package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Kunde;
import com.example.bilabonnement.Model.LejeKontrakt;
import com.example.bilabonnement.Model.MonthlyIncome;
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


    public List<LejeKontrakt> getLejeKontraktById(int lejekontrakt_id) throws SQLException {
        String sql = "SELECT * FROM lejekontrakt WHERE lejekontrakt_id = ?";
        RowMapper<LejeKontrakt> rowMapper = new BeanPropertyRowMapper<LejeKontrakt>(LejeKontrakt.class);
        return template.query(sql, rowMapper, lejekontrakt_id);
    }

//metode til mvp
public int getAntalBiler(LocalDate startdato, LocalDate slutdato) throws SQLException {
    String sql = "SELECT COUNT(*) FROM lejekontrakt WHERE startdato >= ? AND slutdato <= ?";

    // Convert LocalDate to java.sql.Date
    java.sql.Date sqlStartdato = java.sql.Date.valueOf(startdato);
    java.sql.Date sqlSlutdato = java.sql.Date.valueOf(slutdato);

    // Execute the query and return the count
    return template.queryForObject(sql, Integer.class, sqlStartdato, sqlSlutdato);
}


    public List<MonthlyIncome> monthlyIncomeList(int year){
        String sql = "SELECT \n" +
                "    MONTH(slutdato) AS måned, \n" +
                "    SUM(pris) AS indtjening\n" +
                "FROM lejekontrakt\n" +
                "WHERE YEAR(slutdato) = ?\n" +
                "GROUP BY MONTH(slutdato)\n" +
                "ORDER BY MONTH(slutdato);\n";


        RowMapper<MonthlyIncome> rowMapper = new BeanPropertyRowMapper<MonthlyIncome>(MonthlyIncome.class);

        return template.query(sql, rowMapper, year);

    }

    public List<String> getBilMaerker() {
        String sql = "SELECT DISTINCT maerke FROM bil WHERE maerke IS NOT NULL AND maerke != ''";
        return template.queryForList(sql, String.class);  // This will return a List<String>
    }

    public void redigerLejeKontrakt(LejeKontrakt lejeKontrakt){
        String sql = "UPDATE lejeKontrakt SET startdato=?, slutdato=?, maxKm=?, pris=? WHERE lejekontrakt_id= ?";
        template.update(sql, lejeKontrakt.getStartdato(), lejeKontrakt.getSlutdato(), lejeKontrakt.getMaxKm(), lejeKontrakt.getPris(), lejeKontrakt.getLejekontrakt_id());
    }
}
