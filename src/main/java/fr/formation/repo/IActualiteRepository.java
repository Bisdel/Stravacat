package fr.formation.repo;

import java.util.List;

import fr.formation.model.Actualite;

public interface IActualiteRepository extends IRepository<Actualite> {
	public List<Actualite> findByAnimalId(int animalId);

}
