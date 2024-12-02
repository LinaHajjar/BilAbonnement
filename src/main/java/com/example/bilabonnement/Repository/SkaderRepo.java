package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Skader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class SkaderRepo {

    @Autowired
    JdbcTemplate template;


    public List<Skader> getAllSkader() {
        String sql = "select * from skader";
        RowMapper<Skader> rowmapper = new BeanPropertyRowMapper<>(Skader.class);
        return template.query(sql, rowmapper);
    }

    public void addSkader(Skader skader){
        String sql= "INSERT INTO skader (lejekontrakt_id, skade_type, beskrivelse, pris)" + "VALUES(?,?,?,?)";
        template.update(sql, skader.getLejekontrakt_id(), skader.getSkade_type().name(), skader.getBeskrivelse(), skader.getPris());
    }





}
