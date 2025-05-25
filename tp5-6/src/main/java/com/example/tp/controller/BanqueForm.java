package com.example.tp.controller;



import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

import com.example.tp.model.Compte;
import com.example.tp.model.Operation;

public class BanqueForm {

    @NotEmpty
    @Size(min = 3, max = 10)
    private String code;

    private Compte compte;

    private String typeCompte;

    private String exception;

    private List<Operation> operations;

    private String typeOperation;

    @NotEmpty
    @Size(min = 3, max = 10)
    private String code2 = "XXX";

    @DecimalMin(value = "50")
    private double montant = 50;

    private String action;

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public String getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(String typeCompte) {
        this.typeCompte = typeCompte;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

