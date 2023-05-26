package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.formation.exception.AbonnementNotFoundException;
import fr.formation.model.Abonnement;
import fr.formation.repo.IAbonnementRepository;
import jakarta.validation.Valid;

@Controller
public class AbonnementController {

	@Autowired
	private IAbonnementRepository repoAbonnement;
	
	@GetMapping("/abonnement")
	public String findAll(Model model ) {
		model.addAttribute("abonnements" , this.repoAbonnement.findAll());
		
		return "abonnement/liste";
	}
	@GetMapping("/abonnement/ajouter")
	public String add() {
		return "abonnement/ajouter";
	}
	
	@PostMapping({"/abonnement/ajouter" ,"/abonnement/modifier/{id}"})
	public String add(@Valid Abonnement abonnement, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("erreurs" , result);
			return "abonnement/ajouter";
		}
		
		this.repoAbonnement.save(abonnement);
		
		return "redirect:/abonnement";
	}
	
	@GetMapping("/abonnement/modfier/{id}")
	public String edit(@PathVariable int id , Model model) {
		Abonnement abonnement = this.repoAbonnement.findById(id).orElseThrow(AbonnementNotFoundException :: new);
		model.addAttribute("abonnement", abonnement);
		return "abonnement/ajouter";
	}
	
	@GetMapping("/abonnement/supprimer/{id}")
	public String deleteById(@PathVariable int id) {
		try {
			this.repoAbonnement.deleteById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "redirect:/abonnement?erreursup";
		}
		
		return "redirect:/abonnement";
	}
}
