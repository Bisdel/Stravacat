package fr.formation.repo.jpa;

import java.util.List;

import fr.formation.model.Actualite;
import fr.formation.repo.IActualiteRepository;

public class RepositoryActualiteJpa extends AbstractRepositoryJpa implements IActualiteRepository{

    @Override
    public List<Actualite> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public void createEntry() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createEntry'");
    }

    @Override
    public void updateEntry(Actualite entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateEntry'");
    }

    @Override
    public void deleteEntry() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteEntry'");
    }

    @Override
    public List<Actualite> findByAnimalId(int animalId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByAnimalId'");
    }
    
}
