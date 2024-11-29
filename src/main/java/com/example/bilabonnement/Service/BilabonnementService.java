package com.example.bilabonnement.Service;

import com.example.bilabonnement.Repository.BilabonnementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BilabonnementService {


    @Autowired
    BilabonnementRepo bilabonnementRepo;


    public void addKunde(){
        bilabonnementRepo.addKunde();
    }


    public void addLejekontrakt(){
        bilabonnementRepo.addLejekontrakt();
    }
}
