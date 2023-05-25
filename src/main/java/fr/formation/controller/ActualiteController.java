package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.formation.exception.ActualiteNotFoundException;
import fr.formation.model.Actualite;
import fr.formation.repo.IActualiteRepository;
import jakarta.validation.Valid;

@Controller
public class ActualiteController {
	@Autowired
	private IActualiteRepository repoActualite;
	
	@GetMapping("/actualite")
	public String findAll(Model model) {
		model.addAttribute("actualites", this.repoActualite.findAll());	
		return "actualite/liste-actu";
	}
	
	@GetMapping("/actualite/ajouter")
	public String add() {
		return "actualite/form-actu";
	}
	
	@PostMapping({ "/actualite/ajouter", "/actualite/modifier/{id}" })
	public String addOrEdit(@Valid Actualite actualite, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("erreurs", result);
			return "actualite/form-actu";
		}	
		this.repoActualite.save(actualite);
		
		return "redirect:/actualite";
	}
	
	@GetMapping("/actualite/modifier/{id}")
	public String edit(@PathVariable int id, Model model) {
		Actualite actualite = this.repoActualite.findById(id).orElseThrow(ActualiteNotFoundException::new);
		model.addAttribute("actualite", actualite);
		return "actualite/form";
	}
	
	@GetMapping("/actualite/supprimer/{id}")
	public String deleteById(@PathVariable int id) {
		try {
			this.repoActualite.deleteById(id);
		}
		
		catch (Exception ex) {
			return "redirect:/actualite?erreursup";
		}
		
		return "redirect:/actualite";
	}
}

