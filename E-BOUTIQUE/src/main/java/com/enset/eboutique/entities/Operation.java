package com.enset.eboutique.entities;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_OP", discriminatorType = DiscriminatorType.STRING, length = 1)
public abstract class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroOperation;
    private Date dateOperation;
    private double montant;

    @ManyToOne
    @JoinColumn(name = "NUM_COMPTE")
    private Compte compte;

    @ManyToOne
    @JoinColumn(name = "CODE_EMP")
    private Employe employe;

    // Constructeurs
    public Operation() {}

    public Operation(Date dateOperation, double montant, Compte compte, Employe employe) {
        this.dateOperation = dateOperation;
        this.montant = montant;
        this.compte = compte;
        this.employe = employe;
    }

    // Getters et Setters
    public Long getNumeroOperation() { return numeroOperation; }
    public void setNumeroOperation(Long numeroOperation) { this.numeroOperation = numeroOperation; }

    public Date getDateOperation() { return dateOperation; }
    public void setDateOperation(Date dateOperation) { this.dateOperation = dateOperation; }

    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }

    public Compte getCompte() { return compte; }
    public void setCompte(Compte compte) { this.compte = compte; }

    public Employe getEmploye() { return employe; }
    public void setEmploye(Employe employe) { this.employe = employe; }
}
