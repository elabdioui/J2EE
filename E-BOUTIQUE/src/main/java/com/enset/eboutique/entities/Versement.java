package com.enset.eboutique.entities;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("V")
public class Versement extends Operation {

    // Constructeurs
    public Versement() {}

    public Versement(Date dateOperation, double montant, Compte compte, Employe employe) {
        super(dateOperation, montant, compte, employe);
    }
}
