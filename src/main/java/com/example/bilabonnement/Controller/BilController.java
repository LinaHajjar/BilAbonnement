package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Bil;
import com.example.bilabonnement.Model.LejeKontrakt;
import com.example.bilabonnement.Model.Skader;
import com.example.bilabonnement.Service.BilService;
import com.example.bilabonnement.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class BilController {

    @Autowired
    BilService bilService;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/manageBiler")
    public String allBiller(Model model) throws SQLException {
        model.addAttribute("biler", bilService.getAllBil());
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
        return "homeBil/ledigeBiler";

    }


    @GetMapping("/nyBil")
    public String nyBil(Model model) throws SQLException {
        model.addAttribute("bil", new Bil());
        return "homeBil/tilføjBil";
    }

    @PostMapping("/addBil")
    public String opretteBil(@RequestParam("nummerplade") String nummerplade,
                             @RequestParam("maerke") String maerke,
                             @RequestParam("model") String model,
                             @RequestParam("braendstoftype") String braendstoftype,
                             @RequestParam("odometer") int odometer,
                             @RequestParam("foersteregistrering") String foersteregistrering,
                             @RequestParam("co2udledning") int co2udledning) throws SQLException {


        Bil.Braendstoftype convertedBraendstoftype = Bil.Braendstoftype.valueOf(braendstoftype.toUpperCase());


        // Convert date til LocalDate
        LocalDate date = LocalDate.parse(foersteregistrering);

        bilService.opretteBil(nummerplade, maerke, model, convertedBraendstoftype, odometer, date, co2udledning);


        return "redirect:/manageBiler";
    }

    @PostMapping("/sletBil")
    public String sletBil(@RequestParam("nummerplade") String nummerplade) throws SQLException{
        bilService.sletBil(nummerplade);
        return "redirect:/manageBiler";
    }









}
