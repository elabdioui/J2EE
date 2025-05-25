package com.example.tp.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation {

	public Retrait() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Retrait(Date dateOperation, double montant, Compte compte, Employe employe) {
		super(dateOperation, montant, compte, employe);
		// TODO Auto-generated constructor stub
	}
	@Override
    public String toString() {
        return "Retrait";
    }

	
	
  
}