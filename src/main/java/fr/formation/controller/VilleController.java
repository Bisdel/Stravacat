package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.formation.exception.VilleNotFoundException;
import fr.formation.model.Ville;
import fr.formation.repo.IVilleRepository;
import jakarta.validation.Valid;

@Controller
public class VilleController {

	@Autowired
	private IVilleRepository repoVille;

	@GetMapping("/ville")
	public String findAll(Model model) {
		model.addAttribute("villes", this.repoVille.findAll());

		return "ville/liste";
	}
	
	@GetMapping("/ville/ajouter")
	public String add() {
		return "ville/ajouter";
	}

	@PostMapping({ "/ville/ajouter", "/ville/modifier/{id}" })
	private String edit(@Valid Ville ville, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("erreurs", result);
			return "/ville/ajouter";
		}
		this.repoVille.save(ville);
		return "redirect:/ville";
	}

	@GetMapping("/ville/modifier/{id}")
	public String edit(@PathVariable int id, Model model) {
		Ville ville = this.repoVille.findById(id).orElseThrow(VilleNotFoundException::new);
		model.addAttribute("ville", ville);
		return "ville/ajouter";
	}

	@GetMapping("/ville/supprimer/{id}")
	public String deletById(@PathVariable int id) {
		try {
			this.repoVille.deleteById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "redirect:/ville?erreursup";
		}
		return "redirect:/ville";
	}
	
}
