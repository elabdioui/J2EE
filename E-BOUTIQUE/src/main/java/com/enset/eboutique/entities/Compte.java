package com.enset.eboutique.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_COMPTE", discriminatorType = DiscriminatorType.STRING, length = 2)
public abstract class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroCompte;
    private Date dateCreation;
    private double solde;

    @ManyToOne
    @JoinColumn(name = "CODE_CLI")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "CODE_EMP")
    private Employe employe;

    @OneToMany(mappedBy = "compte", fetch = FetchType.LAZY)
    private Collection<Operation> operations;

    // Constructeurs
    public Compte() {}

    public Compte(Date dateCreation, double solde, Client client, Employe employe) {
        this.dateCreation = dateCreation;
        this.solde = solde;
        this.client = client;
        this.employe = employe;
    }

    // Getters et Setters
    public Long getNumeroCompte() { return numeroCompte; }
    public void setNumeroCompte(Long numeroCompte) { this.numeroCompte = numeroCompte; }

    public Date getDateCreation() { return dateCreation; }
    public void setDateCreation(Date dateCreation) { this.dateCreation = dateCreation; }

    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public Employe getEmploye() { return employe; }
    public void setEmploye(Employe employe) { this.employe = employe; }

    public Collection<Operation> getOperations() { return operations; }
    public void setOperations(Collection<Operation> operations) { this.operations = operations; }
}
