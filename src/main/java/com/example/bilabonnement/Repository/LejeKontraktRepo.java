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

}
