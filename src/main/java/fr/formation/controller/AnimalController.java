package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.formation.exception.AnimalNotFoundException;
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
	
	@PostMapping({ "/animal/inscription", "/animal/modifier/{id}" })
	public String add(@Valid Animal animal, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("erreurs", result);
			return "animal/inscription";
		}
		repoVille.save(animal.getVille());
		this.repoAnimal.save(animal);
		
		return "redirect:/animal";
	}
	
	@GetMapping("/animal/modifier/{id}")
	public String edit(@PathVariable int id, Model model) {
		Animal animal = this.repoAnimal.findById(id).orElseThrow(AnimalNotFoundException::new);
		model.addAttribute("animal", animal);
		return "animal/form";
	}
	
	
	@GetMapping("/animal/supprimer/{id}")
	public String deleteById(@PathVariable int id) {
		try {
			this.repoAnimal.deleteById(id);
		}
		
		catch (Exception ex) {
			return "redirect:/animal?erreursup";
		}
		
		return "redirect:/animal";
	}
}
