package com.example.tp6.Service;

import com.example.tp6.Entitie.Film;

import java.util.List;

public interface IFilmService {

    public List<Film> findAllFilms();

    public Film CreateFilm(Film f);

    public Film UpdateFilm(Film f);

    public void DeleteFilm(int id);

    public Film findFilmById(int id);



}
