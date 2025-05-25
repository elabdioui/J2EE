package com.enset.eboutique.metier;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.enset.eboutique.dao.IBanqueDAO;
import com.enset.eboutique.entities.*;

@Service
public class BanqueMetierImpl implements IBanqueMetier {

    @Autowired
    private IBanqueDAO banqueDAO;

    @Override
    public Client addClient(Client c) {
        return banqueDAO.addClient(c);
    }

    @Override
    public Employe addEmploye(Employe e) {
        return banqueDAO.addEmploye(e);
    }

    @Override
    public Groupe addGroupe(Groupe g) {
        return banqueDAO.addGroupe(g);
    }

    @Override
    public void addEmployeToGroupe(Long codeEmp, Long codeGr) {
        banqueDAO.addEmployeToGroupe(codeEmp, codeGr);
    }

    @Override
    public Compte addCompte(Compte cp) {
        return banqueDAO.addCompte(cp);
    }

    @Override
    public Operation addOperation(Operation op) {
        return banqueDAO.addOperation(op);
    }

    @Override
    public Compte consulterCompte(Long numCompte) {
        return banqueDAO.consulterCompte(numCompte);
    }

    @Override
    public List<Compte> consulterComptes() {
        return banqueDAO.consulterComptes();
    }

    @Override
    public List<Client> consulterClients() {
        return banqueDAO.consulterClients();
    }

    @Override
    public List<Employe> consulterEmployes() {
        return banqueDAO.consulterEmployes();
    }

    @Override
    public List<Groupe> consulterGroupes() {
        return banqueDAO.consulterGroupes();
    }

    @Override
    public List<Compte> getCompteParClient(Long codeClient) {
        return banqueDAO.getCompteParClient(codeClient);
    }

    @Override
    public List<Compte> getCompteParEmploye(Long codeEmploye) {
        return banqueDAO.getCompteParEmploye(codeEmploye);
    }

    @Override
    public List<Employe> getEmployeParGroupe(Long codeGroupe) {
        return banqueDAO.getEmployeParGroupe(codeGroupe);
    }

    @Override
    public List<Client> consulterClientParMC(String mc) {
        return banqueDAO.consulterClientParMC(mc);
    }

    @Override
    public void verser(Long numCompte, double montant, Long codeEmp) {
        banqueDAO.verser(numCompte, montant, codeEmp);
    }

    @Override
    public void retirer(Long numCompte, double montant, Long codeEmp) {
        banqueDAO.retirer(numCompte, montant, codeEmp);
    }

    @Override
    public void virement(Long numCompte1, Long numCompte2, double montant, Long codeEmp) {
        banqueDAO.virement(numCompte1, numCompte2, montant, codeEmp);
    }

    @Override
    public List<Operation> consulterOperations(Long numCompte) {
        return banqueDAO.consulterOperations(numCompte);
    }
}
