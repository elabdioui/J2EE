package com.example.tp6.Controller;

import com.example.tp6.Entitie.Film;
import com.example.tp6.Service.ICategoryService;
import com.example.tp6.Service.IFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private IFilmService filmService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/")
    public String listFilms(Model model) {
        model.addAttribute("films", filmService.findAllFilms());
        return "Affiche";
    }

    @GetMapping("/details")
    public String detailsFilm(Model model, @RequestParam int id) {
        model.addAttribute("film", filmService.findFilmById(id));
        return "DetailsFilm";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("film", new Film());
        model.addAttribute("categories", categoryService.findAllCategories());
        return "AddFilm";
    }

    @PostMapping("/add")
    public String processAddFilm(@ModelAttribute Film film) {
        filmService.CreateFilm(film);
        return "redirect:/film/";
    }

    @GetMapping("/update")
    public String showUpdateForm(Model model, @RequestParam int id) {
        model.addAttribute("film", filmService.findFilmById(id));
        model.addAttribute("categories", categoryService.findAllCategories());
        return "UpdateFilm";
    }

    @PostMapping("/update")
    public String processUpdateFilm(@ModelAttribute Film film) {
        filmService.UpdateFilm(film);
        return "redirect:/film/";
    }

    @GetMapping("/delete")
    public String deleteFilm(@RequestParam int id) {
        filmService.DeleteFilm(id);
        return "redirect:/film/";
    }
}