package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Bil;
import com.example.bilabonnement.Model.Kunde;
import com.example.bilabonnement.Model.TopBil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BilRepo {
    @Autowired
    JdbcTemplate template;//jdbc: java db connection, det laver et object der opretter hver gang connection til db

    public List <Bil> getAllBil() throws SQLException {
        String sql = "SELECT * FROM bil";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class); //opretter objekter et af gang og gemme dem i en list
        return template.query(sql, rowMapper);
    }

    public Bil getBilByNummerplade(String nummerplade) throws SQLException {
        String sql = "select * from bil where nummerplade = ?";
        RowMapper<Bil> rowMapper=new BeanPropertyRowMapper<>(Bil.class);
        Bil bil=template.queryForObject(sql, rowMapper, nummerplade);
        return bil;
    }

    public List<String> alleNummerplader(){
        String sql = "select nummerplade from bil";
        return template.queryForList(sql, String.class); // tager alle nummerplader og sætter det i en liste
    }

    public double getSamletIndtægt(LocalDate fraDato, LocalDate tilDato) throws SQLException {
        String sql ="SELECT SUM(pris) FROM lejekontrakt WHERE (startdato > ? AND slutdato < ?)";

        Double result = template.queryForObject(sql, Double.class, fraDato, tilDato);

        return (result != null) ? result : 0.0;
    }

    public TopBil getTopLejedeModeller(LocalDate fraDato, LocalDate tilDato) throws SQLException {
        String sql ="SELECT b.maerke, b.model, COUNT(l.nummerplade) AS antal\n" +
                "        FROM bil b\n" +
                "        JOIN lejekontrakt l ON b.nummerplade = l.nummerplade\n" +
                "        WHERE l.startdato >= ? AND l.slutdato <= ?\n" +
                "        GROUP BY b.maerke, b.model\n" +
                "        ORDER BY antal DESC\n" +
                "        LIMIT 1";
        RowMapper <TopBil> rowMapper = new BeanPropertyRowMapper<>(TopBil.class);
        TopBil topBil=template.queryForObject(sql, rowMapper, fraDato, tilDato);
        return topBil;

    }

    public void opretteBil(String nummerplade, String maerke, String model,
                           Bil.Braendstoftype braendstoftype, int odometer,
                           java.time.LocalDate foersteregistrering, int co2udledning) {

        String sql = "INSERT INTO bil (nummerplade, maerke, model, braendstoftype, odometer, foersteregistrering, co2udledning) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        template.update(sql,
                nummerplade,
                maerke,
                model,
                braendstoftype.name(),
                odometer,
                java.sql.Date.valueOf(foersteregistrering),
                co2udledning
        );
    }

    public void redigerBil(Bil bil){
        String sql = "UPDATE bil SET maerke=?, model=?, odometer=?, co2udledning=? WHERE nummerplade= ?";
        template.update(sql, bil.getMaerke(), bil.getModel(), bil.getOdometer(), bil.getCo2udledning(), String.valueOf(bil.getNummerplade()));
    }


    public boolean sletBil(String nummerplade){

        String sql = "DELETE FROM bil WHERE nummerplade = ?";

        return template.update(sql,nummerplade)>0;
    }




}
