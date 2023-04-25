package fr.formation.repo.sql;

import java.util.List;

import fr.formation.repo.IRepository;



public interface IAbonnementsRepository extends IRepository<Abonnement> {
	 public  List<Aboennements> findByAbonnements(String abonnement);
}
