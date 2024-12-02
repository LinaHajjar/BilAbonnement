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

    // Find lejekontrakt baseret på telefonnummer og nummerplade
    public LejeKontrakt getKontraktByTelefon(String telefonnummer) throws SQLException {
        return lejeKontraktRepo.getKontraktByTelefon(telefonnummer);
    }

    public boolean opdaterLejeKontrakt(LejeKontrakt lejeKontrakt) {
       return lejeKontraktRepo.opdaterLejeKontrakt(lejeKontrakt);
    }

    public void sletLejeKontract(int lejekontrakt_id) throws SQLException {
        lejeKontraktRepo.sletLejeKontract(lejekontrakt_id);
    }


    public void addLejekontrakt(LejeKontrakt lejeKontrakt){
        lejeKontraktRepo.addLejekontrakt(lejeKontrakt);
    }
}

