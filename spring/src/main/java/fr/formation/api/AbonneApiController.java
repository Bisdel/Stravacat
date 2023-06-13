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

@RestController
@RequestMapping("/api/abonnes")
public class AbonneApiController {
	@Autowired
	private IAbonnesRepository repoAbonne;

	@Autowired
	private IVilleRepository villeRepository;

	// LIste des ABonnes
	@GetMapping
	public List<AbonnesResponse> findAll() {
		List<AbonnesResponse> result = this.repoAbonne.findAll().stream().map(AbonnesResponse::convert).toList();
		result.forEach(abo -> {
			Optional<Ville> ville = this.villeRepository.findById(abo.getVille_id());

			if (ville.isPresent()) {
				abo.setVille(ville.get());
			}

		});

		return result;

	}

	// Ajouter un abonnes

	@PostMapping
	public AbonnesResponse add(@Valid @RequestBody AbonnesRequest abonnesRequest, BindingResult result) {
		if (result.hasErrors()) {
			throw new AbonnesNotValidException();
		}
		Abonnes nouveauAbonnes = new Abonnes();
		BeanUtils.copyProperties(abonnesRequest, nouveauAbonnes);

		this.repoAbonne.save(nouveauAbonnes);
		return AbonnesResponse.convert(nouveauAbonnes);
	}

	// Midifier
	@PutMapping("/{id}")
	public Abonnes add(@PathVariable int id, @Valid @RequestBody AbonnesRequest abonnesRequest, BindingResult result) {
		if (result.hasErrors()) {
			throw new AbonnesNotFoundException();
		}
		Abonnes abonnes = this.repoAbonne.findById(id).orElseThrow(AbonnesNotFoundException::new);
		BeanUtils.copyProperties(abonnesRequest, abonnes);
		return this.repoAbonne.save(abonnes);
	}

	// Supprimer
	@DeleteMapping("/{id}")
	public void deletById(@PathVariable int id) {
		this.repoAbonne.deleteById(id);
	}

}
