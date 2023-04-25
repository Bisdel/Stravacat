package fr.formation.repo;

import java.util.List;

import fr.formation.model.Abonnement;



public interface IAbonnementsRepository extends IRepository<Abonnement> {
	 public  List<Abonnement> findByAbonnements(String abonnement);
}
