package com.example.tp.DAO;

import com.example.tp.model.*;
import jakarta.transaction.Transactional;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {
    @Autowired
    private IBanqueDAO dao;

  


    @Override
    public Client addClient(Client client) {
        return dao.addClient(client);
    }

    @Override
    public Employe addEmploye(Employe emp, Long codeSup) {
        return dao.addEmploye(emp, codeSup);
    }

    @Override
    public Groupe addGroupe(Groupe groupe) {
        return dao.addGroupe(groupe);
    }

    @Override
    public void addEmployeToGroupe(Long codeEmp, Long codeGroupe) {
        dao.addEmployeToGroupe(codeEmp, codeGroupe);
    }

    @Override
    public Compte addCompte(Compte compte, Long codeClient, Long codeEmploye) {
        return dao.addCompte(compte, codeClient, codeEmploye);
    }

    @Override
    public void versement(String codeCompte, double montant, Long codeEmploye) {
        Compte cp = dao.consulterCompte(codeCompte);
        Versement versement = new Versement(new Date(), montant, cp,null);
        dao.addOperation(versement, codeCompte, codeEmploye);
        cp.setSolde(cp.getSolde() + montant);
    }

    @Override
    public void retrait(String codeCompte, double montant, Long codeEmploye) {
        Compte cp = dao.consulterCompte(codeCompte);
        Retrait retrait = new Retrait(new Date(), montant, cp, null);
        dao.addOperation(retrait, codeCompte, codeEmploye);
        cp.setSolde(cp.getSolde() - montant);
    }

    @Override
    public void virement(String codeCompte1, String codeCompte2, double montant, Long codeEmploye) {
        retrait(codeCompte1, montant, codeEmploye);
        versement(codeCompte2, montant, codeEmploye);
    }

    @Override
    public Compte consulterCompte(String codeCompte) {
        return dao.consulterCompte(codeCompte);
    }

    @Override
    public List<Operation> consulterOperations(String codeCompte) {
        return dao.consulterOperations(codeCompte);
    }

    @Override
    public Client consulterClient(Long codeClient) {
        return dao.consulterClient(codeClient);
    }

    @Override
    public List<Client> consulterClients(String mc) {
        return dao.consulterClients(mc);
    }

    @Override
    public List<Compte> getComptesByClient(Long codeClient) {
        return dao.getComptesByClient(codeClient);
    }

    @Override
    public List<Compte> getComptesByEmploye(Long codeEmploye) {
        return dao.getComptesByEmploye(codeEmploye);
    }

    @Override
    public List<Employe> getEmployes() {
        return dao.getEmployes();
    }

    @Override
    public List<Groupe> getGroupes() {
        return dao.getGroupes();
    }

    @Override
    public List<Employe> getEmployesByGroupe(Long codeGroupe) {
        return dao.getEmployesByGroupe(codeGroupe);
    }
}
