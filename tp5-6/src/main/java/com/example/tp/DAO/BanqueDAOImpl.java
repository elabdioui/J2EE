package com.example.tp.DAO;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.tp.model.*;

import java.util.List;

@Repository
@Transactional
public class BanqueDAOImpl implements IBanqueDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Client addClient(Client client) {
        em.persist(client);
        return client;
    }

    @Override
    public Employe addEmploye(Employe emp, Long codeSup) {
        if (codeSup != null) {
            Employe employeSup = em.find(Employe.class, codeSup);
            emp.setEmployeSup(employeSup);
        }
        em.persist(emp);
        return emp;
    }

    @Override
    public Groupe addGroupe(Groupe groupe) {
        em.persist(groupe);
        return groupe;
    }

    @Override
    public void addEmployeToGroupe(Long codeEmp, Long codeGroupe) {
        Employe emp = em.find(Employe.class, codeEmp);
        Groupe groupe = em.find(Groupe.class, codeGroupe);
        emp.getGroupes().add(groupe);
        groupe.getEmployes().add(emp);
    }

    @Override
    public Compte addCompte(Compte compte, Long codeClient, Long codeEmploye) {
        Client client = em.find(Client.class, codeClient);
        Employe emp = em.find(Employe.class, codeEmploye);
        compte.setClient(client);
        compte.setEmploye(emp);
        em.persist(compte);
        return compte;
    }

    @Override
    public Operation addOperation(Operation operation, String codeCompte, Long codeEmploye) {
        Compte compte = em.find(Compte.class, codeCompte);
        Employe emp = em.find(Employe.class, codeEmploye);
        operation.setCompte(compte);
        operation.setEmploye(emp);
        em.persist(operation);
        return operation;
    }

    @Override
    public Compte consulterCompte(String codeCompte) {
        Compte cp = em.find(Compte.class, codeCompte);
        if (cp == null) throw new RuntimeException("Compte introuvable !");
        return cp;
    }

    @Override
    public List<Operation> consulterOperations(String codeCompte) {
        Query req = em.createQuery("SELECT op FROM Operation op WHERE op.compte.codeCompte = :code");
        req.setParameter("code", codeCompte);
        return req.getResultList();
    }

    @Override
    public Client consulterClient(Long codeClient) {
        Client client = em.find(Client.class, codeClient);
        if (client == null) throw new RuntimeException("Client introuvable !");
        return client;
    }

    @Override
    public List<Client> consulterClients(String mc) {
        Query req = em.createQuery("SELECT cl FROM Client cl WHERE cl.nomClient LIKE :mc");
        req.setParameter("mc", "%" + mc + "%");
        return req.getResultList();
    }

    @Override
    public List<Compte> getComptesByClient(Long codeClient) {
        Query req = em.createQuery("SELECT cp FROM Compte cp WHERE cp.client.codeClient = :code");
        req.setParameter("code", codeClient);
        return req.getResultList();
    }

    @Override
    public List<Compte> getComptesByEmploye(Long codeEmploye) {
        Query req = em.createQuery("SELECT cp FROM Compte cp WHERE cp.employe.codeEmploye = :code");
        req.setParameter("code", codeEmploye);
        return req.getResultList();
    }

    @Override
    public List<Employe> getEmployes() {
        Query req = em.createQuery("SELECT e FROM Employe e");
        return req.getResultList();
    }

    @Override
    public List<Groupe> getGroupes() {
        Query req = em.createQuery("SELECT gr FROM Groupe gr");
        return req.getResultList();
    }

    @Override
    public List<Employe> getEmployesByGroupe(Long codeGroupe) {
        Query req = em.createQuery("SELECT emp FROM Employe emp JOIN emp.groupes g WHERE g.codeGroupe = :code");
        req.setParameter("code", codeGroupe);
        return req.getResultList();
    }
}
