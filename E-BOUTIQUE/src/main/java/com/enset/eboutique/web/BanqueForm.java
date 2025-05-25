package com.enset.eboutique.web;

public class BanqueForm {
    private Long codeClient;
    private Long codeEmploye;
    private String typeCompte;
    private double solde;
    private double decouvert;
    private double taux;

    // Constructeurs
    public BanqueForm() {}

    // Getters et Setters
    public Long getCodeClient() { return codeClient; }
    public void setCodeClient(Long codeClient) { this.codeClient = codeClient; }

    public Long getCodeEmploye() { return codeEmploye; }
    public void setCodeEmploye(Long codeEmploye) { this.codeEmploye = codeEmploye; }

    public String getTypeCompte() { return typeCompte; }
    public void setTypeCompte(String typeCompte) { this.typeCompte = typeCompte; }

    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }

    public double getDecouvert() { return decouvert; }
    public void setDecouvert(double decouvert) { this.decouvert = decouvert; }

    public double getTaux() { return taux; }
    public void setTaux(double taux) { this.taux = taux; }
}