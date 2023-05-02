package fr.formation.repo.jpa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.formation.ApplicationActuJpa;
import fr.formation.model.Actualite;
import fr.formation.repo.IActualiteRepository;
import jakarta.persistence.EntityManager;

public class RepositoryActualiteJpa extends AbstractRepositoryJpa implements IActualiteRepository{

    @Override
    public List<Actualite> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
                return em
                    .createQuery("select a from Actualite a", Actualite.class)
                    .getResultList();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void createEntry() {
        Actualite actualite = new Actualite();

        RepositoryVilleJpa repoVille = new RepositoryVilleJpa();
        System.out.println("Entrer le nom de la ville");
        String nomVille = ApplicationActuJpa.sc.nextLine();
        if (repoVille.findByNom(nomVille).isPresent()) {
            actualite.setVille(repoVille.findByNom(nomVille).get());
        }
        else {
            repoVille.createEntry();
            actualite.setVille(repoVille.findByNom(nomVille).get());
        }

        RepositoryAnimalJpa repoAnimal = new RepositoryAnimalJpa();
        System.out.println("Entrer le pseudo de l'animal");
        String nomAnimal = ApplicationActuJpa.sc.nextLine();
        if (repoAnimal.findByPseudo(nomAnimal).isPresent()) {
            actualite.setAnimal(repoAnimal.findByPseudo(nomAnimal).get());
        }
        else {
            repoAnimal.createEntry();
            actualite.setAnimal(repoAnimal.findByPseudo(nomAnimal).get());
        }

        System.out.println("Entrer une description");
        actualite.setActu_description(ApplicationActuJpa.sc.nextLine());
        System.out.println("Spécifier la confidentialité(true/false?)");
        actualite.setActu_isPrivate(ApplicationActuJpa.sc.nextBoolean());
        actualite.setActu_timestamp(LocalDateTime.now());

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            try {
                if (actualite.getActu_id() == 0) {
                    em.persist(actualite);
                }
                else {
                    actualite = em.merge(actualite);
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
        System.out.println(actualite);
    }


    @Override
    public void updateEntry(Actualite actualite) {

        RepositoryVilleJpa repoVille = new RepositoryVilleJpa();
        System.out.println("Entrer le nom de la ville");
        String nomVille = ApplicationActuJpa.sc.nextLine();
        if (repoVille.findByNom(nomVille).isPresent()) {
            actualite.setVille(repoVille.findByNom(nomVille).get());
        }
        else {
            repoVille.createEntry();
            actualite.setVille(repoVille.findByNom(nomVille).get());
        }

        RepositoryAnimalJpa repoAnimal = new RepositoryAnimalJpa();
        System.out.println("Entrer le pseudo de l'animal");
        String nomAnimal = ApplicationActuJpa.sc.nextLine();
        if (repoAnimal.findByPseudo(nomAnimal).isPresent()) {
            actualite.setAnimal(repoAnimal.findByPseudo(nomAnimal).get());
        }
        else {
            repoAnimal.createEntry();
            actualite.setAnimal(repoAnimal.findByPseudo(nomAnimal).get());
        }

        System.out.println("Entrer une description");
        actualite.setActu_description(ApplicationActuJpa.sc.nextLine());
        System.out.println("Spécifier la confidentialité(true/false?)");
        actualite.setActu_isPrivate(ApplicationActuJpa.sc.nextBoolean());
        actualite.setActu_timestamp(LocalDateTime.now());

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            try {
                if (actualite.getActu_id() == 0){
                    em.persist(actualite);
                }
                else {
                    actualite = em.merge(actualite);
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
        System.out.println(actualite);
    }

    @Override
    public void deleteEntry() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            System.out.println("Saisir l'id de l'actu");
            int actuId = ApplicationActuJpa.sc.nextInt();
            try {
                em  .createQuery("delete from Actualite a where a.id = ?1")
                    .setParameter(1, actuId);
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
    public List<Actualite> findByAnimalId(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em
                .createQuery("select actu from Actualite actu where actu.animal.id = ?1", Actualite.class)
                .setParameter(1, id)
                .getResultList();
        }   
        catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}