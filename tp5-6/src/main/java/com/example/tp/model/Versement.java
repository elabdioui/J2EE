package com.example.tp.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("V")
public class Versement extends Operation {
	

	public Versement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Versement( Date dateOperation, double montant, Compte compte, Employe employe) {
		super(dateOperation, montant, compte, employe);
		// TODO Auto-generated constructor stub
	}
	@Override
    public String toString() {
        return "Versement";
    }

}

    // Hérite de tous les champs d'Operation
    // Getters and setters
