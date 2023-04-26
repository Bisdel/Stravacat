package fr.formation.repo.jpa;

import java.util.ArrayList;
import java.util.List;
import fr.formation.model.Abonnes;
import fr.formation.model.Saisie;
import fr.formation.repo.IAbonnesRepository;
import jakarta.persistence.EntityManager;

public class RepositoryAbonnesJpa extends AbstractRepositoryJpa implements IAbonnesRepository {

	@Override
	public List<Abonnes> findAll() {

		try (EntityManager em = emf.createEntityManager()) {
			return em.createQuery("select a from Abonnes a", Abonnes.class).getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ArrayList<>();

		}
	
	}

	@Override

	public void createEntry() {
		Abonnes abonne = new Abonnes();
		int id_animal = Saisie.nextInt("L'Id de l'animal: ");
		int age = Saisie.nextInt("Son age : ");
		String pseudo = Saisie.next("Son pseudo :");
		int nb_patounes = Saisie.nextInt("nb_patounes : ");
		int id_ville = Saisie.nextInt(" l'Id du ville : ");
		abonne.setAnimal_id(id_animal);
		abonne.setAge(age);
		abonne.setPseudo(pseudo);
		abonne.setNb_patounes(nb_patounes);
		abonne.setVille_id(id_ville);

		try (EntityManager em = emf.createEntityManager()) {
			em.getTransaction().begin();
			try {
				if (abonne.getId() == 0) {
					em.persist(abonne);
				} else {
					abonne = em.merge(abonne);
				}
				em.getTransaction().commit();
			} catch (Exception ex) {
				ex.printStackTrace();
				em.getTransaction().rollback();
			}

		}
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

	}

	@Override
	public List<Abonnes> findByPseudo(String pseudo) {

		return null;
	}

	@Override
	public void deleteEntry() {
		System.out.println("Entrer le pseudo de l'abonne que vous voulez supprimer");

		try (EntityManager em = emf.createEntityManager()) {
			em.getTransaction().begin();
			try {
				Abonnes abonneAsupprimer = em.find(Abonnes.class, Saisie.next("l'Id de l'abonne a supprimé : "));
				em.remove(abonneAsupprimer);

			} catch (Exception ex) {
				ex.printStackTrace();
				em.getTransaction().rollback();
			}

		} catch (Exception ed) {
			ed.printStackTrace();
		}
	}

}
