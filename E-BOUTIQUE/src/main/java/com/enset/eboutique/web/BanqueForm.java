package com.enset.eboutique.web;

public class BanqueForm {
    private Long codeClient;
    private Long codeEmploye;
    private String typeCompte;
    private double solde;
    private double decouvert;
    private double taux;
    private double montant;
    private String typeOperation;

    // Constructeurs
    public BanqueForm() {}

    public BanqueForm(Long codeClient, Long codeEmploye, String typeCompte, double solde) {
        this.codeClient = codeClient;
        this.codeEmploye = codeEmploye;
        this.typeCompte = typeCompte;
        this.solde = solde;
    }

    // Getters et Setters
    public Long getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(Long codeClient) {
        this.codeClient = codeClient;
    }

    public Long getCodeEmploye() {
        return codeEmploye;
    }

    public void setCodeEmploye(Long codeEmploye) {
        this.codeEmploye = codeEmploye;
    }

    public String getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(String typeCompte) {
        this.typeCompte = typeCompte;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    @Override
    public String toString() {
        return "BanqueForm{" +
                "codeClient=" + codeClient +
                ", codeEmploye=" + codeEmploye +
                ", typeCompte='" + typeCompte + '\'' +
                ", solde=" + solde +
                ", decouvert=" + decouvert +
                ", taux=" + taux +
                ", montant=" + montant +
                ", typeOperation='" + typeOperation + '\'' +
                '}';
    }
}