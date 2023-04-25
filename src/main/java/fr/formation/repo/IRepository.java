package fr.formation.repo;

import java.util.List;

public interface IRepository<T> {

    public List<T> findAll();

    public void createEntry(String pseudo);
    
    public void updateEntry(T entity);
    
    public void deleteEntry(int id);

}
