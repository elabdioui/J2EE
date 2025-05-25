package com.example.tp.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "GROUPES")
public class Groupe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODE_GROUPE")
    private Long codeGroupe;

    @Column(name = "NOM_GROUPE")
    private String nomGroupe;

    @ManyToMany(mappedBy = "groupes")
    private Collection<Employe> employes;
    public Groupe() {}

	public Groupe(Long codeGroupe, String nomGroupe, Collection<Employe> employes) {
		super();
		this.codeGroupe = codeGroupe;
		this.nomGroupe = nomGroupe;
		this.employes = employes;
	}

	public Long getCodeGroupe() {
		return codeGroupe;
	}

	public void setCodeGroupe(Long codeGroupe) {
		this.codeGroupe = codeGroupe;
	}

	public String getNomGroupe() {
		return nomGroupe;
	}

	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}

	public Collection<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(Collection<Employe> employes) {
		this.employes = employes;
	}

    // Getters and setters
    
}
