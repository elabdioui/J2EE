package com.example.tp6.Service;

import com.example.tp6.Entitie.Categorie;
import com.example.tp6.Repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService implements ICategoryService {

    @Autowired
    private CategorieRepository categorieRepository;
    @Override
    public Categorie CreateCategory(Categorie c) {
        return categorieRepository.save(c);
    }

    @Override
    public List<Categorie> findAllCategories() {
        return categorieRepository.findAll();
    }
}
