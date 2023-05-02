package fr.formation.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Ville;

public interface IVilleRepository extends JpaRepository<Ville, Integer> {

	public Optional<Ville> findByNom(String nom);

}
