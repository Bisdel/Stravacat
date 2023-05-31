package fr.formation.controller;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import fr.formation.api.request.AnimalRequest;
import fr.formation.model.Animal;
import fr.formation.repo.IAnimalRepository;
import fr.formation.repo.IVilleRepository;
import fr.formation.request.AnimalLogin;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/animal")
public class AnimalController {
	@Autowired
	private IAnimalRepository repoAnimal;

	@Autowired
	private IVilleRepository repoVille;

	@GetMapping("/{id}")
	public String findById(@PathVariable int id, Model model) {
		Animal animal = repoAnimal.findById(id).get();
		model.addAttribute("animal", animal);

		return "animal/profile";
	}

	@PostMapping({ "/{id}" })
	public String update(@PathVariable int id, @Valid AnimalRequest animalRequest, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("failed", true);
			model.addAttribute("message", "La modification a échoué pour les raisons suivantes :");
			model.addAttribute("erreurs", result);
			return "animal/"+id;
		} else {
			repoVille.save(animalRequest.getVille());

			// recover password from db if not modified
			if (animalRequest.getPassword().length() == 0){
			animalRequest.setPassword(repoAnimal.findById(id).get().getPassword());
			}
			Animal animal = animalRequest.convertToAnimal();
			animal.setId(id);
			this.repoAnimal.save(animal);
			model.addAttribute("success", true);
			model.addAttribute("message", "Vos modifications ont bien été enregistrées !");
			return "animal/"+animal.getId();
		}
	}
	
	@GetMapping("/inscription")
	public String inscriptionGet() {
		return "animal/inscription";
	}

	@PostMapping("/inscription")
	public String inscriptionPost(@Valid Animal animal, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("erreurs", result);
			return "animal/inscription";
		}
		repoVille.save(animal.getVille());
		this.repoAnimal.save(animal);

		return "redirect:/animal";
	}

	@GetMapping("/connexion")
	public String connexionGet() {
		return "animal/connexion";
	}

	@PostMapping("/connexion")
	public String connexion(@Valid AnimalLogin animalLogin, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("erreurs", result);
			return "animal/connexion";
		}
		if (repoAnimal.findByPseudo(animalLogin.getPseudo()).isPresent()) {
			Animal animal = repoAnimal.findByPseudo(animalLogin.getPseudo()).get();
			if (animal.getPassword().equals(animalLogin.getPassword())) {
				return "redirect:/animal/" + animal.getId();
			} else {
				model.addAttribute("erreur", "Mot de passe incorrect, veuillez réessayer !");
				model.addAttribute("pseudo", animalLogin.getPseudo());
				return "animal/connexion";
			}
		} else {
			model.addAttribute("erreur", "Animal non trouvé :(");
			return "animal/connexion";
		}
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
