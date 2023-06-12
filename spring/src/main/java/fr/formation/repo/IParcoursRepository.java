package fr.formation.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.model.Animal;
import fr.formation.model.Parcours;

public interface IParcoursRepository extends JpaRepository<Parcours, Integer>{

	 @Query ("select p from Parcours p where p.datePublicationParcours between ?1 and ?2")
	 public List<Parcours> findByDateParcours(LocalDateTime start, LocalDateTime end);

	public List<Parcours> findByAnimal(Animal animal);

	// @Query("delete p from Parcours p where p.id = ?1")
	// public void deleteEntry(int ref);

	// @Query ("select p from Parcours p where p.villeParcours = ?1")
	// public void findbyVillebyId(String ville);



}
