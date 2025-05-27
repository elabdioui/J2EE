package com.produit.tp7.repository;

import com.produit.tp7.entities.Categorie;
import com.produit.tp7.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Categorie, Long> {

}
