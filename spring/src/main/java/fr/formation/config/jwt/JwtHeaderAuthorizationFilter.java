package fr.formation.config.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import fr.formation.model.Animal;
import fr.formation.repo.IAnimalRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtHeaderAuthorizationFilter extends OncePerRequestFilter {
	@Autowired
	private IAnimalRepository repoAnimal;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		String token = null;
		
		if (authHeader != null) {
			token = authHeader.substring(7); // On retire "Bearer " qui fait 7 caractères
		}
		
		Optional<String> optPseudo = JwtUtil.getUsername(token);
		
		// Si on a le nom d'animal, le jeton est valide
		if (optPseudo.isPresent()) {
			String pseudo = optPseudo.get();
			Optional<Animal> optAnimal = this.repoAnimal.findByPseudo(pseudo);
			
			// Si on a l'animal, on peut l'authentifier
			if (optAnimal.isPresent()) {
				Animal animal = optAnimal.get();
				
				// Simuler la connexion grâce au jeton
				
				// Créer la liste des rôles
				List<GrantedAuthority> authorities = new ArrayList<>();
				
				// Le préfix "ROLE_" permet d'indiquer à SPRING SECURITY qu'il s'agit d'un rôle
				
				if (animal.isAdmin()) {
					authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
				}
				
				else {
					authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
				}
				
				// Recréer un nouvel Authentication
				Authentication authentication = new UsernamePasswordAuthenticationToken(pseudo, null, authorities);
				
				// Injecter cet Authentication dans le contexte de sécurité
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		
		// Important, pour passer à la suite
		filterChain.doFilter(request, response);
	}
}
