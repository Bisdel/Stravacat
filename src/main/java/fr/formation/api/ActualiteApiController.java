package fr.formation.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.api.response.ActualiteResponse;
import fr.formation.exception.ActualiteNotFoundException;
import fr.formation.model.Actualite;
import fr.formation.repo.IActualiteRepository;

@RestController
@RequestMapping("/api/actualite")
public class ActualiteApiController {
    
    @Autowired
    private IActualiteRepository repoActualite;

    @GetMapping
	@JsonView(Views.Actualite.class)
	public List<Actualite> findAll() {
		return this.repoActualite.findAll();
	}

	@GetMapping("/{id}")
	public ActualiteResponse findById(@PathVariable int id) {
		Actualite actualite = this.repoActualite.findById(id).orElseThrow(ActualiteNotFoundException::new);
		ActualiteResponse response = new ActualiteResponse();
		
		BeanUtils.copyProperties(actualite, response);
				
		return response;
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable int id) {
		this.repoActualite.deleteById(id);
	}
}
