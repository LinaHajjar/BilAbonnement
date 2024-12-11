package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.LejeKontrakt;
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


}
