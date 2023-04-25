package fr.formation.repo;

import java.util.List;

import fr.formation.model.Abonnes;



public interface IAbonnesRepository extends IRepository<Abonnes> {
	 public  List<Abonnes> findByPseudo(String pseudo);
}
