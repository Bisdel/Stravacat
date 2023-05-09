package fr.formation.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Actualite;

public interface IActualiteRepository extends JpaRepository<Actualite, Integer> {
	public Optional<Actualite> findByAnimalPseudo(String pseudo);
}
