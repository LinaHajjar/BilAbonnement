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

}
