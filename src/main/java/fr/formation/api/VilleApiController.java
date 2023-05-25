package fr.formation.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.api.request.VilleRequest;
import fr.formation.api.response.VilleResponse;
import fr.formation.exception.VilleNotFoundException;
import fr.formation.exception.VilleNotValidException;
import fr.formation.model.Ville;
import fr.formation.repo.IVilleRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ville")
public class VilleApiController {

	@Autowired
	private IVilleRepository repoville;

	@GetMapping
	private List<VilleResponse> findAll() {
		return this.repoville.findAll().stream().map(VilleResponse::convert).toList();

	}

	@PostMapping
	private VilleResponse add(@Valid @RequestBody VilleRequest villeRequest, BindingResult result) {
		if (result.hasErrors()) {
			throw new VilleNotValidException();
		}
		Ville nouvelleVille = new Ville();
		BeanUtils.copyProperties(villeRequest, nouvelleVille);

		this.repoville.save(nouvelleVille);
		return VilleResponse.convert(nouvelleVille);
	}

	@PutMapping("/{id}")
	public Ville add(@PathVariable int id, @Valid @RequestBody VilleRequest villeRequest, BindingResult result) {
		if (result.hasErrors()) {
			throw new VilleNotFoundException();
		}
		Ville ville = this.repoville.findById(id).orElseThrow(VilleNotFoundException::new);
		BeanUtils.copyProperties(villeRequest, ville);
		return this.repoville.save(ville);
	}

}
