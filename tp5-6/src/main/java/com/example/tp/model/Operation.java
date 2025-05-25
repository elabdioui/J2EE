package com.example.tp.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "OPERATIONS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_OPERATION", discriminatorType = DiscriminatorType.STRING, length = 4)
public class Operation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUMERO_OPERATION")
    private Long numeroOperation;

    @Column(name = "DATE_OPERATION")
    private Date dateOperation;

    @Column(name = "MONTANT")
    private double montant;

    @ManyToOne
    @JoinColumn(name = "CODE_COMPTE")
    private Compte compte;

    @ManyToOne
    @JoinColumn(name = "CODE_EMPLOYE")
    private Employe employe;
    public Operation() {}

	public Operation(Date dateOperation, double montant, Compte compte, Employe employe) {
		super();
		this.dateOperation = dateOperation;
		this.montant = montant;
		this.compte = compte;
		this.employe = employe;
	}

	public Long getNumeroOperation() {
		return numeroOperation;
	}

	public void setNumeroOperation(Long numeroOperation) {
		this.numeroOperation = numeroOperation;
	}

	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
    
    // Getters and setters
}