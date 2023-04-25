package fr.formation.repo;

import java.util.List;

import fr.formation.model.Parcours;

public interface IParcoursRepository extends IRepository<Parcours>{
	public List<Parcours> findByParcoursId(int id);
}
