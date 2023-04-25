package fr.formation.repo;

import java.util.List;

import fr.formation.model.Abonnes;

public interface IRepository<T> {

    public List<T> findAll();

    public void createEntry(String pseudo);
    
    public void updateEntry(T entity);
    
    public void deleteEntry();

}
