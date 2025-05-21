package com.example.tp6.Repository;

import com.example.tp6.Entitie.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}
