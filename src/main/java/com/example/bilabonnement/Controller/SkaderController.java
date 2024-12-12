package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Skader;
import com.example.bilabonnement.Service.SkaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@Controller
public class SkaderController {

    @Autowired
    private SkaderService skaderService;

    @GetMapping("/backToSkader")
    public String backToSkader(){
        return "redirect:/manageSkade";
    }


    @GetMapping("/manageSkade")
    public String allSkader(Model model) throws SQLException {
        model.addAttribute("skader", skaderService.getAllSkader());
        return "homeSkade/manageSkade";
    }


    @GetMapping("/nySkade")
    public String showSkaderForm() {
        return "homeSkade/nySkade";
    }

    @PostMapping("/nySkade")
    public String visSkaderForm(@RequestParam("lejekontrakt_id") int lejekontrakt_id, @RequestParam("skade_type")  String skade_type, @RequestParam("beskrivelse") String beskrivelse, @RequestParam("pris") int pris, Model model) throws SQLException {
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
