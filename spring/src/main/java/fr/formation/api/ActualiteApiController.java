package fr.formation.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.api.response.ActualiteResponse;
import fr.formation.exception.ActualiteNotFoundException;
import fr.formation.exception.ActualiteNotValidException;
import fr.formation.model.Actualite;
import fr.formation.repo.IActualiteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/actualite")
public class ActualiteApiController {
    
    @Autowired
    private IActualiteRepository repoActualite;

    @GetMapping
	@Transactional
	public List<ActualiteResponse> findAll() {
		List<ActualiteResponse> response = new ArrayList<>();
		List<Actualite> actualites  = this.repoActualite.findAll();
		
		for (Actualite actu : actualites) {
			ActualiteResponse actualiteResponse = new ActualiteResponse();
			actualiteResponse.setActu_id(actu.getActu_id());
			actualiteResponse.setPseudo(actu.getAnimal().getPseudo());
			actualiteResponse.setActu_description(actu.getActu_description());
			actualiteResponse.setVille(actu.getVille().getNom());
			actualiteResponse.setActu_timestamp(actu.getActu_timestamp());

			response.add(actualiteResponse);
		}
		return response;
	}

	@GetMapping("/{id}")
	@Transactional
	public ActualiteResponse findById(@PathVariable int id) {
		Actualite actualite = this.repoActualite.findById(id).orElseThrow(ActualiteNotFoundException::new);
		ActualiteResponse response = new ActualiteResponse();
				
		BeanUtils.copyProperties(actualite, response);
		response.setPseudo(actualite.getAnimal().getPseudo());
		response.setVille(actualite.getVille().getNom());
				
		return response;
	}

	@PostMapping
	@JsonView(Views.ActualiteDetail.class)
	public Actualite add(@Valid @RequestBody Actualite actualite, BindingResult result) {
		if (result.hasErrors()){
            throw new ActualiteNotValidException();
        }
		actualite.setActu_timestamp(LocalDateTime.now());
		return this.repoActualite.save(actualite);
	}
	
	@PutMapping("/{id}")
	@JsonView(Views.ActualiteDetail.class)
	public Actualite update(@PathVariable int id, @Valid @RequestBody Actualite actualite, BindingResult result) {
		if (result.hasErrors()) {
			throw new ActualiteNotValidException();
		}
		actualite.setActu_id(id);
		actualite.setActu_timestamp(LocalDateTime.now());
		
		return this.repoActualite.save(actualite);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable int id) {
		this.repoActualite.deleteById(id);
	}
}