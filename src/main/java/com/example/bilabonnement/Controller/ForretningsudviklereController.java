package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.TopBil;
import com.example.bilabonnement.Service.BilService;
import com.example.bilabonnement.Service.LejeKontraktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.time.LocalDate;

@Controller
public class ForretningsudviklereController {

    @Autowired
    LejeKontraktService lejeKontraktService;

    @Autowired
    BilService bilService;

    @GetMapping("/antalLejedeBiler")
    public String antalLejedeBiler(Model model) {
        return "homeForretningsUdvikler/antalLejedeBiler";
    }

    @PostMapping("/antalLejedeBiler")
    public String antalLejedeBiler( @RequestParam("startdato") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startdato,
                                    @RequestParam("slutdato") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate slutdato, @RequestParam(value = "maerker",required = false) String selectedMaerke,  Model model) throws SQLException {

        int lejedeBiler = lejeKontraktService.getAntalBiler(startdato,slutdato); //method getTotalLejedeBiler skal laves i repo og service
        //List<String> maerker = lejeKontraktService.getBilMaerker();


        model.addAttribute("lejedeBiler", lejedeBiler);
        model.addAttribute("startdato", startdato);
        model.addAttribute("slutdato",slutdato);
        //model.addAttribute("maerker", maerker);
        //model.addAttribute("maerke", selectedMaerke);
        return "homeForretningsUdvikler/antalLejedeBiler";
    }

    @GetMapping("/samletIndtægt")
    public String samletIndtægt(Model model) {
        return "homeForretningsUdvikler/samletIndtægt";
    }

    @PostMapping("/samletIndtægt")
    public String samletIndtægt(@RequestParam("fraDato")LocalDate fraDato,@RequestParam("tilDato")LocalDate tilDato,  Model model)throws SQLException{

        Double samletIndtægt = bilService.getSamletIndtægt(fraDato, tilDato); //method getSamletIndtægter skal laves i repo og service

        if (samletIndtægt == null || samletIndtægt == 0.0){
            model.addAttribute("ingenIndtægt", "Der er ikke nogen indtægt indenfor denne dato.");
        } else {
            model.addAttribute("samletIndtægt", samletIndtægt);
        }


        model.addAttribute("fraDato", fraDato);
        model.addAttribute("tilDato", tilDato);

        return "homeForretningsUdvikler/samletIndtægt";
    }

    @GetMapping("/topLejedeModeller")
    public String topLejedeModeller(){
        return "homeForretningsUdvikler/topLejedeModeller";
    }

    @PostMapping("/topLejedeModeller")
    public String topLejedeModeller(@RequestParam("fraDato") LocalDate fraDato,
                                    @RequestParam("tilDato")LocalDate tilDato,  Model model)throws SQLException {

        try {
            TopBil topLejedeModel = bilService.getTopLejedeModeller(fraDato, tilDato);
            //System.out.println(topBil);
            String bilModel = topLejedeModel.getModel();
            String maerke = topLejedeModel.getMaerke();
            int antalLånt = topLejedeModel.getAntal();
            model.addAttribute("model", bilModel);
            model.addAttribute("maerke", maerke);
            model.addAttribute("antalLånt", antalLånt);

            return "homeForretningsUdvikler/topLejedeModeller";
        } catch (EmptyResultDataAccessException e){
            model.addAttribute("ingenTopBil", "Der er ikke udlånt nogle biler i denne periode");
            return "homeForretningsUdvikler/topLejedeModeller";

        }
    }


    @GetMapping("/homeForretningsUdvikler")
    public String homeForretningsUdvikler(){
        return "homeForretningsUdvikler/forretningsUdvikler";
    }

}
