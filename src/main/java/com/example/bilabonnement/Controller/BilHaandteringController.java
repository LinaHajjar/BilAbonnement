package com.example.bilabonnement.Controller;


import com.example.bilabonnement.Model.BilHaandtering;
import com.example.bilabonnement.Service.BilHaandteringService;
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
public class BilHaandteringController {

    @Autowired
    BilHaandteringService bilHaandteringService;


    @GetMapping("/manageHaandteringer")
    public String allHaandteringer(Model model) throws SQLException {

        List<BilHaandtering> haandteringer = bilHaandteringService.bilHaandteringsList();
        model.addAttribute("bilhaandteringer", haandteringer);
        return "homeBilHaandtering/manageHaandteringer";
    }


    @GetMapping("/opretteUdlevering")

    public String nyUdlevereHaandtering(Model model) throws SQLException {
        return "homeBilHaandtering/nyUdlevering";
    }

    @PostMapping("/opretteUdlevering")
    public String opretteUdlevering(
            @RequestParam("lejekontrakt_id") int lejekontrakt_id, @RequestParam("nummerplade") String nummerplade, @RequestParam("handlingsdato") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate handlingsdato,
            @RequestParam("handlingslokation") String handlingslokation, @RequestParam("status") String status, Model model) throws SQLException {




    BilHaandtering.type convertedtype = BilHaandtering.type.UDLEVERING;

    BilHaandtering.status convertedstatus = BilHaandtering.status.valueOf(status.toUpperCase());


        BilHaandtering newHaandtering = new BilHaandtering();
        newHaandtering.setLejekontrakt_id(lejekontrakt_id);
        newHaandtering.setNummerplade(nummerplade);
        newHaandtering.setHandlingsdato(handlingsdato);
        newHaandtering.setHandlingsLokation(handlingslokation);
        newHaandtering.setType(convertedtype);
        newHaandtering.setStatus(convertedstatus);

        bilHaandteringService.opretteUdlevering(newHaandtering);

        model.addAttribute("message", "Udlevering oprettet successfully!");

        return "redirect:/manageBilHaandtering";
    }

    @GetMapping("/opretteReturnering")
    public String opretteReturnering(Model model) throws SQLException {
        return "homeBilHaandtering/nyReturnering";
    }

    @PostMapping("/opretteReturnering")
    public String nyReturHaandtering(@RequestParam("lejekontrakt_id") int lejekontrakt_id, @RequestParam("nummerplade") String nummerplade, @RequestParam("handlingsdato") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate handlingsdato,@RequestParam("handlingslokation")
    String handlingslokation, @RequestParam("status") String status, Model model) throws SQLException {


        BilHaandtering.type convertedtype = BilHaandtering.type.RETURNERING;

        BilHaandtering.status convertedstatus = BilHaandtering.status.valueOf(status.toUpperCase());


        BilHaandtering newHaandtering = new BilHaandtering();
        newHaandtering.setLejekontrakt_id(lejekontrakt_id);
        newHaandtering.setNummerplade(nummerplade);
        newHaandtering.setHandlingsdato(handlingsdato);
        newHaandtering.setHandlingsLokation(handlingslokation);
        newHaandtering.setType(convertedtype);
        newHaandtering.setStatus(convertedstatus);

        bilHaandteringService.opretteReturnering(newHaandtering);

        model.addAttribute("message", "Returnering oprettet successfully!");

        return "redirect:/manageBilHaandtering";
    }

//    @PostMapping("/opdaterStatus")
//    public String opdaterStatus(@RequestParam("status") String status,@RequestParam("haandteringid") int haandteringId, Model model) throws SQLException {
//        BilHaandtering.status convertedStatus = BilHaandtering.status.valueOf(status.toUpperCase());
//
//        bilHaandteringService.opdaterStatus(haandteringId, convertedStatus);
//
//        return "redirect:/manageBilHaandtering";
//    }


    //opdaterstatus
    @PostMapping("/opdaterStatus")
    public String opdaterStatus(@RequestParam("status") String status, @RequestParam("haandteringid") int haandteringId, Model model) throws SQLException {
        try {
            // Convert the string status to the enum
            BilHaandtering.status convertedStatus = BilHaandtering.status.valueOf(status.toUpperCase());

            // Call the repository to update the status
            bilHaandteringService.opdaterStatus(haandteringId, convertedStatus);

            model.addAttribute("message", "Status updated successfully!");
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", "Invalid status value!");
        }

        return "redirect:/manageBilHaandtering";
    }



}
