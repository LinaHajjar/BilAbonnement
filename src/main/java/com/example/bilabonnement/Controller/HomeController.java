package com.example.bilabonnement.Controller;


import com.example.bilabonnement.Model.Kunde;
import com.example.bilabonnement.Model.LejeKontrakt;
import com.example.bilabonnement.Service.BilService;
import com.example.bilabonnement.Service.KundeService;
import com.example.bilabonnement.Service.LejeKontraktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.SQLException;
import java.time.LocalDate;

@Controller
public class HomeController {

    @Autowired
    BilService bilService;
    @Autowired
    LejeKontraktService lejeKontraktService;
    @Autowired
    KundeService kundeService;





    //opretellse af getmapping metode for at vise alle biller
 @GetMapping("/manageBiler")
    public String allBiller(Model model) throws SQLException {
     model.addAttribute("manageBiler", bilService.getAllBil()); // displayer alle biler ud fra metoden i bilservice
     return "homeBil/manageBiler";
 }

 // oprettelse af getmapping metode for at vise alle lejekontrakter
 @GetMapping("/manageKontrakter")
    public String allLejeKontrakter(Model model) throws SQLException{
     model.addAttribute("manageLejeKontrakter",lejeKontraktService.getAllLejeKontrakt());
     return "homeKontrakt/manageKontrakter";

 }

    //oprettelse af postmaping metode for at sende input fra bruger omkring lejekontrakt info

    @PostMapping("/nyKontrakter")
    public RedirectView visLejeKontraktForm(@RequestParam("telefonnummer") String telefonnummer,
                                            @RequestParam("nummerplade") String nummerplade,
                                            @RequestParam("startdato") LocalDate startdato,
                                            @RequestParam("slutdato") LocalDate slutdato,
                                            @RequestParam("maxKm") int maxKm,
                                            @RequestParam("pris") int pris,
                                            Model model) throws SQLException {

        LejeKontrakt kontrakt = new LejeKontrakt(); // der oprettes en lejekontrakt object med info fra brugeren
        kontrakt.setTelefonnummer(telefonnummer);
        kontrakt.setNummerplade(nummerplade);
        kontrakt.setStartdato(startdato);
        kontrakt.setSlutDato(slutdato);
        kontrakt.setMaxKm(maxKm);
        kontrakt.setPris(pris);
        lejeKontraktService.addLejekontrakt(kontrakt); // her sendes den videre til addlejekontrakt metoden
        return new RedirectView("/homeKontrakt/manageKontrakter");
    }

    // oprettelse af getmapping metode for at vise alle lejekontrakter
    @GetMapping("/manageKunder")
    public String allKunder(Model model) throws SQLException{
        model.addAttribute("manageKunder", kundeService.getAllKunde());
        return "homeKunde/manageKunder";
    }


    //oprettelse af postmaping metode for at sende input fra bruger omkring kunde info

    @PostMapping("/nyKunde")
    public RedirectView visKundeForm(@RequestParam("telefonnummer") String telefonnummer,
    @RequestParam("email") String email,
    @RequestParam("fornavn") String fornavn,
    @RequestParam("efternavn") String efternavn,
    @RequestParam("adresse") String adresse,
    @RequestParam("postnummer") int postnummer,
    @RequestParam("byen") String byen,
    @RequestParam("Koerekortnummer") String koerkortnummer,
    @RequestParam("udstedelsdato") LocalDate udstedelsdato,
    Model model) throws SQLException {



       Kunde kunde = new Kunde(); // der oprettes et kunde object
       kunde.setTelefonnummer(telefonnummer);
       kunde.setEmail(email);
       kunde.setFornavn(fornavn);
       kunde.setEfternavn(efternavn);
       kunde.setAdresse(adresse);
       kunde.setPostnummer(postnummer);
       kunde.setByen(byen);
       kunde.setKoerekortnummer(koerkortnummer);
       kunde.setUdstedelsdato(udstedelsdato);

        if (kundeService.phoneNumberExists(telefonnummer) == false) { // der tjekkes først om kunden eksisterer ved at bruge telefonnummeret
            kundeService.addKunde(kunde); // hvis kunden ikke eksisterer, oprettes kunden i tabellen
            return new RedirectView("/homeKunde/manageKunder");
        } else {
            return new RedirectView("/homeKunde/manageKunde");
        }

    }


}









//opretellse af postmapping metode for at sende input fra bruger omkring kunde info




