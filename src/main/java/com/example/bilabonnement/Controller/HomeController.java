package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Bruger;
import com.example.bilabonnement.Model.Kunde;
import com.example.bilabonnement.Model.LejeKontrakt;
import com.example.bilabonnement.Model.Skader;
import com.example.bilabonnement.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
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
    @Autowired
    private BookingService bookingService;


    /* ---------------------------- Log in ---------------------------------*/

    @GetMapping("/manage")
    public String manage(){

        return "manage";
    }



    @GetMapping("/backToManage")
    public String backToManage() {return "manage";}





    @PostMapping("/loginInfo")
    public String loginInfo(@RequestParam("brugernavn") String brugernavn, @RequestParam("kode") String kode, Model model) throws SQLException {
        List<Bruger> brugerList = brugerService.getAllUsers();

        for (Bruger bruger : brugerList) {

            if(bruger.getBrugernavn().equals(brugernavn) && bruger.getKode().equals(kode)) {

                if (bruger.getAfdeling_id() == 1){
                    return "manage";
                } else if(bruger.getAfdeling_id() == 2){
                    return "homeForretningsUdvikler/forretningsUdvikler";
                } else if (bruger.getAfdeling_id() == 3){
                    model.addAttribute("skader", skaderService.getAllSkader());
                    return "homeSkade/manageSkade";
                } else {
                    return "index";
                }
            }
        }
        return "index";
    }


    @GetMapping("/logUd")
    public String logUd(){
        return "index";
    }





    /* ---------------------------- Kunder ---------------------------------*/


    @GetMapping("/manageKunder")
    public String allKunder(Model model) throws SQLException{
        model.addAttribute("kunder", kundeService.getAllKunde());
        return "homeKunde/manageKunder";
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


    @PostMapping("/sletKunde")
    public String sletKunde(@RequestParam("telefonnummer") String telefonnummer) throws SQLException {
        kundeService.sletKunde(telefonnummer);
        return "redirect:/manageKunder";
    }


    @GetMapping("/backToCustomer")
    public String backToCustomer(){

        return "redirect:/manageKunder";
    }


    @GetMapping("/nyKunde")
    public String nyKunde(){
        return "homeKunde/nyKunde";
    }


    /* ---------------------------- Bil ---------------------------------*/




    //opretellse af getmapping metode for at vise alle biller
 @GetMapping("/manageBiler")
    public String allBiller(Model model) throws SQLException {
     model.addAttribute("biler", bilService.getAllBil()); // displayer alle biler ud fra metoden i bilservice
     return "homeBil/manageBiler";
 }

    @PostMapping("/bilTilgaengelig")
    public String checkAvailability(
            @RequestParam("startdato") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startdato,
            @RequestParam("slutdato") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate slutdato,
            Model model
    ) throws SQLException {
        List<LejeKontrakt> availableCars = bookingService.seBiler(startdato, slutdato);
        model.addAttribute("availableCars", availableCars);
        return "ledigeBiler";

    }


    /* ---------------------------- Lejekontrakt ---------------------------------*/



 // oprettelse af getmapping metode for at vise alle lejekontrakter
 @GetMapping("/manageKontrakter")
    public String allLejeKontrakter(Model model) throws SQLException{
     model.addAttribute("lejekontrakter", lejeKontraktService.getAllLejeKontrakt());
     return "homeKontrakt/manageKontrakter";
 }

    //oprettelse af postmaping metode for at sende input fra bruger omkring lejekontrakt info


    @GetMapping("/nyKontrakt")
    public String nyKontrakt(Model model) throws SQLException {
        List<String> nummerpladeList = bilService.alleNummerplader();
        model.addAttribute("nummerplader", nummerpladeList);
        return "homeKontrakt/nyKontrakt";
    }

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

    @GetMapping("/nummerplade")
    public String nummerplade(Model model) throws SQLException {
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

            return "redirect:/manageKontrakter";
        }
    }


    @PostMapping("/sletKontrakt")
    public String sletKontrakt(@RequestParam("lejekontrakt_id") int id) throws SQLException {
        lejeKontraktService.sletLejeKontract(id);
        return "redirect:/manageKontrakter";
    }

    // til at søge alle kontrakter til det samme kunde (telefonnummer)
    @GetMapping("/soegLejekontraktTelefon")
    public String homemanageKontrakt() {
        return ("homeKontrakt/manageKontrakter");
    }


    @GetMapping("/backToKontrakt")
    public String backToKontrakt(Model model) {
        return "redirect:/manageKontrakter";
    }



    @PostMapping("/kundensKontrakter")
    public String kundensKontrakter(@RequestParam("telefonnummer") String telefonnummer, Model model, RedirectAttributes redirectAttributes) throws SQLException {

        List<LejeKontrakt> kontrakter = lejeKontraktService.findKontraktByTelefon(telefonnummer);

        if (kontrakter == null || kontrakter.isEmpty()){

            redirectAttributes.addFlashAttribute("ingenKontrakt", "der ikke tilknyttet nogle kontrakter til denne kunde, eller er kunden ikke oprettet");
            redirectAttributes.addFlashAttribute("nyKunde", true);
            return "redirect:/manageKontrakter";
        } else {
            model.addAttribute("kontrakter", kontrakter);
            return "homeKontrakt/kundensKontrakter";
        }

    }


    @GetMapping("/alleKontrakter")
    public String alleKontrakter(){

        return "redirect:/manageKontrakter";
    }


    /* ---------------------------- Booking ---------------------------------*/


    @GetMapping("/booking")
    public String book(){
        return "booking";
    }





    /* ---------------------------- Forretningsudviklere ---------------------------------*/



    @GetMapping("/antalLejedeBiler")
    public String antalLejedeBiler(Model model) {
        return "homeForretningsUdvikler/antalLejedeBiler";
    }

    @PostMapping("/antalLejedeBiler")
    public String antalLejedeBiler(@RequestParam("fraDato")LocalDate fraDato,@RequestParam("tilDato")LocalDate tilDato,  Model model) throws SQLException{
        int lejedeBiler = bilService.getAntalLejedeBiler(fraDato,tilDato); //method getTotalLejedeBiler skal laves i repo og service
        model.addAttribute("lejedeBiler", lejedeBiler);
        model.addAttribute("fraDato", fraDato);
        model.addAttribute("tilDato", tilDato);
        return "homeForretningsUdvikler/antalLejedeBiler";
    }

    @GetMapping("/samletIndtægt")
    public String samletIndtægt(Model model) {
        return "homeForretningsUdvikler/samletIndtægt";
    }

    @PostMapping("/samletIndtægt")
    public String samletIndtægt(@RequestParam("fraDato")LocalDate fraDato,@RequestParam("tilDato")LocalDate tilDato,  Model model)throws SQLException{
        double samletIndtægt = bilService.getSamletIndtægt(fraDato,tilDato); //method getSamletIndtægter skal laves i repo og service
        model.addAttribute("samletIndtægt", samletIndtægt);
        model.addAttribute("fraDato", fraDato);
        model.addAttribute("tilDato", tilDato);
        return "homeForretningsUdvikler/samletIndtægt";
    }
    @GetMapping("/topLejedeModeller")
    public String topLejedeModeller(Model model) {
        return "homeForretningsUdvikler/topLejedeModeller";
    }

    @PostMapping("/topLejedeModeller")
    public String topLejedeModeller(@RequestParam("fraDato")LocalDate fraDato,@RequestParam("tilDato")LocalDate tilDato,  Model model)throws SQLException {
        String modelBil= bilService.getTopLejedeModeller(fraDato,tilDato); //method getTopLejedeModeller skal laves i repo og service
        model.addAttribute("modelBil", modelBil);
        model.addAttribute("fraDato", fraDato);
        model.addAttribute("tilDato", tilDato);
        return "homeForretningsUdvikler/topLejedeModeller";
    }


    @GetMapping("/homeForretningsUdvikler")
    public String homeForretningsUdvikler(){
        return "homeForretningsUdvikler/forretningsUdvikler";
    }



    /* ---------------------------- Skader ---------------------------------*/


    @GetMapping("/backToSkader")
    public String backToSkader(){
        return "redirect:/manageSkade";
    }


    @GetMapping("/manageSkade")
    public String allSkader(Model model) throws SQLException{
        model.addAttribute("skader", skaderService.getAllSkader());
        return "homeSkade/manageSkade";
    }


    @GetMapping("/nySkade")
    public String showSkaderForm() {
        return "homeSkade/nySkade";
    }

    @PostMapping("/nySkade")
    public String visSkaderForm(@RequestParam("lejekontrakt_id") int lejekontrakt_id, @RequestParam("skade_type")  String skade_type,@RequestParam("beskrivelse") String beskrivelse, @RequestParam("pris") int pris, Model model) throws SQLException {
        //Converted the String received from the request parameter to the corresponding skade_type enum value
        //Skader.skade_type skade_type = Skader.skade_type.valueOf(skadeTypeStr.toUpperCase()); //@RequestParam cannot directly handle the enum conversion by default

        Skader skade = new Skader();
        skade.setLejekontrakt_id(lejekontrakt_id);
        skade.setSkade_type(skade_type);
        skade.setBeskrivelse(beskrivelse);
        skade.setPris(pris);

        skaderService.addSkader(skade);

        return "redirect:/manageSkade";
    }



    @PostMapping("/kundensSkader")
    public String kundensKontrakter(@RequestParam("lejekontrakt_id") int lejekontrakt_id, Model model, RedirectAttributes redirectAttributes) throws SQLException {
        model.addAttribute("Skader", skaderService.getSkaderById(lejekontrakt_id)); // finder alle skaderapporter ud fra en kundes lejekontrakt id
        return "homeSkade/kundensSkader"; // returnerer en ny page hvor den kundes skaderapporter bliver displayed
    }







}