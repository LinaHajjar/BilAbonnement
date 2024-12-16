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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
public class LogInController {
    @Autowired
    BrugerService brugerService;

    @Autowired
    private SkaderService skaderService;

    @GetMapping("/manage")
    public String manage() {
        return "manage";
    }

    @GetMapping("/backToManage")
    public String backToManage() {
        return "manage";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("homeForretningsUdvikler/forretningsUdvikler")
    public String forretningsUdvikler(Model model) {
        // If you want to pass the role here too, you can do it like this:
        model.addAttribute("rolle", "ForretningsUdvikling-Team");
        return "homeForretningsUdvikler/forretningsUdvikler"; // Return the view for the ForretningsUdvikler page
    }

    @PostMapping("/loginInfo")
    public String loginInfo(@RequestParam("brugernavn") String brugernavn,
                            @RequestParam("kode") String kode,
                            Model model,
                            RedirectAttributes redirectAttributes,
                            HttpSession session) throws SQLException {

        List<Bruger> brugerList = brugerService.getAllUsers();
        boolean userFound = false; // For debugging

        for (Bruger bruger : brugerList) {
            String rolle = "";

            if (bruger.getBrugernavn().equals(brugernavn) && bruger.getKode().equals(kode)) {
                userFound = true;

                if (bruger.getAfdeling_id() == 1) {
                    rolle = "DataLejsalg";
                    session.setAttribute("rolle", rolle);
                    return "manage";
                } else if (bruger.getAfdeling_id() == 2) {
                    rolle = "ForretningsUdvikler";
                     session.setAttribute("rolle", rolle);
                    return "homeForretningsUdvikler/forretningsUdvikler";
                } else if (bruger.getAfdeling_id() == 3) {
                    model.addAttribute("skader", skaderService.getAllSkader());
                    rolle = "SkadeogRep";
                    session.setAttribute("rolle", rolle);
                    return "homeSkade/manageSkade";
                }
            }
        }

        // This part will be executed if no user is found
        if (!userFound) {
            System.out.println("User not found or incorrect credentials.");
            redirectAttributes.addFlashAttribute("wrongLogIn", "Dit brugernavn eller adgangskode er forkert.");
        }

        return "redirect:/"; // Redirect back to login page
    }

    @GetMapping("/logUd")
    public String logUd() {
        return "index";
    }
}
