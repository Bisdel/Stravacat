package fr.formation.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Abonnement;


public interface IAbonnementRepository extends JpaRepository<Abonnement, Integer> {
	public  List<Abonnement> findByPseudo(String pseudo);
}
