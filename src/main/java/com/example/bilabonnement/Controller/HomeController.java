package com.example.bilabonnement.Controller;


import com.example.bilabonnement.Service.BilabonnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {

    @Autowired
    BilabonnementService bilabonnementService;


}
