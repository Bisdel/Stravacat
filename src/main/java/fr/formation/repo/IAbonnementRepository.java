package fr.formation.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Abonnement;
import fr.formation.model.Ville;



public interface IAbonnementRepository extends JpaRepository<Abonnement, Integer> {

	public  List<Abonnement> findByPseudo(String pseudo);

}