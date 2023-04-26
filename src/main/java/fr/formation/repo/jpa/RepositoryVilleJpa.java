package fr.formation.repo.jpa;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import fr.formation.model.Saisie;
import fr.formation.model.Ville;
import fr.formation.repo.IVilleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class RepositoryVilleJpa extends AbstractRepositoryJpa implements IVilleRepository {

	@Override
	public List<Ville> findAll() {
		try (EntityManager em = emf.createEntityManager()) {
			return em.createQuery("select v from Ville v", Ville.class).getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	@Override
	public Optional<Ville> findByNom(String nom) {
		try (EntityManager em = emf.createEntityManager()) {
			return Optional.of(em.createQuery("select v from Ville v ", Ville.class).getSingleResult());
		} catch (NoResultException doesnt_exist){
			System.out.println("La ville entrée n'est pas encore disponible dans notre base de données, désolé !");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public void createEntry() {
		Ville ville = new Ville();
		String nom = Saisie.next("le nom de votre ville : ");
		String ambiance = Saisie.next(" l'ambiance de la ville ");

		ville.setNom(nom);
		ville.setAmbiance(ambiance);

		try (EntityManager em = emf.createEntityManager()) {
			em.getTransaction().begin();
			try {
				if (ville.getId() == 0) {
					em.persist(ville);
				} else {
					ville = em.merge(ville);
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
}
