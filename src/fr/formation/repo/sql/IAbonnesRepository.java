package fr.Stravact.repo;

import java.util.List;

import fr.formation.model.Abonnes;

public interface IAbonnesRepository extends IRepository<Abonnes> {
 public   List<Abonnes> findByAbonnes(String pseudo);
}
