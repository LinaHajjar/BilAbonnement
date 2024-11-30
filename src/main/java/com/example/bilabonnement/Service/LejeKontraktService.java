package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.LejeKontrakt;
import com.example.bilabonnement.Repository.LejeKontraktRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LejeKontraktService {

    @Autowired
    LejeKontraktRepo lejeKontraktRepo;

    public List<LejeKontrakt> getAllLejeKontrakt() throws SQLException {
        return lejeKontraktRepo.getAllLejeKontrakt();
    }
}
