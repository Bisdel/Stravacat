package fr.formation.factory;

import fr.formation.repo.IAbonnementRepository;
import fr.formation.repo.IAbonnesRepository;
import fr.formation.repo.IVilleRepository;
import fr.formation.repo.jpa.RepositoryAbonnementJpa;
import fr.formation.repo.jpa.RepositoryAbonnesJpa;
import fr.formation.repo.jpa.RepositoryVilleJpa;

public class RepositoryFactory {

	public static IAbonnesRepository creatAbonnesRepository() {
		return new RepositoryAbonnesJpa();
	}

	public static IVilleRepository creatVilleRepository() {
		return new RepositoryVilleJpa();
	}

	public static IAbonnementRepository creatAbonnementRepository() {
	
		return new RepositoryAbonnementJpa();
	}
}
