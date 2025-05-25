package com.example.tp.model;

import java.io.Serializable;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "CLIENTS")
public class Client implements Serializable {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODE_CLIENT")
    private Long codeClient;

    @Column(name = "NOM_CLIENT")
    private String nomClient;

    @Column(name = "PRENOM_CLIENT")
    private String prenomClient;

    @Column(name = "ADR_CLIENT")
    private String adresseClient;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Collection<Compte> comptes;
    
    
    public Client() {}
    
    public Client(Long codeClient, String nomClient, String prenomClient, String adresseClient,
			Collection<Compte> comptes) {
		super();
		this.codeClient = codeClient;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.adresseClient = adresseClient;
		this.comptes = comptes;
	}

	public Long getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(Long codeClient) {
		this.codeClient = codeClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getPrenomClient() {
		return prenomClient;
	}

	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}

	public String getAdresseClient() {
		return adresseClient;
	}

	public void setAdresseClient(String adresseClient) {
		this.adresseClient = adresseClient;
	}

	public Collection<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}
}