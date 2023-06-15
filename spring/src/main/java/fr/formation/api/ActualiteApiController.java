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

import fr.formation.api.request.ActualiteRequest;
import fr.formation.api.response.ActualiteResponse;
import fr.formation.api.response.AnimalResponse;
import fr.formation.api.response.VilleResponse;
import fr.formation.exception.ActualiteNotFoundException;
import fr.formation.exception.ActualiteNotValidException;
import fr.formation.exception.AnimalNotFoundException;
import fr.formation.exception.VilleNotFoundException;
import fr.formation.model.Actualite;
import fr.formation.repo.IActualiteRepository;
import fr.formation.repo.IAnimalRepository;
import fr.formation.repo.IVilleRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/actualite")
public class ActualiteApiController {
    
    @Autowired
    private IActualiteRepository repoActualite;

	@Autowired
    private IAnimalRepository repoAnimal;

	@Autowired
    private IVilleRepository repoVille;

    @GetMapping
	@Transactional
	public List<ActualiteResponse> findAll() {
		List<ActualiteResponse> response = new ArrayList<>();
		List<Actualite> actualites  = this.repoActualite.findAll();
		
		for (Actualite actu : actualites) {
			ActualiteResponse actualiteResponse = new ActualiteResponse();
			actualiteResponse.setActu_id(actu.getActu_id());
			actualiteResponse.setActu_description(actu.getActu_description());
			actualiteResponse.setActu_timestamp(actu.getActu_timestamp());
			actualiteResponse.setAnimal(AnimalResponse.convert(actu.getAnimal()));
			actualiteResponse.setVille(VilleResponse.convert(actu.getVille()));

			response.add(actualiteResponse);
		}
		return response;
	}

	@GetMapping("/{id}")
	@Transactional
	public ActualiteResponse findById(@PathVariable int id) {
		Actualite actualite = this.repoActualite.findById(id).orElseThrow(ActualiteNotFoundException::new);
	
		return ActualiteResponse.convert(actualite);	
	}

	@GetMapping("/animal/{animalId}")
    public List<ActualiteResponse> findAllByAnimalId(@PathVariable int animalId){
        List<Actualite> actualites = this.repoActualite.findByAnimal(this.repoAnimal.findById(animalId).get());
        List<ActualiteResponse> response = new ArrayList<>();
	
        for (Actualite actu : actualites) {
			response.add(ActualiteResponse.convert(actu));
        }
        return response;
    }

	// @PostMapping
	// @JsonView(Views.Actualite.class)
	// public Actualite add(@Valid @RequestBody Actualite actualite, BindingResult result) {
	// 	if (result.hasErrors()){
    //         throw new ActualiteNotValidException();
    //     }
	// 	actualite.setActu_timestamp(LocalDateTime.now());
	// 	return this.repoActualite.save(actualite);
	// }

	@PostMapping
	public ActualiteResponse add(@Valid @RequestBody ActualiteRequest actualiteRequest, BindingResult result) {
		if (result.hasErrors()){
            throw new ActualiteNotValidException();
        }
		Actualite actualite = new Actualite();
		BeanUtils.copyProperties(actualiteRequest, actualite);
        actualite.setVille(this.repoVille.findById(actualiteRequest.getVille()).orElseThrow(VilleNotFoundException::new));
        actualite.setAnimal(this.repoAnimal.findById(actualiteRequest.getAnimal()).orElseThrow(AnimalNotFoundException::new));
		actualite.setActu_timestamp(LocalDateTime.now());
        
		this.repoActualite.save(actualite);

		return ActualiteResponse.convert(actualite);
	}
	
	// @PutMapping("/{id}")
	// @JsonView(Views.Actualite.class)
	// public Actualite update(@PathVariable int id, @Valid @RequestBody Actualite actualite, BindingResult result) {
	// 	if (result.hasErrors()) {
	// 		throw new ActualiteNotValidException();
	// 	}
	// 	actualite.setActu_id(id);
	// 	actualite.setActu_timestamp(LocalDateTime.now());
		
	// 	return this.repoActualite.save(actualite);
	// }

	@PutMapping("/{id}")
	public ActualiteResponse update(@PathVariable int id, @Valid @RequestBody ActualiteRequest actualiteRequest, BindingResult result) {
		if (result.hasErrors()) {
			throw new ActualiteNotValidException();
		}
		Actualite actualite = new Actualite();
		BeanUtils.copyProperties(actualiteRequest, actualite);
        actualite.setVille(this.repoVille.findById(actualiteRequest.getVille()).orElseThrow(VilleNotFoundException::new));
        actualite.setAnimal(this.repoAnimal.findById(actualiteRequest.getAnimal()).orElseThrow(AnimalNotFoundException::new));
        
		actualite.setActu_id(id);
		actualite.setActu_timestamp(LocalDateTime.now());
		
		this.repoActualite.save(actualite);
		return ActualiteResponse.convert(actualite);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable int id) {
		this.repoActualite.deleteById(id);
	}
}
