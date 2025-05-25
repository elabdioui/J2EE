package com.example.tp.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "EMPLOYES")
public class Employe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODE_EMPLOYE")
    private Long codeEmploye;

    @Column(name = "NOM_EMPLOYE")
    private String nomEmploye;

    @ManyToOne
    @JoinColumn(name = "CODE_EMP_SUP")
    private Employe employeSup;

    @ManyToMany
    @JoinTable(
        name = "EMPLOYE_GROUPE",
        joinColumns = @JoinColumn(name = "CODE_EMPLOYE"),
        inverseJoinColumns = @JoinColumn(name = "CODE_GROUPE")
    )
    private Collection<Groupe> groupes;
    public Employe() {
    }

	public Employe(Long codeEmploye, String nomEmploye, Employe employeSup, Collection<Groupe> groupes) {
		super();
		this.codeEmploye = codeEmploye;
		this.nomEmploye = nomEmploye;
		this.employeSup = employeSup;
		this.groupes = groupes;
	}

	public Long getCodeEmploye() {
		return codeEmploye;
	}

	public void setCodeEmploye(Long codeEmploye) {
		this.codeEmploye = codeEmploye;
	}

	public String getNomEmploye() {
		return nomEmploye;
	}

	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}

	public Employe getEmployeSup() {
		return employeSup;
	}

	public void setEmployeSup(Employe employeSup) {
		this.employeSup = employeSup;
	}

	public Collection<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(Collection<Groupe> groupes) {
		this.groupes = groupes;
	}

   
}