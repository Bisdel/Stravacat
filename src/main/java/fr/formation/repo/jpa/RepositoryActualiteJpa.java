package fr.formation.repo.jpa;

import java.util.ArrayList;
import java.util.List;

import fr.formation.model.Actualite;
import fr.formation.repo.IActualiteRepository;
import jakarta.persistence.EntityManager;

public class RepositoryActualiteJpa extends AbstractRepositoryJpa implements IActualiteRepository{

    @Override
    public List<Actualite> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
                return em
                    .createQuery("select a from Actualite a", Actualite.class)
                    .getResultList();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
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

    @Override
    public Actualite createEntry(Actualite actualite) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createEntry'");
    }
    
}
