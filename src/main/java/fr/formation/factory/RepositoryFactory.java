package fr.formation.factory;

import fr.formation.repo.IAbonnesRepository;
import fr.formation.repo.IQuartierRepository;
import fr.formation.repo.jpa.AbonnesRepositoryJpa;
import fr.formation.repo.jpa.QuartierRepositoryJpa;

public class RepositoryFactory {
	
public static IAbonnesRepository creatAbonnesRepository() {
	return new AbonnesRepositoryJpa();
}

public static IQuartierRepository creatQuaertierRepository() {
	return new QuartierRepositoryJpa();

}
}
