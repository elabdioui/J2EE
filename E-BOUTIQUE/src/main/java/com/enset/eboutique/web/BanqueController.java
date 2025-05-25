package com.enset.eboutique.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.enset.eboutique.entities.*;
import com.enset.eboutique.metier.IBanqueMetier;

@Controller
public class BanqueController {

    @Autowired
    private IBanqueMetier banqueMetier;

    @RequestMapping(value = "/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "3") int size,
                        @RequestParam(name = "chercher", defaultValue = "") String mc) {

        try {
            List<Client> clients = banqueMetier.consulterClientParMC(mc);
            model.addAttribute("listClients", clients);
            model.addAttribute("motCle", mc);
            model.addAttribute("size", size);
            model.addAttribute("pages", new int[clients.size() / size + 1]);
            model.addAttribute("pageCourante", page);
        } catch (Exception e) {
            model.addAttribute("exception", e);
        }
        return "clients";
    }

    @RequestMapping(value = "/comptes", method = RequestMethod.GET)
    public String comptes(Model model,
                          @RequestParam(name = "codeClient") Long codeClient) {
        try {
            Client client = banqueMetier.consulterClients().stream()
                    .filter(c -> c.getCodeClient().equals(codeClient))
                    .findFirst().orElse(null);
            List<Compte> comptes = banqueMetier.getCompteParClient(codeClient);
            model.addAttribute("client", client);
            model.addAttribute("listComptes", comptes);
        } catch (Exception e) {
            model.addAttribute("exception", e);
        }
        return "comptes";
    }

    @RequestMapping(value = "/operations", method = RequestMethod.GET)
    public String operations(Model model,
                             @RequestParam(name = "numCompte") Long numCompte,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "3") int size) {
        try {
            Compte compte = banqueMetier.consulterCompte(numCompte);
            List<Operation> operations = banqueMetier.consulterOperations(numCompte);
            model.addAttribute("compte", compte);
            model.addAttribute("listOperations", operations);
            model.addAttribute("size", size);
            model.addAttribute("pages", new int[operations.size() / size + 1]);
            model.addAttribute("pageCourante", page);
        } catch (Exception e) {
            model.addAttribute("exception", e);
        }
        return "operations";
    }
}