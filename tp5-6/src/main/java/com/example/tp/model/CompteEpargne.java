package com.example.tp.model;

import java.util.Collection;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte {

    @Column(name = "TAUX_INTERET")
    private double tauxInteret;

	public double getTauxInteret() {
		return tauxInteret;
	}

	public void setTauxInteret(double tauxInteret) {
		this.tauxInteret = tauxInteret;
	}

	public CompteEpargne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompteEpargne(String codeCompte, Date dateCreation, double solde, Client client, Employe employe,
			Collection<Operation> operations, double tauxInteret) {
		super(codeCompte, dateCreation, solde, client, employe, operations);
		this.tauxInteret = tauxInteret;
	}
    

    // Getters and setters
}