package com.example.tp.DAO;

import java.util.List;
import com.example.tp.model.*;

public interface IBanqueMetier {

    Client addClient(Client client);

    Employe addEmploye(Employe emp, Long codeSup);

    Groupe addGroupe(Groupe groupe);

    void addEmployeToGroupe(Long codeEmp, Long codeGroupe);

    Compte addCompte(Compte compte, Long codeClient, Long codeEmploye);

    void versement(String codeCompte, double montant, Long codeEmploye);

    void retrait(String codeCompte, double montant, Long codeEmploye);

    void virement(String codeCompte1, String codeCompte2, double montant, Long codeEmploye);

    Compte consulterCompte(String codeCompte);

    List<Operation> consulterOperations(String codeCompte);

    Client consulterClient(Long codeClient);

    List<Client> consulterClients(String mc);

    List<Compte> getComptesByClient(Long codeClient);

    List<Compte> getComptesByEmploye(Long codeEmploye);

    List<Employe> getEmployes();

    List<Groupe> getGroupes();

    List<Employe> getEmployesByGroupe(Long codeGroupe);
}