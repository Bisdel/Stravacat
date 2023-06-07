package fr.formation.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.model.Actualite;

public interface IActualiteRepository extends JpaRepository<Actualite, Integer> {
	@Query("select a from Actualite a where a.animal.id = ?1")
	public List<Actualite> findByActuAnimalId(int id);
}
