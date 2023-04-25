package fr.formation.repo;

import java.util.List;

import fr.formation.model.Animal;

public interface IRepository<T> {

    public List<T> findAll();

    public void createEntry(String pseudo);
    
    public void updateEntry(Animal animal);
    
    public void deleteEntry();

}
