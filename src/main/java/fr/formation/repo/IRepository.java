package fr.formation.repo;

import java.util.List;

import fr.formation.model.Abonnes;

public interface IRepository<T> {

    public List<T> findAll();

    public void createEntry();
    
    public T updateEntry(T entity);
    
    public void deleteEntry();

}
