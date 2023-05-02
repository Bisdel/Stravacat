package fr.formation.repo;

import java.util.List;

import fr.formation.model.Abonnement;


public interface IAbonnementRepository extends JpaRepository<Abonnement> {
	public  List<Abonnement> findByPseudo(String pseudo);
}
