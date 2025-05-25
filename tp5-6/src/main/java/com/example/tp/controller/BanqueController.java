package com.example.tp.controller;



import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.tp.DAO.IBanqueMetier;
import com.example.tp.model.Compte;
import com.example.tp.model.Operation;

import jakarta.validation.Valid;

@Controller
public class BanqueController {

    @Autowired
    private IBanqueMetier metier;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("banqueForm", new BanqueForm());
        System.out.println(">>>> INDEX PAGE CHARGEE <<<<");

        return "banque"; // Ce renvoie banque.jsp dans /WEB-INF/views/
    }


    @PostMapping("/chargerCompte")
    public String chargerCompte(@Valid BanqueForm bf, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "banque";
        }

        try {
            Compte cp = metier.consulterCompte(bf.getCode());
            System.out.print(cp.getSolde()) ;
            System.out.print("--------------------------------------------------------");
            bf.setTypeCompte(cp.getClass().getSimpleName());
            bf.setCompte(cp);
            List<Operation> operations = metier.consulterOperations(bf.getCode());
            bf.setOperations(operations);
        } catch (Exception e) {
            bf.setException(e.getMessage());
        }

        model.addAttribute("banqueForm", bf);
        return "banque";
    }

    @RequestMapping(value = "/saveOperation")
    public String saveOperation(@Valid BanqueForm bf, BindingResult bindingResult) {
        try {
            Compte cp = metier.consulterCompte(bf.getCode());
            bf.setTypeCompte(cp.getClass().getSimpleName());
            bf.setCompte(cp);

            if (bf.getAction() != null) {
                switch (bf.getTypeOperation()) {
                    case "VER":
                        metier.versement(bf.getCode(), bf.getMontant(), 1L);
                        break;
                    case "RET":
                        metier.retrait(bf.getCode(), bf.getMontant(), 1L);
                        break;
                    case "VIR":
                        metier.virement(bf.getCode(), bf.getCode2(), bf.getMontant(), 1L);
                        break;
                }
            }

            if (bindingResult.hasErrors()) {
                return "banque";
            }

        } catch (Exception e) {
            bf.setException(e.getMessage());
        }

        List<Operation> operations = metier.consulterOperations(bf.getCode());
        bf.setOperations(operations);

        return "banque";
    }
}
