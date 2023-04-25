package fr.formation.repo;

import java.util.Optional;

import fr.formation.model.Animal;

public interface IRepositoryAnimal extends IRepository<Animal> {

    public Optional<Animal> findByPseudo(String pseudo);

}
