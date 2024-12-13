package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Kunde;
import com.example.bilabonnement.Service.KundeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.time.LocalDate;

@Controller
public class KundeController {

    @Autowired
    KundeService kundeService;

    @GetMapping("/manageKunder")
    public String allKunder(Model model) throws SQLException {
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

    @GetMapping("/redigerKunde/{telefonnummer}")
    public String redigerKunde(@PathVariable ("telefonnummer") String telefonnummer, Model model) throws SQLException {
        //Kunde kunde=kundeService.getKundeByTelefon(telefonnummer);
        model.addAttribute("kunde", kundeService.getKundeByTelefon(telefonnummer));
        return "homeKunde/redigerKunde";
    }

    @PostMapping("/redigerKunde")
    public String redigerKunde(@ModelAttribute Kunde kunde) {
        kundeService.redigerKunde(kunde);
        return "redirect:/manageKunder";
    }
}
