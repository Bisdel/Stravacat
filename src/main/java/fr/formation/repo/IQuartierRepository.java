package fr.formation.repo;

import java.util.List;

import fr.formation.model.Ville;

public interface IVilleRepository extends IRepository<Ville> {
	 public  List<Ville> findByVille(String ville);
}
