package fr.formation.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.formation.api.request.AbonnesRequest;
import fr.formation.api.response.AbonnesResponse;
import fr.formation.exception.AbonnesNotFoundException;
import fr.formation.exception.AbonnesNotValidException;
import fr.formation.model.Abonnes;
import fr.formation.model.Ville;
import fr.formation.repo.IAbonnesRepository;
import fr.formation.repo.IVilleRepository;
import jakarta.validation.Valid;

@RestController // pour indiquer qu'elle gère les requêtes REST
@RequestMapping("/api/abonnes")
public class AbonneApiController { // Injection des dépendances IAbonnesRepository et IVilleRepository
	@Autowired
	private IAbonnesRepository repoAbonne;

	@Autowired
	private IVilleRepository villeRepository;

	// Méthode pour récupérer la liste de tous les abonnés
	@GetMapping
	public List<AbonnesResponse> findAll() {
		// Récupérer tous les abonnés depuis le repository
		List<AbonnesResponse> result = this.repoAbonne.findAll().stream()
				.map(AbonnesResponse::convert)
				.toList(); // la méthode de conversion convert de la classe AbonnesResponse à chaque
							// élément de la liste d'abonnés et renvoie une liste des objets AbonnesResponse
							// convertis.

		// Parcourir chaque abonné et récupérer les détails de la ville correspondante
		result.forEach(abo -> {
			Optional<Ville> ville = this.villeRepository.findById(abo.getVille_id());

			if (ville.isPresent()) {
				abo.setVille(ville.get());
			}
		});

		// Retourner la liste des abonnés avec les détails de la ville
		return result;
	}

	// Méthode pour ajouter un nouvel abonné
	@PostMapping
	public AbonnesResponse add(@Valid @RequestBody AbonnesRequest abonnesRequest, BindingResult result) {
		// Vérifier s'il y a des erreurs de validation dans le BindingResult
		if (result.hasErrors()) {
			throw new AbonnesNotValidException();
		}

		// Créer un nouvel objet Abonnes et copier les propriétés de la requête
		Abonnes nouveauAbonnes = new Abonnes();
		BeanUtils.copyProperties(abonnesRequest, nouveauAbonnes);

		// Enregistrer le nouvel abonné dans le repository
		this.repoAbonne.save(nouveauAbonnes);

		// Convertir et retourner la réponse AbonnesResponse correspondante
		return AbonnesResponse.convert(nouveauAbonnes);
	}

	// Méthode pour modifier un abonné existant
	@PutMapping("/{id}")
	public Abonnes add(@PathVariable int id, @Valid @RequestBody AbonnesRequest abonnesRequest, BindingResult result) {
		// Vérifier s'il y a des erreurs de validation dans le BindingResult
		if (result.hasErrors()) {
			throw new AbonnesNotFoundException();
		}

		// Récupérer l'abonné existant à partir de l'ID fourni
		Abonnes abonnes = this.repoAbonne.findById(id).orElseThrow(AbonnesNotFoundException::new);

		// Copier les propriétés de la requête dans l'abonné existant
		BeanUtils.copyProperties(abonnesRequest, abonnes);

		// Enregistrer les modifications de l'abonné dans le repository
		return this.repoAbonne.save(abonnes);
	}

	// Méthode pour supprimer un abonné par son ID
	@DeleteMapping("/{id}")
	public void deletById(@PathVariable int id) {
		// Supprimer l'abonné correspondant à l'ID fourni
		this.repoAbonne.deleteById(id);
	}
}
