package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Bil;
import com.example.bilabonnement.Model.Kunde;
import com.example.bilabonnement.Model.LejeKontrakt;
import com.example.bilabonnement.Model.TopBil;
import com.example.bilabonnement.Repository.BilRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BilService {
    @Autowired
    BilRepo bilRepo; //objekt til rådighed

    public List<Bil> getAllBil() throws SQLException {
        List<Bil> biler = bilRepo.getAllBil();
        System.out.println(biler.get(0));
        return biler;
    }

    public List<String> alleNummerplader(){
        return bilRepo.alleNummerplader();
    }


    public int getAntalLejedeBiler (LocalDate fraDato, LocalDate tilDato) throws SQLException {
        return bilRepo.getAntalLejedeBiler(fraDato, tilDato);
    }

    public double getSamletIndtægt(LocalDate fraDato, LocalDate tilDato) throws SQLException {
        return bilRepo.getSamletIndtægt(fraDato, tilDato);
    }

    public TopBil getTopLejedeModeller(LocalDate fraDato, LocalDate tilDato) throws SQLException {
        return bilRepo.getTopLejedeModeller(fraDato, tilDato);
    }

}
