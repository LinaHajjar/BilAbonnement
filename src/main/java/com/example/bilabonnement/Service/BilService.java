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
        //List<Bil> biler = bilRepo.getAllBil();
        //System.out.println(biler.get(0));
        return (bilRepo.getAllBil());
    }

    public List<String> alleNummerplader(){
        return bilRepo.alleNummerplader();
    }


    public double getSamletIndtægt(LocalDate fraDato, LocalDate tilDato) throws SQLException {
        return bilRepo.getSamletIndtægt(fraDato, tilDato);
    }

    public TopBil getTopLejedeModeller(LocalDate fraDato, LocalDate tilDato) throws SQLException {
        return bilRepo.getTopLejedeModeller(fraDato, tilDato);
    }

    public void opretteBil(String nummerplade, String  maerke, String model, Bil.Braendstoftype
            braendstoftype, int odometer, LocalDate foersteregistrering, int co2udledning) throws SQLException {
         bilRepo.opretteBil(nummerplade, maerke, model, braendstoftype, odometer, foersteregistrering, co2udledning);
    }


    public Bil getBilByNummerplade(String nummerplade) throws SQLException {
        return bilRepo.getBilByNummerplade(nummerplade);
    }

    public void redigerBil(Bil bil){
        bilRepo.redigerBil(bil);
    }


}
