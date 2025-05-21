package com.example.tp6.Service;

import com.example.tp6.Entitie.Film;
import com.example.tp6.Repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService implements IFilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public List<Film> findAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public Film CreateFilm(Film f) {
        return filmRepository.save(f);
    }

    @Override
    public Film UpdateFilm(Film f) {
        return filmRepository.save(f);
    }

    @Override
    public void DeleteFilm(int id) {
        filmRepository.deleteById(id);
    }

    public Film findFilmById(int id) {
        return filmRepository.findById(id).orElse(null);
    }
}
