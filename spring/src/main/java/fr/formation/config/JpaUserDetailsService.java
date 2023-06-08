package fr.formation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.model.Animal;
import fr.formation.repo.IAnimalRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {
	@Autowired
	private IAnimalRepository repoAnimal;
	
	@Override
	public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
		Animal animal = this.repoAnimal
					.findByPseudo(pseudo)
					.orElseThrow(() -> new UsernameNotFoundException("L'animal n'existe pas."));
		
		// Si l'animal n'a pas été trouvé, l'exception sera jetée, et on s'arrêtera là
		
		UserBuilder userBuilder = User.withUsername(pseudo).password(animal.getPassword());
		
		if (animal.isAdmin()) {
			userBuilder.roles("ADMIN");
		}
		
		else {
			userBuilder.roles("USER");
		}
		
		return userBuilder.build();
	}
}
