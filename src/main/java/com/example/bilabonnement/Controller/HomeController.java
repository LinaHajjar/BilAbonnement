package com.example.bilabonnement.Controller;


import com.example.bilabonnement.Model.Bruger;
import com.example.bilabonnement.Model.Kunde;
import com.example.bilabonnement.Model.LejeKontrakt;
import com.example.bilabonnement.Model.Skader;
import com.example.bilabonnement.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    BilService bilService;
    @Autowired
    LejeKontraktService lejeKontraktService;
    @Autowired
    KundeService kundeService;
    @Autowired
    BrugerService brugerService;
    @Autowired
    private SkaderService skaderService;


    //opretellse af getmapping metode for at vise alle biller
 @GetMapping("/manageBiler")
    public String allBiller(Model model) throws SQLException {
     model.addAttribute("biler", bilService.getAllBil()); // displayer alle biler ud fra metoden i bilservice
     return "homeBil/manageBiler";
 }

 // oprettelse af getmapping metode for at vise alle lejekontrakter
 @GetMapping("/manageKontrakter")
    public String allLejeKontrakter(Model model) throws SQLException{
     model.addAttribute("lejekontrakter", lejeKontraktService.getAllLejeKontrakt());
     return "homeKontrakt/manageKontrakter";
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

        LejeKontrakt kontrakt = new LejeKontrakt(); // der oprettes en lejekontrakt object med info fra brugeren
        kontrakt.setTelefonnummer(telefonnummer);
        kontrakt.setNummerplade(nummerplade);
        kontrakt.setStartdato(startdato);
        kontrakt.setSlutdato(slutdato);
        kontrakt.setMaxKm(maxKm);
        kontrakt.setPris(pris);
        lejeKontraktService.addLejekontrakt(kontrakt); // her sendes den videre til addlejekontrakt metoden
        return "redirect:/manageKontrakter";
    }

    // oprettelse af getmapping metode for at vise alle lejekontrakter
    @GetMapping("/manageKunder")
    public String allKunder(Model model) throws SQLException{
        model.addAttribute("kunder", kundeService.getAllKunde());
        return "homeKunde/manageKunder";
    }


    @GetMapping("/manageSkad")
    public String allSkader(Model model) throws SQLException{
     model.addAttribute("Skader", skaderService.getAllSkader());
     return "homeSkad/manageSkad";
    }

    @GetMapping("/nySkad")
    public String showSkaderForm() {
        return "homeSkad/nySkad";
    }



    @PostMapping("/nySkad")
    public String visSkaderForm(@RequestParam("lejekontrakt_id") int lejekontrakt_id, @RequestParam("skade_type")  Skader.skade_type skade_type,@RequestParam("beskrivelse") String beskrivelse, @RequestParam("pris") int pris, Model model) throws SQLException {

        // Converted the String received from the request parameter to the corresponding skade_type enum value
//        Skader.skade_type skade_type = Skader.skade_type.valueOf(skadeTypeStr.toUpperCase()); //@RequestParam cannot directly handle the enum conversion by default

        Skader skad = new Skader();
        skad.setLejekontrakt_id(lejekontrakt_id);
        skad.setSkade_type(skade_type);
        skad.setBeskrivelse(beskrivelse);
        skad.setPris(pris);

        skaderService.addSkader(skad);

        return "redirect:/manageSkad";

    }


    //oprettelse af postmaping metode for at sende input fra bruger omkring kunde info

    @GetMapping("/nyKunde")
    public String nyKunde(){
     return "homeKunde/nyKunde";
    }


    @PostMapping("/tilføjKunde")
    public String visKundeForm(@RequestParam("telefonnummer") String telefonnummer,
                               @RequestParam("email") String email,
                               @RequestParam("fornavn") String fornavn,
                               @RequestParam("efternavn") String efternavn,
                               @RequestParam("adresse") String adresse,
                               @RequestParam("postnummer") int postnummer,
                               @RequestParam("byen") String byen,
                               @RequestParam("koerekortnummer") String koerkortnummer,
                               @RequestParam("udstedelsdato") LocalDate udstedelsdato,
                               Model model, RedirectAttributes redirectAttributes) throws SQLException {



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
            redirectAttributes.addFlashAttribute("registreret", "Kunde er blevet registreret");
            return "redirect:/manageKunder";
        } else {
            redirectAttributes.addFlashAttribute("error", "Kunde eksisterer i forvejen");
            return "redirect:/manageKunder";
        }

    }


    @GetMapping("/nyKontrakt")
    public String sletKontrakt(){
     return "homeKontrakt/nyKontrakt";
    }

    @PostMapping("/tilføjKontrakt")
    public String tilføjKontrakt(@RequestParam("telefonnummer") String telefonnummer,
                               @RequestParam("nummerplade") String nummerplade,
                               @RequestParam("startdato") LocalDate startdato,
                               @RequestParam("slutdato") LocalDate slutdato,
                               @RequestParam("maxKm") int maxKm,
                               @RequestParam("pris") int pris,
                                 RedirectAttributes redirectAttributes) throws SQLException {

        try {

            LejeKontrakt kontrakt = new LejeKontrakt();
            kontrakt.setTelefonnummer(telefonnummer);
            kontrakt.setNummerplade(nummerplade);
            kontrakt.setStartdato(startdato);
            kontrakt.setSlutdato(slutdato);
            kontrakt.setMaxKm(maxKm);
            kontrakt.setPris(pris);
            lejeKontraktService.addLejekontrakt(kontrakt);
            redirectAttributes.addFlashAttribute("oprettet", "Lejekontrakt er blevet oprettet."); // giver en message hvis lejekontrakt bliver oprettet
            return "redirect:/manageKontrakter";
        } catch (DataIntegrityViolationException ex) {
            // Handle the exception and add an error message
            redirectAttributes.addFlashAttribute("error",
                    "Systemet kunne ikke oprette kontrakten. Tjek om telefonnummeret og nummerpladen eksisterer"); // giver en message hvis lejekontrakt ikke bliver oprettet

            // Redirect back to the form or a page of your choice
            return "redirect:/manageKontrakter";
        }
    }


    @PostMapping("/sletKunde")
    public String sletKunde(@RequestParam("telefonnummer") String telefonnummer) throws SQLException {
        kundeService.sletKunde(telefonnummer);
        return "redirect:/manageKunder";
    }

    @PostMapping("/sletKontrakt")
    public String sletKontrakt(@RequestParam("lejekontrakt_id") int id) throws SQLException {

        lejeKontraktService.sletLejeKontract(id);
        return "redirect:/manageKontrakter";

    }

    @GetMapping("/")
    public String manage(){
        return "index";
    }

    @PostMapping("/loginInfo")
    public String loginInfo(@RequestParam("brugernavn") String brugernavn, @RequestParam("kode") String kode) throws SQLException {

        List<Bruger> brugerList = brugerService.getAllUsers();

        for (Bruger bruger : brugerList) {

            if(bruger.getBrugernavn().equals(brugernavn) && bruger.getKode().equals(kode)) {

                if (bruger.getAfdeling_id() == 1){
                    return "manage";
                } else if(bruger.getAfdeling_id() == 2){
                    return "statistik";
                } else if (bruger.getAfdeling_id() == 3){
                    return "homeSkad/manageSkad";
                } else {
                    return "index";
                }


            }
        }
        return "index";
    }

}









//opretellse af postmapping metode for at sende input fra bruger omkring kunde info




