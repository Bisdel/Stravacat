package fr.formation.repo;

import java.util.List;



public interface IAbonnementsRepository extends IRepository<Abonnement> {
	 public  List<Abonnement> findByAbonnements(String abonnement);
}
