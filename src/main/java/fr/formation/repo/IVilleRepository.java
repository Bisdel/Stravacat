package fr.formation.repo;

import java.util.Optional;

import fr.formation.model.Ville;

public interface IVilleRepository extends IRepository<Ville> {
	public Optional<Ville> findByNom(String nom);
}
