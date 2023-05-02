package fr.formation.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Animal;

public interface IRepositoryAnimal extends JpaRepository<Animal, Integer> {

    public Optional<Animal> findByPseudo(String pseudo);

}
