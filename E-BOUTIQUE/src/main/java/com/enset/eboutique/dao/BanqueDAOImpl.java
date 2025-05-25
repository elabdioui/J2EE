package com.enset.eboutique.dao;



import java.util.Date;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.enset.eboutique.entities.*;

@Repository
@Transactional
public class BanqueDAOImpl implements IBanqueDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Client addClient(Client c) {
        em.persist(c);
        return c;
    }

    @Override
    public Employe addEmploye(Employe e) {
        em.persist(e);
        return e;
    }

    @Override
    public Groupe addGroupe(Groupe g) {
        em.persist(g);
        return g;
    }

    @Override
    public void addEmployeToGroupe(Long codeEmp, Long codeGr) {
        Employe emp = em.find(Employe.class, codeEmp);
        Groupe gr = em.find(Groupe.class, codeGr);
        emp.getGroupes().add(gr);
        gr.getEmployes().add(emp);
    }

    @Override
    public Compte addCompte(Compte cp) {
        em.persist(cp);
        return cp;
    }

    @Override
    public Operation addOperation(Operation op) {
        em.persist(op);
        return op;
    }

    @Override
    public Compte consulterCompte(Long numCompte) {
        return em.find(Compte.class, numCompte);
    }

    @Override
    public List<Compte> consulterComptes() {
        Query req = em.createQuery("select c from Compte c");
        return req.getResultList();
    }

    @Override
    public List<Client> consulterClients() {
        Query req = em.createQuery("select c from Client c");
        return req.getResultList();
    }

    @Override
    public List<Employe> consulterEmployes() {
        Query req = em.createQuery("select e from Employe e");
        return req.getResultList();
    }

    @Override
    public List<Groupe> consulterGroupes() {
        Query req = em.createQuery("select g from Groupe g");
        return req.getResultList();
    }

    @Override
    public List<Compte> getCompteParClient(Long codeClient) {
        Query req = em.createQuery("select c from Compte c where c.client.codeClient=:x");
        req.setParameter("x", codeClient);
        return req.getResultList();
    }

    @Override
    public List<Compte> getCompteParEmploye(Long codeEmploye) {
        Query req = em.createQuery("select c from Compte c where c.employe.codeEmploye=:x");
        req.setParameter("x", codeEmploye);
        return req.getResultList();
    }

    @Override
    public List<Employe> getEmployeParGroupe(Long codeGroupe) {
        Query req = em.createQuery("select e from Employe e join e.groupes g where g.codeGroupe=:x");
        req.setParameter("x", codeGroupe);
        return req.getResultList();
    }

    @Override
    public List<Client> consulterClientParMC(String mc) {
        Query req = em.createQuery("select c from Client c where c.nomClient like :x");
        req.setParameter("x", "%" + mc + "%");
        return req.getResultList();
    }

    @Override
    public void verser(Long numCompte, double montant, Long codeEmp) {
        Compte cp = consulterCompte(numCompte);
        Employe emp = em.find(Employe.class, codeEmp);
        Versement v = new Versement(new Date(), montant, cp, emp);
        addOperation(v);
        cp.setSolde(cp.getSolde() + montant);
    }

    @Override
    public void retirer(Long numCompte, double montant, Long codeEmp) {
        Compte cp = consulterCompte(numCompte);
        Employe emp = em.find(Employe.class, codeEmp);
        if (cp instanceof CompteCourant) {
            CompteCourant cc = (CompteCourant) cp;
            if (cp.getSolde() + cc.getDecouvert() < montant)
                throw new RuntimeException("Solde insuffisant");
        } else {
            if (cp.getSolde() < montant)
                throw new RuntimeException("Solde insuffisant");
        }
        Retrait r = new Retrait(new Date(), montant, cp, emp);
        addOperation(r);
        cp.setSolde(cp.getSolde() - montant);
    }

    @Override
    public void virement(Long numCompte1, Long numCompte2, double montant, Long codeEmp) {
        retirer(numCompte1, montant, codeEmp);
        verser(numCompte2, montant, codeEmp);
    }

    @Override
    public List<Operation> consulterOperations(Long numCompte) {
        Query req = em.createQuery("select o from Operation o where o.compte.numeroCompte=:x order by o.dateOperation desc");
        req.setParameter("x", numCompte);
        return req.getResultList();
    }
}
