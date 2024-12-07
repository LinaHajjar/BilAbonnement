package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Bil;
import com.example.bilabonnement.Model.Kunde;
import com.example.bilabonnement.Model.LejeKontrakt;
import com.example.bilabonnement.Repository.BilRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
        return bilRepo.getAllBil();
    }

    public List<String> alleNummerplader(){
        return bilRepo.alleNummerplader();
    }


    public int getAntalLejedeBiler (LocalDate fraDato, LocalDate tilDato) throws SQLException {
        return bilRepo.getAntalLejedeBiler(fraDato, tilDato);
    }

}
