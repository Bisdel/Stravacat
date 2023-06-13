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

import fr.formation.api.request.AbonnementRequest;
import fr.formation.api.response.AbonnementResponse;
import fr.formation.exception.AbonnementNotFoundException;
import fr.formation.exception.AbonnementNotValidException;
import fr.formation.model.Abonnement;
import fr.formation.model.Ville;
import fr.formation.repo.IAbonnementRepository;
import fr.formation.repo.IVilleRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/abonnement")
public class AbonnementApiController {

	@Autowired
	private IAbonnementRepository repoAbonnement;

	@Autowired
	private IVilleRepository repoVille;

	// Liste des Abonnement
	@GetMapping
	public List<AbonnementResponse> findAll() {
		List<AbonnementResponse> result = this.repoAbonnement.findAll().stream().map(AbonnementResponse::convert)
				.toList();
		result.forEach(abon -> {
			Optional<Ville> ville = this.repoVille.findById(abon.getVille_id());
			if (ville.isPresent()) {
				abon.setVille(ville.get());
			}

		});

		return result;
	}

	// Ajouter
	@PostMapping
	public AbonnementResponse add(@Valid @RequestBody AbonnementRequest abonnementRequest, BindingResult result) {
		if (result.hasErrors()) {
			throw new AbonnementNotValidException();
		}
		Abonnement nouveauAbonnement = new Abonnement();
		BeanUtils.copyProperties(abonnementRequest, nouveauAbonnement);

		this.repoAbonnement.save(nouveauAbonnement);

		return AbonnementResponse.convert(nouveauAbonnement);
	}

	// Modifier
	@PutMapping("/{id}")
	public Abonnement add(@PathVariable int id, @Valid @RequestBody AbonnementRequest abonnementRequest,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new AbonnementNotFoundException();
		}

		Abonnement abonnement = this.repoAbonnement.findById(id).orElseThrow(AbonnementNotFoundException::new);

		BeanUtils.copyProperties(abonnementRequest, abonnement);

		return this.repoAbonnement.save(abonnement);
	}

	// Supprimer
	@DeleteMapping("/{id}")
	public void deletByid(@PathVariable int id) {
		this.repoAbonnement.deleteById(id);
	}

}
