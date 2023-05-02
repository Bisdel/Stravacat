package fr.formation;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.formation.config.AppConfig;
import fr.formation.model.Animal;
import fr.formation.repo.IRepositoryAnimal;

public class ApplicationAnimalAdminJpa {

    public static Scanner scadmin = new Scanner(System.in);

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            System.out.println("\nVous avez lancé le programme avec les droits d'administrateur.");

            IRepositoryAnimal repoAnimalJpa = context.getBean(IRepositoryAnimal.class);

            System.out.println("\n-- Affichage des animaux présents sur le site :");
            List<Animal> animaux = repoAnimalJpa.findAll();
            for (Animal animal : animaux) {
                System.out.println(
                        "Animal #" + animal.getId() + " : " + animal.getPseudo() + ", " + animal.getAge() + " ans.");
            }

            System.out.println("\nVoulez-vous supprimer un animal ?");

            if (Navigation.choixOuiNon()) {
                repoAnimalJpa.delete(animal);
            }

        } catch (BeansException e) {
            e.printStackTrace();
        }

        System.out.println("Merci d'être passé Monsieur l'Administrateur, ça fait toujours plaisir !\nA bientôt !");
    }

}
