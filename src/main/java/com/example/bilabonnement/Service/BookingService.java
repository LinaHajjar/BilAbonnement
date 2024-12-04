package com.example.bilabonnement.Service;


import com.example.bilabonnement.Model.Bil;
import com.example.bilabonnement.Model.LejeKontrakt;
import com.example.bilabonnement.Repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class BookingService {


    @Autowired
    BookingRepo bookingrepo;



    public List<Bil> getTilgaengeliBiler(Date startdato, Date slutdato) {
        return bookingrepo.getTilgaengeligBiler(startdato, slutdato);
    }


    public List<LejeKontrakt> seBiler(Date startdato, Date slutdato) {
        return bookingrepo.seBiler(startdato, slutdato);
    }





}
