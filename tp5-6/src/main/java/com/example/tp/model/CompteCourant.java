package com.example.tp.model;

import java.util.Collection;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {

    @Column(name = "DECOUVERT")
    private double decouvert;

	public CompteCourant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }


	public CompteCourant(String codeCompte, Date dateCreation, double solde, Client client, Employe employe,
			Collection<Operation> operations, double decouvert) {
		super(codeCompte, dateCreation, solde, client, employe, operations);
		this.decouvert = decouvert;
	}

    // Getters and setters
    
}