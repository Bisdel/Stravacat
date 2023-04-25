package fr.formation.repo.jpa;

import java.util.List;

import fr.formation.model.Quartier;
import fr.formation.repo.IQuartierRepository;

public class RepositoryQuartierJpa implements IQuartierRepository {

	@Override
	public List<Quartier> findAll() {
		
		return null;
	}

	@Override
	public void createEntry() {
	
	}

	@Override
	public void updateEntry(Quartier entity) {
	

	}

	@Override
	public void deleteEntry() {
	
	}

	@Override
	public List<Quartier> findByVille(String ville) {
	
		return null;
	}

}
