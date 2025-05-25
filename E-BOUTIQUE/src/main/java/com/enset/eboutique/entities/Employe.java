package com.enset.eboutique.entities;

import java.util.Collection;
import jakarta.persistence.*;

@Entity
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeEmploye;
    private String nomEmploye;

    @ManyToOne
    @JoinColumn(name = "CODE_SUP")
    private Employe superieur;

    @OneToMany(mappedBy = "superieur", fetch = FetchType.LAZY)
    private Collection<Employe> employes;

    @ManyToMany
    @JoinTable(name = "EMP_GR")
    private Collection<Groupe> groupes;

    @OneToMany(mappedBy = "employe", fetch = FetchType.LAZY)
    private Collection<Compte> comptes;

    @OneToMany(mappedBy = "employe", fetch = FetchType.LAZY)
    private Collection<Operation> operations;

    // Constructeurs
    public Employe() {}

    public Employe(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }

    // Getters et Setters
    public Long getCodeEmploye() { return codeEmploye; }
    public void setCodeEmploye(Long codeEmploye) { this.codeEmploye = codeEmploye; }

    public String getNomEmploye() { return nomEmploye; }
    public void setNomEmploye(String nomEmploye) { this.nomEmploye = nomEmploye; }

    public Employe getSuperieur() { return superieur; }
    public void setSuperieur(Employe superieur) { this.superieur = superieur; }

    public Collection<Employe> getEmployes() { return employes; }
    public void setEmployes(Collection<Employe> employes) { this.employes = employes; }

    public Collection<Groupe> getGroupes() { return groupes; }
    public void setGroupes(Collection<Groupe> groupes) { this.groupes = groupes; }

    public Collection<Compte> getComptes() { return comptes; }
    public void setComptes(Collection<Compte> comptes) { this.comptes = comptes; }

    public Collection<Operation> getOperations() { return operations; }
    public void setOperations(Collection<Operation> operations) { this.operations = operations; }
}