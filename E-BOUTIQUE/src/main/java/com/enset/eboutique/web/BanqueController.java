package com.enset.eboutique.web;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.enset.eboutique.entities.*;
import com.enset.eboutique.metier.IBanqueMetier;

@Controller
public class BanqueController {

    @Autowired
    private IBanqueMetier banqueMetier;

    @RequestMapping(value = {"/", "/index"})
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "5") int size,
                        @RequestParam(name = "chercher", defaultValue = "") String mc) {

        try {
            List<Client> clients;
            if (mc.isEmpty()) {
                clients = banqueMetier.consulterClients();
            } else {
                clients = banqueMetier.consulterClientParMC(mc);
            }

            // Calcul simple de pagination
            int totalClients = clients.size();
            int totalPages = (int) Math.ceil((double) totalClients / size);
            int startIndex = page * size;
            int endIndex = Math.min(startIndex + size, totalClients);

            List<Client> clientsPage = clients.subList(startIndex, Math.min(endIndex, totalClients));

            model.addAttribute("listClients", clientsPage);
            model.addAttribute("motCle", mc);
            model.addAttribute("size", size);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalClients", totalClients);

        } catch (Exception e) {
            model.addAttribute("exception", e);
            e.printStackTrace();
        }
        return "clients";
    }

    @RequestMapping(value = "/comptes", method = RequestMethod.GET)
    public String comptes(Model model,
                          @RequestParam(name = "codeClient") Long codeClient) {
        try {
            // Récupérer le client
            List<Client> allClients = banqueMetier.consulterClients();
            Client client = allClients.stream()
                    .filter(c -> c.getCodeClient().equals(codeClient))
                    .findFirst()
                    .orElse(null);

            if (client == null) {
                throw new RuntimeException("Client introuvable avec le code : " + codeClient);
            }

            List<Compte> comptes = banqueMetier.getCompteParClient(codeClient);
            model.addAttribute("client", client);
            model.addAttribute("listComptes", comptes);

        } catch (Exception e) {
            model.addAttribute("exception", e);
            e.printStackTrace();
        }
        return "comptes";
    }

    @RequestMapping(value = "/operations", method = RequestMethod.GET)
    public String operations(Model model,
                             @RequestParam(name = "numCompte") Long numCompte,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "5") int size) {
        try {
            Compte compte = banqueMetier.consulterCompte(numCompte);
            if (compte == null) {
                throw new RuntimeException("Compte introuvable avec le numéro : " + numCompte);
            }

            List<Operation> operations = banqueMetier.consulterOperations(numCompte);

            // Pagination
            int totalOperations = operations.size();
            int totalPages = (int) Math.ceil((double) totalOperations / size);
            int startIndex = page * size;
            int endIndex = Math.min(startIndex + size, totalOperations);

            List<Operation> operationsPage = operations.subList(startIndex, Math.min(endIndex, totalOperations));

            model.addAttribute("compte", compte);
            model.addAttribute("listOperations", operationsPage);
            model.addAttribute("size", size);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalOperations", totalOperations);

        } catch (Exception e) {
            model.addAttribute("exception", e);
            e.printStackTrace();
        }
        return "operations";
    }

    @RequestMapping(value = "/formCompte", method = RequestMethod.GET)
    public String formCompte(Model model, @RequestParam(name = "codeClient") Long codeClient) {
        try {
            List<Client> allClients = banqueMetier.consulterClients();
            Client client = allClients.stream()
                    .filter(c -> c.getCodeClient().equals(codeClient))
                    .findFirst()
                    .orElse(null);

            List<Employe> employes = banqueMetier.consulterEmployes();

            model.addAttribute("client", client);
            model.addAttribute("listEmployes", employes);
            model.addAttribute("banqueForm", new BanqueForm());

        } catch (Exception e) {
            model.addAttribute("exception", e);
        }
        return "formCompte";
    }

    @RequestMapping(value = "/saveCompte", method = RequestMethod.POST)
    public String saveCompte(@ModelAttribute BanqueForm banqueForm, Model model) {
        try {
            List<Client> allClients = banqueMetier.consulterClients();
            Client client = allClients.stream()
                    .filter(c -> c.getCodeClient().equals(banqueForm.getCodeClient()))
                    .findFirst()
                    .orElse(null);

            List<Employe> allEmployes = banqueMetier.consulterEmployes();
            Employe employe = allEmployes.stream()
                    .filter(e -> e.getCodeEmploye().equals(banqueForm.getCodeEmploye()))
                    .findFirst()
                    .orElse(null);

            if (client == null || employe == null) {
                throw new RuntimeException("Client ou employé introuvable");
            }

            Compte compte;
            if (banqueForm.getTypeCompte().equals("CC")) {
                compte = new CompteCourant(new Date(), banqueForm.getSolde(),
                        client, employe, banqueForm.getDecouvert());
            } else {
                compte = new CompteEpargne(new Date(), banqueForm.getSolde(),
                        client, employe, banqueForm.getTaux());
            }

            banqueMetier.addCompte(compte);
            return "redirect:/comptes?codeClient=" + banqueForm.getCodeClient();

        } catch (Exception e) {
            model.addAttribute("exception", e);
            return "formCompte";
        }
    }

    @RequestMapping(value = "/formOperation", method = RequestMethod.GET)
    public String formOperation(Model model, @RequestParam(name = "numCompte") Long numCompte) {
        try {
            Compte compte = banqueMetier.consulterCompte(numCompte);
            List<Employe> employes = banqueMetier.consulterEmployes();

            model.addAttribute("compte", compte);
            model.addAttribute("listEmployes", employes);
            model.addAttribute("banqueForm", new BanqueForm());

        } catch (Exception e) {
            model.addAttribute("exception", e);
        }
        return "formOperation";
    }

    @RequestMapping(value = "/saveOperation", method = RequestMethod.POST)
    public String saveOperation(@ModelAttribute BanqueForm banqueForm, Model model,
                                @RequestParam(name = "numCompte") Long numCompte,
                                @RequestParam(name = "typeOperation") String typeOperation) {
        try {
            if (typeOperation.equals("VERS")) {
                banqueMetier.verser(numCompte, banqueForm.getMontant(), banqueForm.getCodeEmploye());
            } else if (typeOperation.equals("RET")) {
                banqueMetier.retirer(numCompte, banqueForm.getMontant(), banqueForm.getCodeEmploye());
            }

            return "redirect:/operations?numCompte=" + numCompte;

        } catch (Exception e) {
            model.addAttribute("exception", e);
            return "formOperation";
        }
    }
}