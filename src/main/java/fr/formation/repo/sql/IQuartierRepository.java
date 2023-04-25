package fr.formation.repo.sql;

import java.util.List;

import fr.formation.model.Quartier;
import fr.formation.repo.IRepository;

public interface IQuartierRepository extends IRepository<Quartier> {
	 public  List<Quartier> findByVille(String ville);
}
