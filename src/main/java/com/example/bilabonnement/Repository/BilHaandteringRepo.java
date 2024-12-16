package com.example.bilabonnement.Repository;


import com.example.bilabonnement.Model.Bil;
import com.example.bilabonnement.Model.BilHaandtering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BilHaandteringRepo {

    @Autowired
    JdbcTemplate template;


//    public List<BilHaandtering> seAlleHaandteringer(){
//
//        String sql = "SELECT * FROM BilHaandtering";
//        RowMapper rowMapper = new BeanPropertyRowMapper(BilHaandtering.class);
//        return template.query(sql, rowMapper);
//    }

    public List<BilHaandtering> seAlleHaandteringer(){

        String sql =  "SELECT haandtering_id, lejekontrakt_id, nummerplade, handlingsdato, handlingslokation, UPPER(type) as type, UPPER(status) as status FROM BilHaandtering";
        RowMapper rowMapper = new BeanPropertyRowMapper(BilHaandtering.class);
        return template.query(sql, rowMapper);
    }




    public void opretteUdlevering(BilHaandtering bilHaandtering) {

        String sql = "INSERT INTO bilhaandtering(lejekontrakt_id, nummerplade, handlingsdato, handlingslokation, type, status)" + "VALUES(?,?,?,?,?,?)";



        template.update(sql, bilHaandtering.getLejekontrakt_id(),
                bilHaandtering.getNummerplade(),
                bilHaandtering.getHandlingsdato(),
                bilHaandtering.getHandlingsLokation(),
                "UDLEVERING",
                bilHaandtering.getStatus().name()
        );
    }

    public void opretteReturnering(BilHaandtering bilHaandtering) {

        String sql = "INSERT INTO bilhaandtering(lejekontrakt_id, nummerplade, handlingsdato, handlingslokation, type, status)" + "VALUES(?,?,?,?,?,?)";



        template.update(sql, bilHaandtering.getLejekontrakt_id(),
                bilHaandtering.getNummerplade(),
                bilHaandtering.getHandlingsdato(),
                bilHaandtering.getHandlingsLokation(),
                "RETURNERING",
                bilHaandtering.getStatus().name()
        );
    }



    public void opdaterStatus(int haandtering_id, BilHaandtering.status status) {

        String sql = "UPDATE bilhaandtering SET status = ? WHERE haandtering_id = ?";
        template.update(sql, status.name(), haandtering_id);
    }












}
