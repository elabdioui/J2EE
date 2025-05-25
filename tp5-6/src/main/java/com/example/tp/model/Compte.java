package com.example.tp.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "COMPTES")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_COMPTE", discriminatorType = DiscriminatorType.STRING, length = 4)
public class Compte implements Serializable {

    @Id
    @Column(name = "CODE_COMPTE")
    private String codeCompte;

    @Column(name = "DATE_CREATION")
    private Date dateCreation;

    @Column(name = "SOLDE")
    private double solde;

    @ManyToOne
    @JoinColumn(name = "CODE_CLIENT")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "CODE_EMP")
    private Employe employe;

    @OneToMany(mappedBy = "compte", fetch = FetchType.LAZY)
    private Collection<Operation> operations;
    
    public Compte() {}

	public Compte(String codeCompte, Date dateCreation, double solde, Client client, Employe employe,
			Collection<Operation> operations) {
		super();
		this.codeCompte = codeCompte;
		this.dateCreation = dateCreation;
		this.solde = solde;
		this.client = client;
		this.employe = employe;
		this.operations = operations;
	}

	public String getCodeCompte() {
		return codeCompte;
	}

	public void setCodeCompte(String codeCompte) {
		this.codeCompte = codeCompte;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public Collection<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}

    // Getters and setters
    
}