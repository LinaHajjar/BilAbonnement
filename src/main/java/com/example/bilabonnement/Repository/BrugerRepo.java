package com.example.bilabonnement.Repository;


import com.example.bilabonnement.Model.Bruger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrugerRepo {

    @Autowired
    JdbcTemplate template;



    public List<Bruger> getAllUsers(){
        String sql = "select * from medarbejde";
        RowMapper<Bruger> rowMapper = new BeanPropertyRowMapper<Bruger>(Bruger.class);
        return template.query(sql, rowMapper);

    }

}
