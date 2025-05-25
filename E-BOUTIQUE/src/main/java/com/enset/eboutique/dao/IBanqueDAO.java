package com.enset.eboutique.dao;

import java.util.List;
import com.enset.eboutique.entities.*;

public interface IBanqueDAO {
    public Client addClient(Client c);
    public Employe addEmploye(Employe e);
    public Groupe addGroupe(Groupe g);
    public void addEmployeToGroupe(Long codeEmp, Long codeGr);
    public Compte addCompte(Compte cp);
    public Operation addOperation(Operation op);
    public Compte consulterCompte(Long numCompte);
    public List<Compte> consulterComptes();
    public List<Client> consulterClients();
    public List<Employe> consulterEmployes();
    public List<Groupe> consulterGroupes();
    public List<Compte> getCompteParClient(Long codeClient);
    public List<Compte> getCompteParEmploye(Long codeEmploye);
    public List<Employe> getEmployeParGroupe(Long codeGroupe);
    public List<Client> consulterClientParMC(String mc);
    public void verser(Long numCompte, double montant, Long codeEmp);
    public void retirer(Long numCompte, double montant, Long codeEmp);
    public void virement(Long numCompte1, Long numCompte2, double montant, Long codeEmp);
    public List<Operation> consulterOperations(Long numCompte);
}
