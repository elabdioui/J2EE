package com.produit.tp7.repository;

import com.produit.tp7.entities.Categorie;
import com.produit.tp7.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(path = "rest")
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    List<Produit> findByNomProduit(String nom);
    List<Produit> findByNomProduitContains(String nom);

    @Query("select p from Produit p where p.nomProduit like %?1 and p.prixProduit > ?2")
    List<Produit> findByNomPrix (String nom, Double prix);

    @Query("select p from Produit p where p.categorie = ?1")
    List<Produit> findByCategorie (Categorie categorie);

    @Query("select p from Produit p order by p.nomProduit ASC")
    List<Produit> findByOrderByNomProduitAsc();

    @Query("select p from Produit p order by p.nomProduit ASC, p.prixProduit DESC")
    List<Produit> trierProduitsNomsPrix();

    @Query("select p from Produit p where p.categorie.idCat = ?1")
    List<Produit> findByCategorieIdCat(Long id);
}
