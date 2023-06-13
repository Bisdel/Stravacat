package fr.formation.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Actualite;
import fr.formation.model.Animal;

public interface IActualiteRepository extends JpaRepository<Actualite, Integer> {
	// @Query("select a from Actualite a where a.animal.id = ?1")
	// public List<Actualite> findByActuAnimalId(int id);

	public List<Actualite> findByAnimal(Animal animal);
}
