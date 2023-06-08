package fr.formation.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.api.request.AnimalRequest;
import fr.formation.api.request.ConnexionRequest;
import fr.formation.api.response.ConnexionResponse;
import fr.formation.api.response.AnimalResponse;
import fr.formation.config.jwt.JwtUtil;
import fr.formation.model.Animal;
import fr.formation.model.Ville;
import fr.formation.repo.IAnimalRepository;
import fr.formation.repo.IVilleRepository;

@RestController
@RequestMapping("/api/animal")
public class AnimalApiController {
	@Autowired
	private IAnimalRepository repoAnimal;

	@Autowired
	private IVilleRepository repoVille;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired // Par défaut, ce manager n'existe pas dans le contexte, donc on le configure dans SecurityConfig
	private AuthenticationManager authenticationManager;
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<AnimalResponse> findAll() {
		return this.repoAnimal.findAll()
				.stream()
				.map(u -> {
					AnimalResponse resp = new AnimalResponse();
					
					resp.setId(u.getId());
					resp.setPseudo(u.getPseudo());
					
					return resp;
				})
				.toList();
	}
	
	@PostMapping("/connexion")
	public ConnexionResponse connexion(@RequestBody ConnexionRequest connexionRequest) {
		// On va demander à SPRING SECURITY de vérifier le username / password
		// On a besoin d'un AuthenticationManager
		// On utilisera la méthode authenticate, qui attend un Authentication
		// Et on utilisera le type PseudoPasswordAuthenticationToken pour donner le username & le password
		Authentication authentication =
				new UsernamePasswordAuthenticationToken(connexionRequest.getPseudo(), connexionRequest.getPassword());
		
		// On demande à SPRING SECURITY de vérifier ces informations de connexion
		this.authenticationManager.authenticate(authentication);
		
		// Si on arrive ici, c'est que la connexion a fonctionné
		ConnexionResponse response = new ConnexionResponse();
		
		// On génère un jeton pour l'animal connecté
		String token = JwtUtil.generate(authentication);
		
		response.setSuccess(true);
		response.setToken(token); // On donne le jeton en réponse
		
		return response;
	}
	
	@PostMapping("/inscription")
	public AnimalResponse inscription(@RequestBody AnimalRequest animalRequest) {
		Animal animal = new Animal();
		
		BeanUtils.copyProperties(animalRequest, animal);
		animal.setPassword(this.passwordEncoder.encode(animalRequest.getPassword()));

		// Pour la ville, user input sous forme de String, stockée dans un AnimalRequest, puis on la transforme en ville
		if (repoVille.findByNom(animalRequest.getVille()).isEmpty()){
			Ville ville = new Ville();
			ville.setNom(animalRequest.getVille());
			repoVille.save(ville);
		}
		animal.setVille(repoVille.findByNom(animalRequest.getVille()).get());
		this.repoAnimal.save(animal);
		
		AnimalResponse response = new AnimalResponse();
		BeanUtils.copyProperties(animal, response);
		
		return response;
	}
}
