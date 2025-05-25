package com.enset.eboutique.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte {
    private double taux;

    // Constructeurs
    public CompteEpargne() {}

    public CompteEpargne(Date dateCreation, double solde, Client client, Employe employe, double taux) {
        super(dateCreation, solde, client, employe);
        this.taux = taux;
    }

    // Getters et Setters
    public double getTaux() { return taux; }
    public void setTaux(double taux) { this.taux = taux; }
}
