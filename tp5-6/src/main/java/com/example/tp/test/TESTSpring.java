package com.example.tp.test;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.tp.DAO.IBanqueDAO;
import com.example.tp.DAO.IBanqueMetier;
import com.example.tp.model.*; // Assure-toi d'importer tes entités (Client, Employe, etc.)

public class TESTSpring {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
        		new ClassPathXmlApplicationContext("root-context.xml");

        IBanqueMetier metier = (IBanqueMetier) context.getBean("metier");

        metier.addClient(new Client(null, "nomClient1", "pClient1", "adrClient1", null));
        metier.addClient(new Client(null, "nomClient2", "pClient2", "adrClient2", null));

        metier.addEmploye(new Employe(null, "E1", null, null), null);
        metier.addEmploye(new Employe(null, "E2", null, null), 1L);
        metier.addEmploye(new Employe(null, "E3", null, null), 1L);

        metier.addGroupe(new Groupe(null, "G1", null));
        metier.addGroupe(new Groupe(null, "G2", null));

        metier.addEmployeToGroupe(1L, 1L);
        metier.addEmployeToGroupe(2L, 2L);
        metier.addEmployeToGroupe(3L, 2L);

        metier.addCompte(new CompteCourant("CC1", new Date(), 9000, null, null, null, 8000), 1L, 2L);
        metier.addCompte(new CompteEpargne("CE1", new Date(), 40000, null, null, null, 5.5), 2L, 2L);

        metier.versement("CC1", 2000, 2L);
        metier.retrait("CC1", 1000, 2L);
        metier.virement("CC1", "CE1", 4000, 2L);

        System.out.print("les insertions sont fait");

        context.close();
    }
}
