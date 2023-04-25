package fr.formation.factory;

import fr.formation.repo.IAbonnesRepository;
import fr.formation.repo.IQuartierRepository;
import fr.formation.repo.jpa.RepositoryAbonnesJpa;
import fr.formation.repo.jpa.RepositoryQuartierJpa;

public class RepositoryFactory {
	
public static IAbonnesRepository creatAbonnesRepository() {
	return new RepositoryAbonnesJpa();
}

public static IQuartierRepository creatQuaertierRepository() {
	return new RepositoryQuartierJpa();

}
}
