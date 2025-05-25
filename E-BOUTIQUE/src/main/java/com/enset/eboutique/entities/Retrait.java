package com.enset.eboutique.entities;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation {

    // Constructeurs
    public Retrait() {}

    public Retrait(Date dateOperation, double montant, Compte compte, Employe employe) {
        super(dateOperation, montant, compte, employe);
    }
}
