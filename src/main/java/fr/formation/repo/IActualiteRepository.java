package fr.formation.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import fr.formation.model.Actualite;

public interface IActualiteRepository extends JpaRepository<Actualite, Integer> {
    public List<Actualite> findAll();

	public Optional<Actualite> findByAnimalPseudo(String pseudo);

	// @Modifying
	// public void createActualite();
    
	// @Modifying
    // public void deleteByActualiteIdAndAnimalPseudo(int id, String pseudo);
}
