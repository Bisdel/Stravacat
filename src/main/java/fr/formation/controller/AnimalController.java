package fr.formation.controller;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import fr.formation.model.Animal;
import fr.formation.repo.IAnimalRepository;
import fr.formation.repo.IVilleRepository;
import jakarta.validation.Valid;

@Controller
public class AnimalController {
	@Autowired
	private IAnimalRepository repoAnimal;

	@Autowired
	private IVilleRepository repoVille;

	@GetMapping("/animal")
	public String findAll(Model model) {
		model.addAttribute("animaux", this.repoAnimal.findAll());	
		return "animal/profile";
	}
	
	@GetMapping("/animal/inscription")
	public String add() {
		return "animal/inscription";
	}
	
	@PostMapping({ "/animal/inscription" })
	public String add(@Valid Animal animal, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("erreurs", result);
			return "animal/inscription";
		}
		repoVille.save(animal.getVille());
		this.repoAnimal.save(animal);
		
		return "redirect:/animal";
	}
		
	@GetMapping("/animal/connexion")
	public String connexion() {
		return "animal/connexion";
	}

	@PostMapping({ "/animal/connexion" })
	public String connexion(@Valid Animal animal, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("erreurs", result);
			return "animal/connexion";
		}
		repoVille.save(animal.getVille());
		this.repoAnimal.save(animal);
		
		return "redirect:/animal";
	}
	
	// Ici il faudrait ajouter un préremplissage du formulaire de modification avec les
	// données de l'utilisateur actuel identifié via un cookie de session
	// normalement comme l'id n'est pas modifié ça va save dessus obligatoirement 
	// même si l'user change tout
	@PostMapping({ "/animal" })
	public String update(@Valid Animal animal, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("erreurs", result);
		}
		else {
			repoVille.save(animal.getVille());
			this.repoAnimal.save(animal);
		}
		return "animal";
	}
	
	@GetMapping("/animal/supprimer")
	public String deleteById(@SessionAttribute Session session) {
		try {
			// int id = session.getId
			// this.repoAnimal.deleteById(id);
		}
		
		catch (Exception ex) {
			return "redirect:/animal?erreursup";
		}
		
		return "redirect:/animal";
	}
}
