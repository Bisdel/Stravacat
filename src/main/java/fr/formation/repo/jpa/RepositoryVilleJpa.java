package fr.formation.repo.jpa;

import java.util.ArrayList;
import java.util.List;

import fr.formation.model.Abonnes;
import fr.formation.model.Saisie;
import fr.formation.model.Ville;
import fr.formation.repo.IVilleRepository;
import jakarta.persistence.EntityManager;

public class RepositoryVilleJpa extends AbstractRepositoryJpa implements IVilleRepository {

	@Override
	public List<Ville> findAll() {
		try (EntityManager em = emf.createEntityManager()) {
			em.createQuery("select v from Ville v", Ville.class).getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public void createEntry() {
		Ville vil = new Ville();
		String nom = Saisie.next("le nom de votrte ville : ");
		String ambiance = Saisie.next(" l'ambiance de la ville ");

		vil.setVille(nom);
		vil.setAmbiance(ambiance);

		try (EntityManager em = emf.createEntityManager()) {
			em.getTransaction().begin();
			try {
				if (vil.getId() == 0) {
					em.persist(vil);
				} else {
					vil = em.merge(vil);
				}
				em.getTransaction().commit();
			} catch (Exception ex) {
				ex.printStackTrace();
				em.getTransaction().rollback();
			}
		}
	}

	@Override
	public void updateEntry(Ville entity) {
		try (EntityManager em = emf.createEntityManager()) {
			em.getTransaction().begin();
			try {
				if (entity.getId() == 0) {
					em.persist(entity);
				} else {
					entity = em.merge(entity);
				}
				em.getTransaction().commit();
			}

			catch (Exception ex) {
				ex.printStackTrace();
				em.getTransaction().commit();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void deleteEntry() {
		System.out.println("Entrer le pseudo de la ville que vous voulez supprimer");

		try (EntityManager em = emf.createEntityManager()) {
			em.getTransaction().begin();
			try {
				Ville villeAsupprimer = em.find(Ville.class, Saisie.next("l'Id de la ville a supprimé : "));
				em.remove(villeAsupprimer);

			} catch (Exception ex) {
				ex.printStackTrace();
				em.getTransaction().rollback();
			}

		} catch (Exception ed) {
			ed.printStackTrace();
		}
	}

	@Override
	public List<Ville> findByVille(String ville) {

		return null;
	}

}
