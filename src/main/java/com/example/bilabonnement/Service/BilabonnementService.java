package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Kunde;
import com.example.bilabonnement.Model.LejeKontrakt;
import com.example.bilabonnement.Repository.BilabonnementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BilabonnementService {


    @Autowired
    BilabonnementRepo bilabonnementRepo;


    public void addKunde(Kunde kunde){
        bilabonnementRepo.addKunde(kunde);
    }


    public void addLejekontrakt(LejeKontrakt lejeKontrakt){
        bilabonnementRepo.addLejekontrakt(lejeKontrakt);
    }
}
