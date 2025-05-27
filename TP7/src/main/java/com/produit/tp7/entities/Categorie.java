package com.produit.tp7.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCat;
    @NonNull
    private String nomCat;
    private String descriptionCat;
    @JsonIgnore
    @OneToMany(mappedBy = "categorie")
    private List<Produit> produits;

    public Categorie(String nomCat, String descriptionCat, List<Produit> produits) {

        this.nomCat = nomCat;
        this.descriptionCat = descriptionCat;
        this.produits = produits;
    }



}

