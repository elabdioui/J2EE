package com.enset.eboutique.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {
    private double decouvert;

    // Constructeurs
    public CompteCourant() {}

    public CompteCourant(Date dateCreation, double solde, Client client, Employe employe, double decouvert) {
        super(dateCreation, solde, client, employe);
        this.decouvert = decouvert;
    }

    // Getters et Setters
    public double getDecouvert() { return decouvert; }
    public void setDecouvert(double decouvert) { this.decouvert = decouvert; }
}
