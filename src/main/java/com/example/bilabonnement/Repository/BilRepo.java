package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Bil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
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
}
