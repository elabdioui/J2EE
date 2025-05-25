package com.enset.eboutique.entities;

import java.util.Collection;
import jakarta.persistence.*;

@Entity
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeGroupe;
    private String nomGroupe;

    @ManyToMany(mappedBy = "groupes", fetch = FetchType.LAZY)
    private Collection<Employe> employes;

    // Constructeurs
    public Groupe() {}

    public Groupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

    // Getters et Setters
    public Long getCodeGroupe() { return codeGroupe; }
    public void setCodeGroupe(Long codeGroupe) { this.codeGroupe = codeGroupe; }

    public String getNomGroupe() { return nomGroupe; }
    public void setNomGroupe(String nomGroupe) { this.nomGroupe = nomGroupe; }

    public Collection<Employe> getEmployes() { return employes; }
    public void setEmployes(Collection<Employe> employes) { this.employes = employes; }
}
