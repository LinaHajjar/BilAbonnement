package com.example.bilabonnement.Repository;


import com.example.bilabonnement.Model.Bil;
import com.example.bilabonnement.Model.LejeKontrakt;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class BookingRepo {

    JdbcTemplate template;




    public List<Bil> getTilgaengeligBiler(Date startdato, Date slutdato) {

        String sql = "SELECT b.* FROM Bil b LEFT JOIN lejekontrakt lk ON b.car_id = lk.car_id WHERE(lejekontrakt.startdato IS NULL  AND lejekontrakt.enddato = ?)";
        RowMapper<Bil> rowmapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql, rowmapper);

    }


    public List<LejeKontrakt> seBiler(Date startdato, Date slutdato) {
        String sql = "SELECT nummerplade, startdato, slutdato  FROM lejekontrakt lk ORDER BY nummerplade, startdato";
        RowMapper<LejeKontrakt> rowMapper = new BeanPropertyRowMapper<>(LejeKontrakt.class);
        return template.query(sql, rowMapper);
    }


    public List<Bil> carAvailabilityPeriod(Date startdato, Date slutdato) {
        String sql = "SELECT b.* FROM Bil b LEFT JOIN lejekontrakt lk ON b.car_id = lk.car_id WHERE" +
                "lk.startdato IS NULL OR lk.enddato<? OR lk.startdato> ?";

        RowMapper<Bil> rowmapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql, rowmapper, startdato, slutdato);
    }







}
