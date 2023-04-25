package fr.Stravact.repo;

import java.util.List;

import fr.formation.model.Quartier;

public interface IQuartierRepository extends IRepository<Quartier> {
	 public  List<Quartier> findByVille(String ville);
}
