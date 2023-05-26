package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.formation.exception.AbonnesNotFoundException;
import fr.formation.model.Abonnes;
import fr.formation.repo.IAbonnesRepository;
import jakarta.validation.Valid;

@Controller
public class AbonneController {

	@Autowired
	private IAbonnesRepository repoAbonnes;

	@GetMapping("/abonne")
	public String findAll(Model model) {
		model.addAttribute("abonnes", this.repoAbonnes.findAll());

		return "abonne/listes";
	}
	
	@GetMapping("/abonne/ajouter")
	public String add() {
		return "abonne/ajouter";
	}

	@PostMapping({ "/abonne/ajouter", "/abonne/modifier/{id}" })
	public String add(@Valid Abonnes abonnes, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("erreurs", result);
			return "abonne/ajouter";
		}

		this.repoAbonnes.save(abonnes);

		return "redirect:/abonne";
	}

	@GetMapping("/abonne/modifier/{id}")
	public String edit(@PathVariable int id, Model model) {
		Abonnes abonne = this.repoAbonnes.findById(id).orElseThrow(AbonnesNotFoundException::new);
		model.addAttribute("abonne", abonne);
		return "abonne/ajouter";
	}

	@GetMapping("/abonne/supprimer/{id}")
	public String deleteById(@PathVariable int id) {
		try {
			this.repoAbonnes.deleteById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "redirect:/abonne?erreursup";
		}
		return "redirect:/abonne";
	}

}
