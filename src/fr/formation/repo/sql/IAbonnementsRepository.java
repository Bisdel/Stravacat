package fr.Stravact.repo;

import java.util.List;



public interface IAbonnementsRepository extends IRepository<Abonnements> {
	 public  List<Aboennements> findByAbonnements(String abonnement);
}
