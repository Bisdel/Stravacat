package fr.formation.api;

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

import fr.formation.api.request.AnimalRequest;
import fr.formation.api.response.AnimalDetailResponse;
import fr.formation.exception.AnimalNotFoundException;
import fr.formation.exception.AnimalNotValidException;
import fr.formation.model.Animal;
import fr.formation.repo.IAnimalRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/animal")
public class AnimalApiController {
	@Autowired
	private IAnimalRepository repoAnimal;
	
	
	@GetMapping
	@JsonView(Views.Animal.class)
	public List<Animal> findAll() {
		return this.repoAnimal.findAll();
	}
	
	@GetMapping("/{id}")
	@Transactional // Important pour garder l'EntityManager pour récupérer getProduits()
	public AnimalDetailResponse findById(@PathVariable int id) {
		Animal animal = this.repoAnimal.findById(id).orElseThrow(AnimalNotFoundException::new);
		AnimalDetailResponse response = new AnimalDetailResponse();
		
		BeanUtils.copyProperties(animal, response);
				
		return response;
	}
	
	@PostMapping
	@JsonView(Views.Animal.class)
	public Animal add(@Valid @RequestBody AnimalRequest animalRequest, BindingResult result) {
		if (result.hasErrors()) {
			throw new AnimalNotValidException();
		}
		
		Animal animal = new Animal();
		
		BeanUtils.copyProperties(animalRequest, animal);
		
		return this.repoAnimal.save(animal);
	}
	
	@PutMapping("/{id}")
	@JsonView(Views.Animal.class)
	public Animal edit(@PathVariable int id, @Valid @RequestBody AnimalRequest animalRequest, BindingResult result) {
		if (result.hasErrors()) {
			throw new AnimalNotValidException();
		}
		
		Animal animal = this.repoAnimal.findById(id).orElseThrow(AnimalNotFoundException::new);
		
		BeanUtils.copyProperties(animalRequest, animal);
		
		return this.repoAnimal.save(animal);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable int id) {
		this.repoAnimal.deleteById(id);
	}
}
