package fr.formation.repo.jpa;

import java.util.ArrayList;
import java.util.List;

import fr.formation.model.Abonnement;
import fr.formation.model.Saisie;
import fr.formation.repo.IAbonnementRepository;
import jakarta.persistence.EntityManager;

public class RepositoryAbonnementJpa extends AbstractRepositoryJpa implements IAbonnementRepository {

	@Override
	public List<Abonnement> findAll() {
		try (EntityManager em = emf.createEntityManager()) {
		return	em.createQuery("select b from Abonnement b", Abonnement.class).getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public void createEntry() {
		Abonnement abnm = new Abonnement();
		int abnm_animal_id = Saisie.nextInt("l'id de l'animal : ");
		String abnm_pseudo = Saisie.next("le pseudo de l'animal : ");
		int abnm_age = Saisie.nextInt("l'age : ");
		String abnm_espece = Saisie.next("l'espece : ");
		int abnn_ville_id = Saisie.nextInt("l'id de la ville : ");

		abnm.setAnimal_id(abnm_animal_id);
		abnm.setPseudo(abnm_pseudo);
		abnm.setAge(abnm_age);
		abnm.setEspece(abnm_espece);
		abnm.setVille_id(abnn_ville_id);

		try (EntityManager em = emf.createEntityManager()) {
			em.getTransaction().begin();
			try {
				if (abnm.getId() == 0) {
					em.persist(abnm);
				} else {
					abnm = em.merge(abnm);
				}
				em.getTransaction().commit();
			} catch (Exception ex) {
				ex.printStackTrace();
				em.getTransaction().rollback();
			}
		}
	}

	@Override
	public void updateEntry(Abonnement entity) {
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

	}

	@Override
	public void deleteEntry() {
		System.out.println("Entrer l'id de l'abonnement que vous voulez supprimer");

		try (EntityManager em = emf.createEntityManager()) {
			em.getTransaction().begin();
			try {
				Abonnement abonnementAsupprimer = em.find(Abonnement.class,
						Saisie.next("l'Id de l'abonne a supprimé : "));
				em.remove(abonnementAsupprimer);

			} catch (Exception ex) {
				ex.printStackTrace();
				em.getTransaction().rollback();
			}

		} catch (Exception ed) {
			ed.printStackTrace();
		}
	}

	@Override
	public List<Abonnement> findByPseudo(String pseudo) {
		// TODO Auto-generated method stub
		return null;
	}

}
