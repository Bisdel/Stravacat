package fr.formation.repo.sql;

import java.util.List;

import fr.formation.model.Abonnes;
import fr.formation.repo.IRepository;

public interface IAbonnesRepository extends IRepository<Abonnes> {
 public   List<Abonnes> findByAbonnes(String pseudo);
}
