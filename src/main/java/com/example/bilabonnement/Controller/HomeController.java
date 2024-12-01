package com.example.bilabonnement.Controller;


import com.example.bilabonnement.Model.Kunde;
import com.example.bilabonnement.Model.LejeKontrakt;
import com.example.bilabonnement.Service.BilService;
import com.example.bilabonnement.Service.BilabonnementService;
import com.example.bilabonnement.Service.KundeService;
import com.example.bilabonnement.Service.LejeKontraktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.time.LocalDate;

@Controller
public class HomeController {

    @Autowired
    BilabonnementService bilabonnementService;
    @Autowired
    BilService bilService;
    @Autowired
    LejeKontraktService lejeKontraktService;
    @Autowired
    KundeService kundeService;





    //opretellse af getmapping metode for at vise alle biller
 @GetMapping("/manageBiler")
    public String allBiller(Model model) throws SQLException {
     model.addAttribute("manageBiler", bilService.getAllBil());
     return "manageBiler";
 }

 // oprettelse af getmapping metode for at vise alle lejekontrakter
 @GetMapping("/manageLejeKontrakter")
    public String allLejeKontrakter(Model model) throws SQLException{
     model.addAttribute("manageLejeKontrakter",lejeKontraktService.getAllLejeKontrakt());
     return "manageLejeKontrakter";

 }

    //oprettelse af postmaping metode for at sende input fra bruger omkring lejekontrakt info

    @PostMapping("/nyKontrakter")
    public String visLejeKontraktForm(@RequestParam("telefonnummer") String telefonnummer,
                                      @RequestParam("nummerplade") String nummerplade,
                                      @RequestParam("startdato") LocalDate startdato,
                                      @RequestParam("slutdato") LocalDate slutdato,
                                      @RequestParam("maxKm") int maxKm,
                                      @RequestParam("pris") int pris,
                                      Model model) throws SQLException {

        LejeKontrakt lejeKontrakt = lejeKontraktService.getKontraktByTelefon(telefonnummer);

        if (lejeKontrakt != null) {
            model.addAttribute("lejeKontrakt", lejeKontrakt);
            lejeKontrakt.setStartdato(startdato);
            lejeKontrakt.setSlutDato(slutdato);
            lejeKontrakt.setMaxKm(maxKm);
            lejeKontrakt.setPris(pris);


            boolean isUpdated= lejeKontraktService.opdaterLejeKontrakt(lejeKontrakt);

            if(isUpdated == true){
                model.addAttribute("besked", "Lejekontrakten er opdateret.");
                model.addAttribute("lejeKontrakt", lejeKontrakt);
                return "manageKontrakter";
            } else {
            model.addAttribute("besked", "opdatering af Lejekontrakten mislykkes.");
            return "nyKontrakter";
            }
        }
         else {
            model.addAttribute("besked", "Ingen lejekontrakt fundet.");
            return "nyKontrakter";
        }
    }

    // oprettelse af getmapping metode for at vise alle lejekontrakter
    @GetMapping("/manageKunder")
    public String allKunder(Model model) throws SQLException{
        model.addAttribute("manageKunder", kundeService.getAllKunde());
        return "manageKunder";
    }


    //oprettelse af postmaping metode for at sende input fra bruger omkring kunde info

    @PostMapping("/nyKunde")
    public String visKundeForm(@RequestParam("telefonnummer") String telefonnummer,
    @RequestParam("email") String email,
    @RequestParam("fornavn") String fornavn,
    @RequestParam("efternavn") String efternavn,
    @RequestParam("adresse") String adresse,
    @RequestParam("postnummer") int postnummer,
    @RequestParam("byen") String byen,
    @RequestParam("Koerekortnummer") String koerkortnummer,
    @RequestParam("udstedelsdato") LocalDate udstedelsdato,
    Model model) throws SQLException {

        Kunde kunde = kundeService.getKundeByTelefon(telefonnummer);

        if (kunde != null) {
            model.addAttribute("kunde", kunde);
            kunde.setEmail(email);
            kunde.setFornavn(fornavn);
            kunde.setEfternavn(efternavn);
            kunde.setAdresse(adresse);
            kunde.setPostnummer(postnummer);
            kunde.setByen(byen);
            kunde.setKoerekortnummer(koerkortnummer);
            kunde.setUdstedelsdato(udstedelsdato);

            boolean isUpdated= kundeService.opdaterKundeInfo(kunde);

            if(isUpdated == true){
                model.addAttribute("besked", "Kundens oplysninger er opdateret.");
                model.addAttribute("kunde", kunde);
                return "manageKunder";
            } else {
                model.addAttribute("besked", "opdatering af kundens oplysninger mislykkes.");
                return "nyKunde";
            }
        }
        else {
            model.addAttribute("besked", "Kunde var ikke fundet.");
            return "nyKunde";
        }


    }


}









//opretellse af postmapping metode for at sende input fra bruger omkring kunde info




