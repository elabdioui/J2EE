package com.example.tp6.Service;

import com.example.tp6.Entitie.Categorie;

import java.util.List;

public interface ICategoryService {

    public Categorie CreateCategory(Categorie c);

    public List<Categorie> findAllCategories();
}
