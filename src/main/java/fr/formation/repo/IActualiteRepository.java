package fr.formation.repo;

<<<<<<< HEAD
import java.util.Optional;
=======
import java.util.List;
>>>>>>> ed4118af8ae274b32f0b5e55c2d5330433792cd5

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.model.Actualite;

public interface IActualiteRepository extends JpaRepository<Actualite, Integer> {
	@Query("select a from Actualite a where a.animal.id = ?1")
	public List<Actualite> findByActuAnimalId(int id);
}
