package com.produit.tp7;

import com.produit.tp7.entities.Categorie;
import com.produit.tp7.entities.Produit;
import com.produit.tp7.repository.CategoryRepository;
import com.produit.tp7.repository.ProduitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Tp7ApplicationTests {

    @Autowired
    private ProduitRepository produitRepository;
    private CategoryRepository categoryRepository;

    @Test
    public void TestCreateProduits(){
        Produit prod = new Produit("PC Portable", 1200.0, new java.util.Date());
         produitRepository.save(prod);

    }
    @Test
    public void TestFindByNomProduit(){
        List<Produit> prods = produitRepository.findByNomProduit("PC Portable");
        for (Produit p : prods)
        {
            System.out.println(p);
        }
    }

    @Test
    public void testfindByNomPrix()
    {
        List<Produit> prods = produitRepository.findByNomPrix("iphone X", 1000.0);
        for (Produit p : prods)
        {
            System.out.println(p);
        }
    }

    @Test
    public void testfindByCategorie(){
        Categorie cat = new Categorie();
        cat.setIdCat(1L);
        List<Produit> prods = produitRepository.findByCategorie(cat);
        for (Produit p : prods)
        {
            System.out.println(p);
        }
    }

    @Test
    public void testfindByOrderByNomProduitAsc(){
        List<Produit> prods = produitRepository.findByOrderByNomProduitAsc();
        for (Produit p : prods)
        {
            System.out.println(p);
        }
    }

}
