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

	@Autowired // Cette annotation est utilisée pour effectuer l'injection de dépendances
				// automatique dans Spring. Elle permet à Spring de rechercher une instance
				// appropriée du type spécifié (ici, IAbonnesRepository) et de l'injecter
				// automatiquement dans la variable repoAbonnes.
	private IAbonnesRepository repoAbonnes;

	@GetMapping("/abonne") // Cette annotation est utilisée pour mapper une méthode à une requête HTTP GET.
							// L'URL spécifiée (/abonne) détermine l'adresse à laquelle cette méthode sera
							// exécutée lorsque la requête GET est reçue.
	public String findAll(Model model) {
		// Récupère tous les abonnés à partir du repository
		model.addAttribute("abonnes", this.repoAbonnes.findAll());

		return "abonne/listes";
	}

	@GetMapping("/abonne/ajouter")
	public String add() {
		// Affiche la page de formulaire pour ajouter un nouvel abonné
		return "abonne/ajouter";
	}

	@PostMapping({ "/abonne/ajouter", "/abonne/modifier/{id}" }) // Cette annotation est utilisée pour mapper une
																	// méthode à une requête HTTP POST. Les URL
																	// spécifiées (/abonne/ajouter et
																	// /abonne/modifier/{id}) indiquent les adresses
																	// auxquelles cette méthode sera exécutée lors de la
																	// réception des requêtes POST correspondantes.
	public String add(@Valid Abonnes abonnes, BindingResult result, Model model) {
		// Cette méthode est appelée lors de la soumission du formulaire d'ajout ou de
		// modification d'un abonné
		if (result.hasErrors()) {
			// Vérifie s'il y a des erreurs de validation dans le formulaire
			model.addAttribute("erreurs", result);
			return "abonne/ajouter";
		}
		// Sauvegarde l'abonné dans le repository
		this.repoAbonnes.save(abonnes);
		return "redirect:/abonne";
	}

	@GetMapping("/abonne/modifier/{id}")
	public String edit(@PathVariable int id, Model model) {
		// Affiche la page de formulaire pour modifier un abonné existant
		// Recherche l'abonné par son ID dans le repository
		Abonnes abonne = this.repoAbonnes.findById(id).orElseThrow(AbonnesNotFoundException::new);
		model.addAttribute("abonne", abonne);
		return "abonne/ajouter";
	}

	@GetMapping("/abonne/supprimer/{id}") // Supprime l'abonné par son ID
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
