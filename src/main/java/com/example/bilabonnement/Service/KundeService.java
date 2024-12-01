package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Kunde;
import com.example.bilabonnement.Repository.KundeRepo;
import com.example.bilabonnement.Repository.LejeKontraktRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class KundeService {
    @Autowired
    KundeRepo kundeRepo;


    public List<Kunde> getAllKunde() throws SQLException {
        return kundeRepo.getAllKunde();
    }

    //skal lave den her metode færdig
    public static Kunde findKundeTelefonnummer(String telefonnummer) {

        return KundeRepo.findKundeTlfnummer(telefonnummer);

    }

    //skal lave den
    public boolean opdaterKundeInfo(Kunde kunde) throws SQLException {
        return kundeRepo.opdaterKundeInfo(kunde);
    }


}
