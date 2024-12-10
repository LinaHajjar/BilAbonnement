package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Bruger;
import com.example.bilabonnement.Service.BrugerService;
import com.example.bilabonnement.Service.SkaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class LogInController {
    @Autowired
    BrugerService brugerService;
    @Autowired
    private SkaderService skaderService;


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


}
