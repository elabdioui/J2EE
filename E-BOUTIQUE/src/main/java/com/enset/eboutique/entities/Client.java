package com.enset.eboutique.entities;

import jakarta.persistence.*;

import java.util.Collection;


@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeClient;
    private String nomClient;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Collection<Compte> comptes;

    // Constructeurs
    public Client() {}

    public Client(String nomClient) {
        this.nomClient = nomClient;
    }

    // Getters et Setters
    public Long getCodeClient() { return codeClient; }
    public void setCodeClient(Long codeClient) { this.codeClient = codeClient; }

    public String getNomClient() { return nomClient; }
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }

    public Collection<Compte> getComptes() { return comptes; }
    public void setComptes(Collection<Compte> comptes) { this.comptes = comptes; }
}