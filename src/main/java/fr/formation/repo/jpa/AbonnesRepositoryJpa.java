package fr.formation.repo.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.formation.model.Abonnes;

import fr.formation.repo.IAbonnesRepository;
import jakarta.persistence.EntityManager;

public class AbonnesRepositoryJpa extends AbstractRepositoryJpa implements IAbonnesRepository {

	@Override
	public List<Abonnes> findAll() {
		
		try (EntityManager em = emf.createEntityManager()) {
			em.createQuery("select a from Abonnes a", Abonnes.class).getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ArrayList<>();

		}
		return null;
	}

	@Override
	public void createEntry() {
       Abonnes abonne = new Abonnes();
       
	}

	@Override
	public void updateEntry(Abonnes entity) {
		try (EntityManager em = emf.createEntityManager()) {
			em.getTransaction().begin();
			
			try {
				if (entity.getId() == 0) {
					em.persist(entity);
				}
				
				else {
					entity = em.merge(entity);
				}
				
				em.getTransaction().commit();
			}
			
			catch (Exception ex) {
				ex.printStackTrace();
				em.getTransaction().rollback();
			}
		}
		
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return ;
	}

	@Override
	public List<Abonnes> findByPseudo(String pseudo) {

		return null;
	}

	@Override
	public void deleteEntry() {
		// TODO Auto-generated method stub
		
	}

}
