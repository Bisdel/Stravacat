package fr.formation.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
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

}
