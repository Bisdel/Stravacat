package fr.formation.repo.jpa;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Optional;

import fr.formation.ApplicationAdminJpa;
import fr.formation.ApplicationAnimalJpa;
import fr.formation.model.Animal;
import fr.formation.repo.IRepositoryAnimal;
import jakarta.persistence.EntityManager;

import java.lang.String;

public class RepositoryAnimalJpa extends AbstractRepositoryJpa implements IRepositoryAnimal {

    @Override
    public List<Animal> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return em
                    .createQuery("select a from Animal a", Animal.class)
                    .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Animal> findByPseudo(String pseudo) {
        try (EntityManager em = emf.createEntityManager()) {
            return Optional.of(em
                    .createQuery("select a from Animal a where a.pseudo = ?1", Animal.class)
                    .setParameter(1, pseudo)
                    .getSingleResult());
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public void createEntry() {
        System.out.println(
                "Veuillez entrer les informations de votre nouvel animal, au format pseudo - motdepasse - age - espece - ville");
        Animal animal = new Animal();
        try {
            String infos = ApplicationAnimalJpa.sc.nextLine();
            animal.setPseudo(String.format(infos.split(" - ")[0]));
            animal.setPassword(String.format(infos.split(" - ")[1]));
            animal.setAge(Integer.parseInt(infos.split(" - ")[2]));
            animal.setEspece(String.format(infos.split(" - ")[3]));
            String nomVille = String.format(infos.split(" - ")[4]);
            RepositoryVilleJpa repoVilleCheck = new RepositoryVilleJpa();
            if (repoVilleCheck.findByNom(nomVille).isPresent()) {
                animal.setVille(repoVilleCheck.findByNom(nomVille).get());
            } else {
                System.out.println("La ville entrée n'existe pas encore, mais nous allons la créer ensemble !");
                repoVilleCheck.createEntry();
                animal.setVille(repoVilleCheck.findByNom(nomVille).get());
            }

        } catch (NumberFormatException e) {
            System.out.println("Le format de l'age et/ou de l'espece est incorrect.");
        } catch (IllegalFormatException e) {
            System.out.println("Le format du pseudo et/ou du mot de passe est incorrect.");
        }
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            try {
                if (animal.getId() == 0) {
                    em.persist(animal);
                } else {
                    animal = em.merge(animal);
                }
                em.getTransaction().commit();
                System.out.println("Votre animal a bien été créé !");
            } catch (Exception ex) {
                ex.printStackTrace();
                em.getTransaction().rollback();
            }
        }
    }

    @Override
    public void updateEntry(Animal animal) {
        boolean saisieValide = false;
        while (saisieValide == false) {
            System.out.println(
                    "\nVeuillez entrer les informations à modifier, au format champ(pseudo/motdepasse/age/espece/ville) - donnée, exemple : pseudo - Franklinlatortue)");
            String infos = ApplicationAnimalJpa.sc.nextLine();

            try {

                if (infos.startsWith("pseudo - ")) {
                    animal.setPseudo(String.format(infos.split("pseudo - ")[1]));
                    saisieValide = true;
                } else if (infos.startsWith("motdepasse - ")) {
                    animal.setPassword(String.format(infos.split("motdepasse - ")[1]));
                    saisieValide = true;
                } else if (infos.startsWith("age - ")) {
                    animal.setAge(Integer.parseInt(infos.split("age - ")[1]));
                    saisieValide = true;
                } else if (infos.startsWith("espece - ")) {
                    animal.setEspece(String.format(infos.split("espece - ")[1]));
                    saisieValide = true;
                } else if (infos.startsWith("ville - ")) {
                    RepositoryVilleJpa repoVille = new RepositoryVilleJpa();
                    if (repoVille.findByNom(String.format(infos.split("ville - ")[1])).isPresent()) {
                        animal.setVille(repoVille.findByNom(String.format(infos.split("ville - ")[1])).get());
                        saisieValide = true;
                    } else {
                        repoVille.createEntry();
                        animal.setVille(repoVille.findByNom(String.format(infos.split("ville - ")[1])).get());
                        saisieValide = true;
                    }
                }
            } catch (Exception e) {
                System.out.println(
                        "\nUne erreur s'est glissée dans votre saisie. Veuillez bien respecter le format de saisie des données à modifier :)");
            }
        }
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            try {
                if (animal.getId() == 0) {
                    em.persist(animal);
                } else {
                    animal = em.merge(animal);
                }
                em.getTransaction().commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                em.getTransaction().rollback();
            }
            System.out.println("Les infos de votre animal ont bien été modifiées.");
        }
    }

    @Override
    public void deleteEntry() {
        boolean saisieValide = false;
        while (saisieValide == false) {
            System.out.println("Entrez le pseudo à supprimer :");
            String pseudo = ApplicationAdminJpa.scadmin.nextLine();
            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();
                try {
                    em.createQuery("delete from Animal a where a.pseudo = ?1")
                            .setParameter(1, pseudo)
                            .executeUpdate();
                    em.getTransaction().commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    em.getTransaction().rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("L'animal a bien été supprimé de la base de données.");
            saisieValide = true;
        }

    }
}