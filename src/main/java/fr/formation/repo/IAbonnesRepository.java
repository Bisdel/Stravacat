package fr.formation.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Abonnes;



public interface IAbonnesRepository extends JpaRepository<Abonnes , Integer> {
	 public  List<Abonnes> findByPseudo(String pseudo);

	public void createEntry();
}
