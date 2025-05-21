package com.example.tp6.Controller;

import com.example.tp6.Entitie.Categorie;
import com.example.tp6.Service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categorie/")
public class CategorieController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/")
    public String all(Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        return "all";
    }

    @GetMapping("/Create")
    public String create(Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        return "create";
    }

    @PostMapping("/save")
    public String saveCategorie(@ModelAttribute Categorie categorie) {
        categoryService.CreateCategory(categorie);
        return "redirect:/categorie/";
    }
}
