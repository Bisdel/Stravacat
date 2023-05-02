package fr.formation.repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.model.Parcours;

public interface IParcoursRepository extends JpaRepository<Parcours, Integer>{

	@Query ("select p from Parcours p where p.datePublicationParcours between ?1 and ?2")
	public List<Parcours> findByDateParcours(LocalDateTime start);

}
