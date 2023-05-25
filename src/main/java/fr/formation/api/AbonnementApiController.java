package fr.formation.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.formation.api.response.AbonnementResponse;
import fr.formation.repo.IAbonnementRepository;

@Controller
@RequestMapping("/api/Aboonement")
public class AbonnementApiController {
	
	@Autowired
	private IAbonnementRepository repoAbonnement;
	
	// Liste des Abonnement
	@GetMapping
	public List<AbonnementResponse> findAll(){
		return this.repoAbonnement.findAll().stream().map(AbonnementResponse :: convert).toList();
	}
	
	

}
